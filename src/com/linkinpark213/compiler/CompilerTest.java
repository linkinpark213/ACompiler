package com.linkinpark213.compiler;

import com.linkinpark213.compiler.analyzer.lexical.LexicalAnalyzer;
import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.syntactic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.Program;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.StatementString;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class CompilerTest {
    public void printLexicalAnalysisResult(ArrayList<Token> tokens) {
        System.out.println("==================================");
        System.out.println(tokens);
        for (int i = 0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i).fullString());
        }
        System.out.println("==================================");
    }

    public String readCode(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        CompilerTest compilerTest = new CompilerTest();
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        ArrayList<Token> tokenQueue = lexicalAnalyzer.analyze(compilerTest.readCode(new File("code.txt")));
        ArrayList<Quad> quadQueue = new ArrayList<Quad>();
        compilerTest.printLexicalAnalysisResult(tokenQueue);
        Program root = new Program();
        System.out.println(root.analyze(root, tokenQueue, quadQueue));
        if (tokenQueue.size() > 0) {
            Token token = tokenQueue.get(1);
            System.out.println("Syntax Error at Row " + token.getRow() + ", Column " + token.getColumn());
        }
        root.printTree(0);
    }
}
