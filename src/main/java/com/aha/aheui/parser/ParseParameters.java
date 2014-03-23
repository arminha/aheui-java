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
import com.aha.aheui.ast.Operation;
import com.aha.aheui.ast.Storage;
import com.aha.aheui.visitor.AbstractVisitor;
import com.aha.hangul.FinalJamo;
import com.aha.util.Method;

class ParseParameters extends AbstractVisitor<Instruction> implements Method<Instruction> {

    private ErrorLevel invalidParameters = ErrorLevel.Ignore;

    public ErrorLevel getInvalidParameters() {
        return invalidParameters;
    }

    public void setInvalidParameters(ErrorLevel invalidParameters) {
        this.invalidParameters = invalidParameters;
    }

    @Override
    public void apply(Instruction instruction) {
        visit(instruction);
    }

    @Override
    public Instruction visit(Instruction instruction) {
        if (instruction.getOperation().hasParameters() || instruction.getOperation() == Operation.Pop) {
            return super.visit(instruction);
        }
        return instruction;
    }

    @Override
    public Instruction visitPop(Instruction instruction) {
        switch (instruction.getSyllable().getFinalJamo()) {
        case Ieung:
            instruction.setOperation(Operation.PrintInteger);
            break;
        case Hieut:
            instruction.setOperation(Operation.PrintCharacter);
            break;
        case None:
            break;
        default:
            ErrorUtil.error(instruction, invalidParameters, "");
        }
        return instruction;
    }

    @Override
    public Instruction visitPush(Instruction instruction) {
        switch (instruction.getSyllable().getFinalJamo()) {
        case Ieung:
            instruction.setOperation(Operation.ReadInteger);
            break;
        case Hieut:
            instruction.setOperation(Operation.ReadCharacter);
            break;
        case None:
            instruction.setNumber(0);
            break;
        case Giyeok:
        case Nieun:
        case Siot:
            instruction.setNumber(2);
            break;
        case Digeut:
        case Jieut:
        case Kieuk:
            instruction.setNumber(3);
            break;
        case Mieum:
        case Bieup:
        case Chieut:
        case Tieut:
        case Pieup:
        case SsangGiyeok:
        case GiyeokSiot:
        case SsangSiot:
            instruction.setNumber(4);
            break;
        case Rieul:
        case NieunJieut:
        case NieunHieut:
            instruction.setNumber(5);
            break;
        case BieupSiot:
            instruction.setNumber(6);
            break;
        case RieulGiyeok:
        case RieulSiot:
            instruction.setNumber(7);
            break;
        case RieulHieut:
            instruction.setNumber(8);
            break;
        case RieulMieum:
        case RieulBieup:
        case RieulTieut:
        case RieulPieup:
            instruction.setNumber(9);
            break;
        default:
            throw new IllegalArgumentException();
        }
        return instruction;
    }

    @Override
    public Instruction visitSelect(Instruction instruction) {
        Storage storage = convertToStorage(instruction.getSyllable().getFinalJamo());
        instruction.setStorage(storage);
        return instruction;
    }

    @Override
    public Instruction visitTransfer(Instruction instruction) {
        Storage storage = convertToStorage(instruction.getSyllable().getFinalJamo());
        instruction.setStorage(storage);
        return instruction;
    }

    private static Storage convertToStorage(FinalJamo jamo) {
        return Storage.values()[jamo.ordinal()];
    }

}
