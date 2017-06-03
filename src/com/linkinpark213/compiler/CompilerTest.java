package com.linkinpark213.compiler;

import com.linkinpark213.compiler.analyzer.lexical.LexicalAnalyzer;
import com.linkinpark213.compiler.analyzer.lexical.dfd.IdentifierDFD;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class CompilerTest {
    public void printAnalyzeResult(ArrayList<Symbol> symbols) {
        System.out.println(symbols);
        for (int i = 0; i < symbols.size(); i++) {
            System.out.println(symbols.get(i).fullString());
        }
        System.out.println("==================================");
    }

    public static void main(String[] args) {
        CompilerTest compilerTest = new CompilerTest();
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        compilerTest.printAnalyzeResult(lexicalAnalyzer.analyze("{a:= true + 3 & 2   )"));
        compilerTest.printAnalyzeResult(lexicalAnalyzer.analyze("do{var := 4 + false;} while a"));
        compilerTest.printAnalyzeResult(lexicalAnalyzer.analyze("if a = 2 then { k = k *3 | d;}  "));
    }
}
