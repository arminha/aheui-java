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

package com.aha.aheui.interpreter;

import java.io.FileInputStream;
import java.io.IOException;

import com.aha.aheui.ast.Instruction;
import com.aha.aheui.ast.Program;
import com.aha.aheui.parser.AheuiParser;
import com.aha.util.Matrix;
import com.aha.util.Tuple;
import com.aha.util.streams.StreamHelper;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            printUsage();
            System.exit(-1);
        }
        
        String source = null;
        try {
            FileInputStream fIn = new FileInputStream(args[0]);
            source = StreamHelper.readAll(fIn, "UTF-8");
            fIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
        AheuiParser parser = new AheuiParser();
        Tuple<Matrix<Instruction>, Program> result = parser.parse(source);
        
        Program program = result.getSecond();
        AheuiInterpreter interpreter = new AheuiInterpreter();
        interpreter.run(program);
    }

    private static void printUsage() {
        // TODO Auto-generated method stub
        
    }

}
