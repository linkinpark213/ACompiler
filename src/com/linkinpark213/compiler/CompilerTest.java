package com.linkinpark213.compiler;

import com.linkinpark213.compiler.analyzer.lexical.LexicalAnalyzer;
import com.linkinpark213.compiler.analyzer.lexical.dfd.IdentifierDFD;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class CompilerTest {
    public static void main(String[] args) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        ArrayList<Symbol> symbols = lexicalAnalyzer.analyze("while++ whileabc- dsef * fsd_3");
        for (int i = 0; i < symbols.size(); i++) {
            System.out.println(symbols.get(i).fullString());
        }
    }
}
