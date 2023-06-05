package org.example;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SourceCodeParser {


    public void commentChecker(String ignoreFile) throws IOException {



        List<String> reportLine = new ArrayList<>();
        String directoryPath = "";

        try {
            Files.walkFileTree(Paths.get(directoryPath), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(".java")) {
                        Path filePath= file.toAbsolutePath();
                        String fileName = filePath.getFileName().toString();

                        if(!(fileName.contains("SourceCodeParser.java") || fileName.contains("SourceCodeVisitor.java") )) {
                            {
                                ArrayList<String> missingComment = checkComment(String.valueOf(filePath));

                                for (String s: missingComment) {
                                    System.out.println("--"+s);
                                    reportLine.add(file.getParent()+fileName +":"+ s);
                                }

                            }
                        }

                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        generateReport(reportLine);

    }




        public ArrayList<String> checkComment(String filePath) throws IOException {
            String sourceCode = "";
            try {
                sourceCode = Files.readString(Paths.get(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }

            SourceCodeVisitor sourceCodeVisitor = new SourceCodeVisitor();


            if(sourceCode.contains("@Given") || sourceCode.contains("@When") || sourceCode.contains("@Then") || sourceCode.contains("@And"))
                return sourceCodeVisitor.misCommentList;

            else if (sourceCode.contains("public static By ")) {
                return sourceCodeVisitor.misCommentList;

            }


            ASTParser parser = ASTParser.newParser(AST.JLS19);
            parser.setSource(sourceCode.toCharArray());

            CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);
            compilationUnit.accept(sourceCodeVisitor);


            ArrayList<String> li = sourceCodeVisitor.getFoundCommentList();
            for(String s : li)
            {System.out.println(s);}

            return sourceCodeVisitor.misCommentList;


    }


    public static void generateReport(List<String> comment) throws IOException {
        File resultFile;
        Writer fileWriter;
        resultFile = new File("Results_final.html");

        if (!resultFile.exists()) {
            resultFile.createNewFile();
        }

        fileWriter = new FileWriter(resultFile.getAbsoluteFile());

        for (String s: comment) {
            System.out.println("+++" + s);
        }

        fileWriter.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        table {\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "table, th, td {\n" +
                "  padding: 15px;\n" +
                "}\n" +
                "\n" +
                "table, th, td {\n" +
                "  border: 2px solid white;\n" +
                "}\n" +
                "\n" +
                "th {\n" +
                "  border-bottom: 5px solid white;\n" +
                "}\n" +
                "\n" +
                "table th {\n" +
                "  background-color: #5b99d4;\n" +
                "  width: 25%;\n" +
                "  text-align: left;\n" +
                "}\n" +
                "\n" +
                "tr:hover {\n" +
                "  background-color: powderblue;\n" +
                "}\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>List of Methods that are not preceded by comments</h1>"+
                "<table border=\"1\">\n" +
                "    <tr><th style=\"width:10%\" >Sr no.</th><th>Class</th><th>Method</th><th>File Path</th><th>Comment Value</th></tr>");

        for (int i =0;i<comment.size();i++ )
        {
            String[] temp =comment.get(i).split(":");
            fileWriter.write("<tr><td>"+(i+1)+"</td><td>"+temp[2]+"</td><td>"+temp[1]+"</td><td>"+temp[0]+"</td><td>null</td></tr>");
        }

        fileWriter.write("</table>\n" +
                "</body>\n" +
                "</html>");
        fileWriter.close();
    }

}

