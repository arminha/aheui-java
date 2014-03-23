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

import com.aha.aheui.ast.Program;
import com.aha.aheui.ast.Instruction;
import com.aha.util.Matrix;
import com.aha.util.Tuple;

public class AheuiParser {

    public Tuple<Matrix<Instruction>, Program> parse(String source) {
        Lexer lexer = new Lexer();
        Matrix<Instruction> instructionTable = lexer.getInstructions(source);
        instructionTable.apply(new ParseParameters());
        instructionTable.apply(new ParseDirection());
        CodeBlockGenerator blockGenerator = new CodeBlockGenerator();
        Program program = blockGenerator.createProgram(instructionTable);
        program.mergeBlocks();

        return new Tuple<Matrix<Instruction>, Program>(instructionTable, program);
    }

}
