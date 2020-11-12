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

package com.aha.aheui.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodeBlock {

    private final DirectionModifier previousDirectionModifier;
    private final List<Instruction> instructions;

    private CodeBlock next;
    private CodeBlock alternativeNext;

    public CodeBlock(Instruction firstInstruction) {
        instructions = new ArrayList<>();
        instructions.add(firstInstruction);
        previousDirectionModifier = null;
    }

    public CodeBlock(Instruction firstInstruction, Instruction previousInstruction) {
        instructions = new ArrayList<>();
        instructions.add(firstInstruction);
        previousDirectionModifier = computePreviousDirectionModifier(firstInstruction, previousInstruction);
    }

    public DirectionModifier getPreviousDirectionModifier() {
        return previousDirectionModifier;
    }

    public CodeBlock getNext() {
        return next;
    }

    public void setNext(CodeBlock next) {
        this.next = next;
    }

    public CodeBlock getAlternativeNext() {
        return alternativeNext;
    }

    public void setAlternativeNext(CodeBlock alternativeNext) {
        this.alternativeNext = alternativeNext;
    }

    public List<Instruction> getInstructions() {
        return Collections.unmodifiableList(instructions);
    }

    void mergeWithNext() {
        if (next == null || alternativeNext != null) {
            throw new IllegalStateException();
        }
        instructions.addAll(next.getInstructions());
        alternativeNext = next.getAlternativeNext();
        next = next.getNext();
    }

    public String getUniqueName() {
        Instruction first = instructions.get(0);
        return getNameInternal(first, previousDirectionModifier);
    }

    private static DirectionModifier computePreviousDirectionModifier(Instruction instruction,
            Instruction previousInstruction) {
        int lineDiff = instruction.getLine() - previousInstruction.getLine();
        int columnDiff = instruction.getColumn() - previousInstruction.getColumn();
        DirectionModifier modifier;
        if (lineDiff == 0) {
            switch (columnDiff) {
            case -2:
                modifier = DirectionModifier.DoubleLeft;
                break;
            case -1:
                modifier = DirectionModifier.Left;
                break;
            case 1:
                modifier = DirectionModifier.Rigth;
                break;
            case 2:
                modifier = DirectionModifier.DoubleRigth;
                break;
            default:
                throw new IllegalArgumentException();
            }
        } else if (columnDiff == 0) {
            switch (lineDiff) {
            case -2:
                modifier = DirectionModifier.DoubleUp;
                break;
            case -1:
                modifier = DirectionModifier.Up;
                break;
            case 1:
                modifier = DirectionModifier.Down;
                break;
            case 2:
                modifier = DirectionModifier.DoubleDown;
                break;
            default:
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
        return modifier;
    }

    private static String getNameInternal(Instruction instruction, DirectionModifier previousDirectionModifier) {
        StringBuilder sb = new StringBuilder();
        sb.append('l').append(instruction.getLine());
        sb.append('r').append(instruction.getColumn());
        if (previousDirectionModifier != null) {
            sb.append(previousDirectionModifier.name());
        }
        return sb.toString();
    }

    static String getUniqueName(Instruction instruction, Instruction previousInstruction) {
        if (previousInstruction != null) {
            return getNameInternal(instruction, computePreviousDirectionModifier(instruction, previousInstruction));
        } else {
            return getNameInternal(instruction, null);
        }
    }
}
