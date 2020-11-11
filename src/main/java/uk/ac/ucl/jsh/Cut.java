package uk.ac.ucl.jsh;

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
import java.util.List;
import java.util.stream.Collectors;

public class Cut implements Application {

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);

        argCheck(args);

        String start_end = args.get(1).replaceAll("[^-?0-9]+", " ");
        List<String> line_args = Arrays.asList(start_end.trim().split(" "));

        // Concatenate all args into a single string
        String concat_args = String.join(", ", line_args);
        List<Integer> clean_args = parse_cut_input(concat_args);

        String file_name = args.get(2);

        Charset encoding = StandardCharsets.UTF_8;
        File curr_File = new File(currentDirectory + File.separator + file_name);
        if (curr_File.exists()) {
            Path file_Path = Paths.get(currentDirectory + File.separator + file_name);
            try (BufferedReader reader = Files.newBufferedReader(file_Path, encoding)) {
                writeOut(reader, clean_args, writer);
            } catch (IOException e) {
                throw new RuntimeException("cut: cannot open " + file_name);
            }
        } else {
            throw new RuntimeException("cut: file does not exist");
        }

        return currentDirectory;
    }

    /* Prints to specified output */
    private void writeOut(BufferedReader reader, List<Integer> clean_args, OutputStreamWriter writer) throws IOException {
        String line;
        ArrayList<Character> seperated_bytes = new ArrayList<Character>();

        while ((line = reader.readLine()) != null) {
            String line_from_textfile = String.valueOf(line);
            seperated_bytes.clear();
            for (int i : clean_args) {
                if (i < 0) {
                    throw new RuntimeException("cut: byte index specified does not exist");
                } else if (i < line_from_textfile.length()) {
                    seperated_bytes.add(line_from_textfile.charAt(i));
                }
            }
            String resulting_line = seperated_bytes.stream().map(String::valueOf).collect(Collectors.joining());

            writer.write(resulting_line);
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        }
    }

    /* Validates arguments input */
    private void argCheck(ArrayList<String> args) throws IOException {
        if (args.isEmpty()) {
            throw new RuntimeException("cut: missing arguments");
        } else if (args.size() != 1 && args.size() != 3) {
            throw new RuntimeException("cut: wrong arguments");
        } else if (args.size() == 3 && !args.get(0).equals("-b")) {
            throw new RuntimeException("cut: wrong argument " + args.get(0));
        }
    }

    // Parses cut case input
    private static List<Integer> parse_cut_input(String str) throws IOException {

        // Num of char per line (set as arbitrary):
        int num_of_char_per_line = 1000;

        // Split the arguments into a List
        str = str.replaceAll("[^-?0-9]+", " ");
        String[] line_args = str.trim().split(" ");
        List<Integer> total_range = new ArrayList<Integer>();

        // Parse each element to extend arguments where necessary
        for (String elem : line_args) {
            try {
                // For: X,Y,Z (e.g.: 1,2,3)
                int int_elem = Integer.parseInt(elem);
                if (int_elem < 0) {
                    for (int i = 1; i <= Math.abs(int_elem); i++) {
                        total_range.add(i - 1);
                    }
                } else {
                    total_range.add(int_elem - 1);
                }
            } catch (Exception e) {
                // Everything that is "X-"
                elem = elem.replaceAll("[^?0-9]+", " ");
                List<String> inner_range = Arrays.asList(elem.trim().split(" "));

                switch (inner_range.size()) { 
                    // For: X- (e.g.: 1-)
                    case 1:
                        try {
                            int converted_elem = Integer.parseInt(inner_range.get(0));
                            for (int j = converted_elem; j < num_of_char_per_line; j++) {
                                if (!total_range.contains(j)) {
                                    total_range.add(j - 1);
                                }
                            }
                        } catch (Exception f) {
                            throw new IOException("cut: could not convert arguments");
                        }
                        break;
                    // For: X-Y, where X < Y (e.g.: 1-3)
                    case 2:
                        try {
                            int converted_start = Integer.parseInt(inner_range.get(0));
                            int converted_end = Integer.parseInt(inner_range.get(1));
                            for (int j = converted_start; j <= converted_end; j++) {
                                if (!total_range.contains(j)) {
                                    total_range.add(j - 1);
                                }
                            }
                        } catch (Exception f) {
                            throw new IOException("cut: could not convert args");
                        }
                        break;
                    default:
                        throw new IOException("cut: incorrect list ranges");
                }
            }
        }
        return total_range;
    }
}
