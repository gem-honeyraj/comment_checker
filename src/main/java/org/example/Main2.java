package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {

    public static void main(String[] args) {

     /*   String jarFilePath = "path/to/your.jar";
        String outputDir = "output/directory";

        try {
            // Create a decompiler instance
            Decompiler decompiler = new Decompiler();

            // Set the output directory for decompiled files
            decompiler.setOutputDir(outputDir);

            // Decompile the .jar file
            decompiler.decompileClasspath(jarFilePath);

            // Optionally, you can decompile a single class from the .jar file
            // decompiler.decompileClass(className);

            // Save the decompiled files to disk
            decompiler.save();

            System.out.println("Decompilation completed successfully.");
        } catch (DecompilerException | IOException e) {
            System.err.println("An error occurred during decompilation: " + e.getMessage());
        }


      */


        String sourceCode = "";
        try {
            sourceCode = Files.readString(Paths.get("C:\\Users\\honey.raj\\IdeaProjects\\JavaParser\\target\\classes\\org\\example\\Example.class"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sourceCode);


    }
}
