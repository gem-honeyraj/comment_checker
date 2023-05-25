package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {

    public static void main(String[] args) {


        String filePath = "src/main/java/org/example/Test.java";
        try (
                BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sourceCode = new StringBuilder();
            String line;
            while (true) {
                try {
                    if (!((line = reader.readLine()) != null)) break;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                sourceCode.append(line).append(System.lineSeparator());
            }

            String regex = "/\\*\\*(.|[\\r\\n])*?\\*/|//.*";
            Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(sourceCode.toString());

            while (matcher.find()) {
                String comment = matcher.group();
                System.out.println("Comment: " + comment.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
