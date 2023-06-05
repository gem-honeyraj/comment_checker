package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Example {
    public static void main(String[] args) {
        String jarFilePath = "C:\\Users\\honey.raj\\Downloads\\gemjar-core-1.2.3.jar";

        try (JarFile jarFile = new JarFile(jarFilePath)) {
            Enumeration<JarEntry> entries = jarFile.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();

                if (!entry.isDirectory() && entry.getName().endsWith(".java")) {
                    try (InputStream inputStream = jarFile.getInputStream(entry);
                         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                        String line;

                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
