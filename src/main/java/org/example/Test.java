package org.example;

import java.io.IOException;

public class Test {





    public static void method2(){
        System.out.println("m1");
    }


    /**
     * @author OYYYYY
     * @version 1.2.3
     */
    public static void method322(){
        System.out.println("m322");
    }

    public static void main(String[] args) throws IOException {

        method2();
        SourceCodeParser sourceCodeParser = new SourceCodeParser();
        sourceCodeParser.commentChecker("");
        method322();




    }









}
