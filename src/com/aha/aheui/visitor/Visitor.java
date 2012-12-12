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

package com.aha.aheui.visitor;

import com.aha.aheui.ast.Instruction;

public interface Visitor<T> {

    T visit(Instruction instruction);

    T visitNull(Instruction instruction);

    T visitTerminate(Instruction instruction);

    T visitAdd(Instruction instruction);

    T visitMultiply(Instruction instruction);

    T visitDivide(Instruction instruction);

    T visitSubtract(Instruction instruction);

    T visitModulo(Instruction instruction);

    T visitPop(Instruction instruction);

    T visitPrintInteger(Instruction instruction);

    T visitPrintCharacter(Instruction instruction);

    T visitPush(Instruction instruction);

    T visitReadInteger(Instruction instruction);

    T visitReadCharacter(Instruction instruction);

    T visitDuplicate(Instruction instruction);

    T visitSwap(Instruction instruction);

    T visitSelect(Instruction instruction);

    T visitTransfer(Instruction instruction);

    T visitCompare(Instruction instruction);

    T visitDecide(Instruction instruction);
    
    T visitInvalidOperation(Instruction instruction);

}
