package com.linkinpark213.compiler.analyzer.lexical.dfd;
import com.linkinpark213.compiler.analyzer.lexical.symbols.*;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public interface DFD {
    public Symbol nextSymbol(String string);
}
