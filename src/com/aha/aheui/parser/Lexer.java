package com.aha.aheui.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import com.aha.aheui.ast.Instruction;
import com.aha.aheui.ast.Operation;
import com.aha.hangul.HangulSyllable;
import com.aha.util.Matrix;

public class Lexer {

    public Matrix<Instruction> getInstructions(String program) {
        BufferedReader reader = new BufferedReader(new StringReader(program));
        Matrix<Instruction> result = new Matrix<Instruction>();
        int lineNum = 0;
        try {
            String line;
            do {
                line = reader.readLine();
                if (line == null || line.length() == 0) {
                    continue;
                }
                char firstChar = line.charAt(0);
                if (HangulSyllable.isHangulSyllable(firstChar)) {
                    int syllableCount = 0;
                    for (int i = 0; i < line.length(); i++) {
                        char c = line.charAt(i);
                        if (HangulSyllable.isHangulSyllable(c)) {
                            Instruction instruction = new Instruction(
                                    new HangulSyllable(c), lineNum,
                                    syllableCount);
                            setOperation(instruction);
                            result.set(lineNum, syllableCount, instruction);
                            syllableCount++;
                        }
                    }
                    lineNum++;
                }
            } while (line != null);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private void setOperation(Instruction instruction) {
        switch (instruction.getSyllable().getInitialJamo()) {
        case Ieung:
            instruction.setOperation(Operation.Null);
            break;
        case Hieut:
            instruction.setOperation(Operation.Terminate);
            break;
        case Digeut:
            instruction.setOperation(Operation.Add);
            break;
        case SsangDigeut:
            instruction.setOperation(Operation.Multiply);
            break;
        case Nieun:
            instruction.setOperation(Operation.Divide);
            break;
        case Tieut:
            instruction.setOperation(Operation.Subtract);
            break;
        case Rieul:
            instruction.setOperation(Operation.Modulo);
            break;
        case Mieum:
            instruction.setOperation(Operation.Pop);
            break;
        case Bieup:
            instruction.setOperation(Operation.Push);
            break;
        case SsangBieup:
            instruction.setOperation(Operation.Duplicate);
            break;
        case Pieup:
            instruction.setOperation(Operation.Swap);
            break;
        case Siot:
            instruction.setOperation(Operation.Select);
            break;
        case SsangSiot:
            instruction.setOperation(Operation.Transfer);
            break;
        case Jieut:
            instruction.setOperation(Operation.Compare);
            break;
        case Chieut:
            instruction.setOperation(Operation.Decide);
            break;
        case Giyeok:
        case SsangGiyeok:
        case SsangJieut:
        case Kieuk:
            instruction.setOperation(Operation.InvalidOperation);
            break;
        default:
            throw new IllegalArgumentException("Unknown inital jamo");
        }
    }

}
