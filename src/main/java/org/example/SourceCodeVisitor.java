package org.example;

import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;


public class SourceCodeVisitor extends ASTVisitor{


    ArrayList<String> misCommentList = new ArrayList<>();
    ArrayList<String> foundCommentList = new ArrayList<>();
    String commentText="";



    @Override
    public boolean visit(MethodDeclaration node) {
        String methodName = node.getName().getIdentifier();

        if(methodName.equals("main"))
            return false;

        String className = getClassName(node);
        String comment = getComment(node);
        if (!(comment.trim().equals("")) && comment.trim() != null ) {
            commentText = comment.toString();
            foundCommentList.add(methodName+":"+className+":"+comment);
        }

        else
        {
            misCommentList.add(methodName+":"+className);
        //    throw new RuntimeException("Comment not found before class:"+className+"    method:"+methodName);
        }
        return super.visit(node);
    }

    private String getClassName(ASTNode astNode) {
        //to get line number
//        int lineNumber = astNode.getStartPosition();
//        int line = astNode.getLineNumber(lineNumber);
        TypeDeclaration classDeclaration = null;

        while (astNode != null) {
            if (astNode instanceof TypeDeclaration) {
                classDeclaration= (TypeDeclaration) astNode;
            }
            astNode = astNode.getParent();
        }
        return classDeclaration.getName().getIdentifier();

    }


    private String getComment(BodyDeclaration node) {

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

        String s="";

        Comment comment = node.getJavadoc();

        if(comment!= null) {
           s= comment.toString().substring(3, comment.toString().length() - 2);
            return s;
        }
        else
            return s;
    }


    public ArrayList<String> getMissingCommentList() {
        return this.misCommentList;
    }

    public ArrayList<String> getFoundCommentList() {
        return this.foundCommentList;
    }

}
