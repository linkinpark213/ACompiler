package com.linkinpark213.compiler.analyzer.lexical.dfa;
import com.linkinpark213.compiler.analyzer.lexical.LexicalAnalyzer;
import com.linkinpark213.compiler.error.lexical.InvalidSymbolError;
import com.linkinpark213.compiler.analyzer.lexical.tokens.*;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public interface DFA {
    public Token nextSymbol(String string, LexicalAnalyzer analyzer) throws InvalidSymbolError;
}
