package org.example;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testsample {


    public static void main(String[] args) {
        String[] expressions = {
                "public static By",
                "public   static    By",
                "public By",
                "public By and other text",
                "pUblic By and other text",
                "other text public static By"
        };

        String pattern = "public\\s*static\\s*By";

        // Create a pattern
        Pattern regex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);

        for (String expression : expressions) {
            // Create a matcher
            Matcher matcher = regex.matcher(expression);

            // Use the find method to check if the pattern is found
            if (matcher.find()) {
                System.out.println("Pattern found in: " + expression);
            } else {
                System.out.println("");
            }
        }
    }

}
