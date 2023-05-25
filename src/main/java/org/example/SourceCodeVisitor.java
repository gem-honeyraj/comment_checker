package org.example;

import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.List;


public class SourceCodeVisitor extends ASTVisitor{


    List<String> misCommentList = new ArrayList<>();

    @Override
    public boolean visit(MethodDeclaration node) {
        String methodName = node.getName().getIdentifier();


        if(methodName.equals("main"))
            return false;

        //to get line number
//        int lineNumber = node.getStartPosition();
//        int line = rootNode.getLineNumber(lineNumber);

        Comment comment = getComment(node);
        if (comment != null) {
            String commentText = comment.toString();
            System.out.println("Method: " + methodName);
            System.out.println("Comment: " + commentText);
            System.out.println();
        }

        else
        {
            String className = getClassName(node);
            misCommentList.add("Comment not found before class: "+className+" method: "+methodName);
          //  throw new RuntimeException("Comment not found before class: "+className+" method: "+methodName);
        }
        return super.visit(node);
    }

    private String getClassName(ASTNode atsNode) {
        TypeDeclaration classDeclaration = null;

        while (atsNode != null) {
            if (atsNode instanceof TypeDeclaration) {
                classDeclaration= (TypeDeclaration) atsNode;
            }
            atsNode = atsNode.getParent();
        }
        return classDeclaration.getName().getIdentifier();

    }


    private Comment getComment(BodyDeclaration node) {

      //  List<Comment> comments = ((CompilationUnit) node.getRoot()).getCommentList();
      //  int nodeStartPosition = node.getStartPosition();

       /* Comment comment = null;
        for (Comment c : comments) {
            int commentStartPosition = c.getStartPosition();
            int commentEndPosition = commentStartPosition + c.getLength();

            if (commentStartPosition <= nodeStartPosition && commentEndPosition > nodeStartPosition) {
                if (!c.toString().isEmpty()) {
                    comment = c;
                    break;
                }
            }
        }

        */
        Comment comment = node.getJavadoc();
        return comment;
    }


    public List<String> getMissingCommentList() {
        return this.misCommentList;
    }
}
