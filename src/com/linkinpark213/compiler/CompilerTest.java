package com.linkinpark213.compiler;

import com.linkinpark213.compiler.analyzer.lexical.LexicalAnalyzer;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.StatementString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.keyword.IfKeyword;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class CompilerTest {
    public void printAnalyzeResult(ArrayList<Symbol> symbols) {
        System.out.println("==================================");
        System.out.println(symbols);
        for (int i = 0; i < symbols.size(); i++) {
            System.out.println(symbols.get(i).fullString());
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
        ArrayList<Symbol> symbolQueue = lexicalAnalyzer.analyze(compilerTest.readCode(new File("code.txt")));
        compilerTest.printAnalyzeResult(symbolQueue);
        StatementString root = new StatementString();
        System.out.println(root.analyze(root, symbolQueue));
        if (symbolQueue.size() > 0) {
            Symbol symbol = symbolQueue.get(1);
            System.out.println("Syntax Error at Row " + symbol.getRow() + ", Column " + symbol.getColumn());
        }
        root.printTree(0);
        root.getChildren();
    }
}
