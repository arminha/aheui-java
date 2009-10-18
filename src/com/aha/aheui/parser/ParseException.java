package com.aha.aheui.parser;

public class ParseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int line;
    private int column;
    
    public ParseException(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public ParseException(int line, int column, String message) {
        super(message);
        this.line = line;
        this.column = column;
    }

    public ParseException(int line, int column, Throwable throwable) {
        super(throwable);
        this.line = line;
        this.column = column;
    }

    public ParseException(int line, int column, String message, Throwable throwable) {
        super(message, throwable);
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

}
