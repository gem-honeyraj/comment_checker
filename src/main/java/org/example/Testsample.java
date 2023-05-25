package org.example;
import org.eclipse.jdt.core.dom.*;
public class Testsample {



        public static void main(String[] args) {
            String sourceCode = "public class MyClass {\n" +
                    "    public void method1() {\n" +
                    "        // Method 1\n" +
                    "    }\n" +
                    "    public void method2() {\n" +
                    "        // Method 2\n" +
                    "    }\n" +
                    "}";

            ASTParser parser = ASTParser.newParser(AST.JLS19);
            parser.setSource(sourceCode.toCharArray());
            ASTNode rootNode = parser.createAST(null);

            int methodCount = 0;
            rootNode.accept(new ASTVisitor() {
                @Override
                public boolean visit(MethodDeclaration node) {
                    methodCount=methodCount+1;
                    return super.visit(node);
                }
            });

            System.out.println("Number of methods: " + methodCount);
        }
    }

}
