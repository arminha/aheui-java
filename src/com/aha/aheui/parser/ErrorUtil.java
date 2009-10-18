package com.aha.aheui.parser;

import com.aha.aheui.ast.Instruction;

public class ErrorUtil {

    public static void error(int line, int column, ErrorLevel errorLevel, String message) {
        switch (errorLevel) {
        case Error:
            throw new ParseException(line, column, message);
        case Warn:
            // TODO
            break;
        case Ignore:
            // TODO
            break;
        default:
            throw new IllegalArgumentException("errorLevel");
        }
    }
    
    public static void error(Instruction instruction, ErrorLevel errorLevel, String message) {
        error(instruction.getLine(), instruction.getColumn(), errorLevel, message);
    }
    
}
