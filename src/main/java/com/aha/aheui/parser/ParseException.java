/*
 * Copyright (C) 2012  Armin Häberling
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

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
