package uk.ac.ucl.jsh.Applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cut implements Application {

    private OutputStreamWriter writer;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException {

        String message = argCheck(args);
        if (!message.equals("nothing")) {
            throwError(message, output);
        } else {
            return exec(args, currentDirectory, output);
        }
        return "";
    }

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException {
        writer = new OutputStreamWriter(output);

        String start_end = args.get(1).replaceAll("[^-?0-9]+", " ");
        List<String> line_args = Arrays.asList(start_end.trim().split(" "));

        // Concatenate all args into 1 string
        String concat_args = String.join(", ", line_args);
        List<Integer> clean_args = parse_cut_input(concat_args);
        String file_name = args.get(2);

        process(currentDirectory, clean_args, file_name);

        return currentDirectory;
    }

    private void process(String currentDirectory, List<Integer> clean_args, String file_name) {
        Charset encoding = StandardCharsets.UTF_8;
        File curr_File = new File(currentDirectory + File.separator + file_name);
        if (curr_File.exists()) {
            Path file_Path = Paths.get(currentDirectory + File.separator + file_name);
            try (BufferedReader reader = Files.newBufferedReader(file_Path, encoding)) {
                writeOut(reader, clean_args);
            } catch (IOException e) {
                throw new RuntimeException("cut: cannot open " + file_name);
            }
        } else {
            throw new RuntimeException("cut: file does not exist");
        }
    }

    /* Prints to specified output */
    private void writeOut(BufferedReader reader, List<Integer> clean_args) throws IOException {
        String line;
        ArrayList<Character> separated_bytes;

        while ((line = reader.readLine()) != null) {
            separated_bytes = new ArrayList<>();
            for (int i : clean_args) {
                if (i < 0) {
                    throw new RuntimeException("cut: byte index specified does not exist");
                } else if (i < line.length()) {
                    separated_bytes.add(line.charAt(i));
                }
            }
            String resulting_line = separated_bytes.stream().map(String::valueOf).collect(Collectors.joining());
            outputToConsole(resulting_line);
        }
    }

    private void outputToConsole(String line) throws IOException {
        writer.write(line);
        writer.write(System.getProperty("line.separator"));
        writer.flush();
    }

    /* Validates arguments input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.isEmpty()) {
            return "cut: missing arguments";
        } else if (args.size() != 1 && args.size() != 3) {
            return "cut: wrong arguments";
        } else if (args.size() == 3 && !args.get(0).equals("-b")) {
            return "cut: wrong argument " + args.get(0);
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }

    private List<Integer> parse_caseOne(List<String> inner_range, List<Integer> total_range) throws IOException {
        List<Integer> final_lst = new ArrayList<>();
        try {
            int converted_elem = Integer.parseInt(inner_range.get(0));
            int num_of_char_per_line = 1000;
            for (int j = converted_elem + 1; j < num_of_char_per_line; j++) {
                if (!total_range.contains(j)) {
                    final_lst.add(j - 1);
                }
            }
        } catch (Exception f) {
            throw new IOException("cut: could not convert arguments");
        }
        return final_lst;
    }

    private List<Integer> parse_caseTwo(List<String> inner_range, List<Integer> total_range) throws IOException {
        List<Integer> final_lst = new ArrayList<>();
        try {
            int converted_start = Integer.parseInt(inner_range.get(0)), converted_end = Integer.parseInt(inner_range.get(1));
            for (int j = converted_start + 1; j <= converted_end; j++) {
                if (!total_range.contains(j)) {
                    final_lst.add(j - 1);
                }
            }
        } catch (Exception f) {
            throw new IOException("cut: could not convert args");
        }
        return final_lst;
    }

    private List<Integer> parse_caseZero(String elem) {
        List<Integer> final_lst = new ArrayList<>();
        int int_elem = Integer.parseInt(elem);
        if (int_elem < 0) {
            for (int i = 1; i <= Math.abs(int_elem); i++) {
                final_lst.add(i - 1);
            }
        } else {
            final_lst.add(int_elem - 1);
        }
        return final_lst;
    }

    // Parses cut case input
    private List<Integer> parse_cut_input(String str) throws IOException {
        // Split the arguments into a List
        str = str.replaceAll("[^-?0-9]+", " ");
        String[] line_args = str.trim().split(" ");
        List<Integer> total_range = new ArrayList<>();

        // Parse each element to extend arguments where necessary
        for (String elem : line_args) {
            try {
                total_range = Stream.of(total_range, parse_caseZero(elem))
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
            } catch (Exception e) {
                elem = elem.replaceAll("[^?0-9]+", " ");
                List<String> inner_range = Arrays.asList(elem.trim().split(" "));

                switch (inner_range.size()) {
                    case 1: // For: X- (e.g.: 1-)
                        total_range = Stream.of(total_range, parse_caseOne(inner_range, total_range))
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList());
                        break;
                    case 2: // For: X-Y, where X < Y (e.g.: 1-3)
                        total_range = Stream.of(total_range, parse_caseTwo(inner_range, total_range))
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList());
                        break;
                    default:
                        throw new IOException("cut: incorrect list ranges");
                }
            }
        }
        return total_range;
    }
}