package com.javawebcompiler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JavaCompilerTests {
    public static void main(String[] args) {
        String path = "C:/upload/DynamicClass.java";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int results = compiler.run(null, null, null, path);
        System.out.println("Result : " + results);
    }
}
