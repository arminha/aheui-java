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

package com.aha.aheui;

import java.io.PrintStream;

import com.aha.aheui.ast.CodeBlock;
import com.aha.aheui.ast.Instruction;
import com.aha.aheui.ast.Program;
import com.aha.aheui.interpreter.AheuiInterpreter;
import com.aha.aheui.interpreter.Main;
import com.aha.aheui.parser.AheuiParser;
import com.aha.hangul.FinalJamo;
import com.aha.hangul.HangulSyllable;
import com.aha.hangul.InitialJamo;
import com.aha.hangul.MedialJamo;
import com.aha.util.Matrix;
import com.aha.util.Tuple;

import org.junit.Test;

public class HangulUtilsTest {

    public static final String FIBBONACCI_EXAMPLE = "살뷸바우박우뭏뻐설멍뻐선썬퍼\n"
            + "삭방손삳볻타삭뿌박투빠쑨싿솓\n" + "도벌후멓설멍섣차볻타솓산도해\n";

    public static final String HELLO_WORLD_EXAMPLE = "밤밣따빠밣밟따뿌\n" + "빠맣파빨받밤뚜뭏\n"
            + "돋밬탕빠맣붏두붇\n" + "볻뫃박발뚷투뭏붖\n" + "뫃도뫃희멓뭏뭏붘\n" + "뫃봌토범더벌뿌뚜\n"
            + "뽑뽀멓멓더벓뻐뚠\n" + "뽀덩벐멓뻐덕더벅\n";

    @Test
    public void testHello() throws Exception {
        AheuiParser parser = new AheuiParser();
        Tuple<Matrix<Instruction>, Program> result = parser.parse(HELLO_WORLD_EXAMPLE);

        Program program = result.getSecond();
        AheuiInterpreter interpreter = new AheuiInterpreter();
        interpreter.run(program);
    }

    @Test
    public void testParser() throws Exception {
        PrintStream out = new PrintStream(System.out, true, "UTF-8");

        HangulSyllable syllable = new HangulSyllable('민');
        out.println(syllable.getInitialJamo());
        out.println(syllable.getMedialJamo());
        out.println(syllable.getFinalJamo());
        out.println(syllable.character());

        HangulSyllable po = new HangulSyllable(InitialJamo.Pieup, MedialJamo.Eo, FinalJamo.None);
        out.println(po.character());
        out.println((int) po.character());
        out.println(HangulSyllable.isHangulSyllable(po.character()));

        AheuiParser parser = new AheuiParser();
        Tuple<Matrix<Instruction>, Program> result = parser.parse(FIBBONACCI_EXAMPLE);
        Matrix<Instruction> instructions = result.getFirst();
        for (int j = 0; j < instructions.getRows(); j++) {
            out.println("Line " + j + ":");
            for (int i = 0; i < instructions.getColumns(); i++) {
                out.println(instructions.get(j, i));
            }
        }

        out.println();

        Program program = result.getSecond();

        out.println("Number of code blocks: " + program.getCodeBlocks().size());
        for (CodeBlock codeBlock : program.getCodeBlocks()) {
            out.println("Block " + codeBlock.getUniqueName());
            for (Instruction instruction : codeBlock.getInstructions()) {
                out.println("    " + instruction.toString());
            }
            if (codeBlock.getNext() != null) {
                out.println("  Next = " + codeBlock.getNext().getUniqueName());
            }
            if (codeBlock.getAlternativeNext() != null) {
                out.println("  AlternativeNext = " + codeBlock.getAlternativeNext().getUniqueName());
            }
        }

        out.println();

        out.flush();
    }

    @Test
    public void testInterpreter() {
        Main.main(new String[] {"doc/examples/Hello.aheui"});
    }

}
