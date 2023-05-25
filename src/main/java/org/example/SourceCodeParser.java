package org.example;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class SourceCodeParser {

    public static void main(String[] args) {

        String directoryPath = "src";

        try {
            Files.walkFileTree(Paths.get(directoryPath), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(".java")) {
                        Path filePath= file.toAbsolutePath();
                        if(!(filePath.getFileName().toString().contains("SourceCodeParser.java") || filePath.getFileName().toString().contains("SourceCodeVisitor.java")))
                        checkComment(String.valueOf(filePath));
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void checkComment(String filePath)
        {
            String sourceCode = "";
            try {
                sourceCode = Files.readString(Paths.get(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }

            ASTParser parser = ASTParser.newParser(AST.JLS19);

            parser.setSource(sourceCode.toCharArray());

            CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);

            SourceCodeVisitor sourceCodeVisitor = new SourceCodeVisitor();



            compilationUnit.accept(sourceCodeVisitor);


            List<String> lis = sourceCodeVisitor.getMissingCommentList();


            for (String s: lis)
            {
                System.out.println(s);
            }

    }
}

