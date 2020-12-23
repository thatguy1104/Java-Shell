package uk.ac.ucl.jsh.Applications;

import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cut implements Application {

    private OutputStreamWriter writer;

    @Override
    public String mainExec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        String message = argCheck(args);
        String appResult;
        if (!message.equals("nothing")) {
            throwError(message, output);
        } else {
            appResult = exec(args, currentDirectory, input, output);
            if (appResult.startsWith("ERROR")) {
                throwError(appResult.substring(6), output);
            }
            return appResult;
        }
        return "";
    }

    @Override
    public String exec(ArrayList<String> args, String currentDirectory, InputStream input, OutputStream output) throws IOException {
        writer = new OutputStreamWriter(output);

        if (args.size() == 0) {
            args = new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(new Scanner(input)))));
        }

        String concat_args = Stream.of(args.get(2)
                .replaceAll("[^-?0-9]+", " ")
                .split(" "))
                .map (String::new)
                .collect(Collectors.joining(", "));

        List<Integer> clean_args = parse_cut_input(concat_args);

        if (clean_args.get(0) == -2) {
            return "ERROR cut: incorrect list ranges";
        } else if (clean_args.get(0) == -1) {
            return "ERROR cut: could not convert arguments";
        }

        String file_name = args.get(3);

        return process(currentDirectory, clean_args, file_name);
    }

    private String process(String currentDirectory, List<Integer> clean_args, String file_name) throws IOException {
        File curr_File = new File(currentDirectory + File.separator + file_name);
        if (curr_File.exists()) {
            Path filePath = Paths.get(currentDirectory + File.separator + file_name);
            Scanner scn = new Scanner(filePath);
            try {
                if (!writeOut(scn, clean_args)) {
                    return "ERROR cut: byte index specified does not exist";
                }
            } catch (IOException e) {
                return "ERROR cut: cannot open " + file_name;
                //throw new RuntimeException("cut: cannot open " + file_name);
            }
            return currentDirectory;
        } else {
            return "ERROR cut: file does not exist";
            //throw new RuntimeException("cut: file does not exist");
        }
    }

    /* Prints to specified output */
    private Boolean writeOut(Scanner scn, List<Integer> clean_args) throws IOException {
        ArrayList<Character> separated_bytes;

        while (scn.hasNextLine()) {
            String line = scn.nextLine();
            separated_bytes = new ArrayList<>();
            for (int i : clean_args) {
                if (i < 0) {
                    return false;
                    //throw new RuntimeException("cut: byte index specified does not exist");
                } else if (i < line.length()) {
                    separated_bytes.add(line.charAt(i));
                }
            }
            String resulting_line = separated_bytes.stream().map(String::valueOf).collect(Collectors.joining());
            outputToConsole(resulting_line);
        }
        return true;
    }

    private void outputToConsole(String line) throws IOException {
        writer.write(line + Jsh.lineSeparator);
        writer.flush();
    }

    /* Validates arguments input */
    @Override
    public String argCheck(ArrayList<String> args) {
        if (args.size() == 1) {
            return "cut: missing arguments";
        } else if (args.size() != 2 && args.size() != 4) {
            return "cut: wrong arguments";
        } else if (args.size() == 4 && !args.get(1).equals("-b")) {
            return "cut: wrong argument " + args.get(1);
        } else {
            return "nothing";
        }
    }

    @Override
    public void throwError(String message, OutputStream output) {
        throw new RuntimeException(message);
    }

    private List<Integer> parse_caseOne(List<String> inner_range, List<Integer> total_range) {
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
            return null;
            //throw new IOException("cut: could not convert arguments");
        }
        return final_lst;
    }

    private List<Integer> parse_caseTwo(List<String> inner_range, List<Integer> total_range) {
        List<Integer> final_lst = new ArrayList<>();
        try {
            int converted_start = Integer.parseInt(inner_range.get(0));
            int converted_end = Integer.parseInt(inner_range.get(1));
            for (int j = converted_start + 1; j <= converted_end; j++) {
                if (!total_range.contains(j)) {
                    final_lst.add(j - 1);
                }
            }
        } catch (Exception f) {
            return null;
            //throw new IOException("cut: could not convert arguments");
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
    private List<Integer> parse_cut_input(String str) {
        // Split the arguments into a List
        List<String> line_args  = Stream.of(str
                .replaceAll("[^-?0-9]+", " ")
                .split(" "))
                .map (String::new)
                .collect(Collectors.toList());

        List<Integer> total_range = new ArrayList<>();
        List<Integer> case_one;
        List<Integer> case_two;

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
                        case_one = parse_caseOne(inner_range, total_range);
                        if (case_one == null) {
                            total_range.set(0, -1);
                            return total_range;
                        }
                        total_range = Stream.of(total_range, case_one)
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList());
                        break;
                    case 2: // For: X-Y, where X < Y (e.g.: 1-3)
                        case_two = parse_caseTwo(inner_range, total_range);
                        if (case_two == null) {
                            total_range.set(0, -1);
                            return total_range;
                        }
                        total_range = Stream.of(total_range, case_two)
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList());
                        break;
                    default:
                        total_range.set(0, -2);
                        return total_range;
                        //throw new IOException("cut: incorrect list ranges");
                }
            }
        }
        return total_range;
    }
}