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

import com.aha.aheui.ast.Instruction;

class ErrorUtil {

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
