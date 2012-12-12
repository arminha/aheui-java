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
import com.aha.util.NotImplementedException;

public abstract class AbstractVisitor<T> implements Visitor<T> {

    @Override
    public T visit(Instruction instruction) {
        if (instruction.getOperation() == null) {
            throw new IllegalArgumentException(
                    "operation is not set for the instruction.");
        }
        switch (instruction.getOperation()) {
        case Add:
            return visitAdd(instruction);
        case Compare:
            return visitCompare(instruction);
        case Decide:
            return visitDecide(instruction);
        case Divide:
            return visitDivide(instruction);
        case Duplicate:
            return visitDuplicate(instruction);
        case Modulo:
            return visitModulo(instruction);
        case Multiply:
            return visitMultiply(instruction);
        case Null:
            return visitNull(instruction);
        case Pop:
            return visitPop(instruction);
        case PrintCharacter:
            return visitPrintCharacter(instruction);
        case PrintInteger:
            return visitPrintInteger(instruction);
        case Push:
            return visitPush(instruction);
        case ReadCharacter:
            return visitReadCharacter(instruction);
        case ReadInteger:
            return visitReadInteger(instruction);
        case Select:
            return visitSelect(instruction);
        case Subtract:
            return visitSubtract(instruction);
        case Swap:
            return visitSwap(instruction);
        case Terminate:
            return visitTerminate(instruction);
        case Transfer:
            return visitTransfer(instruction);
        case InvalidOperation:
            return visitInvalidOperation(instruction);
        default:
            throw new IllegalArgumentException("unknown operation");
        }
    }

    @Override
    public T visitAdd(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitCompare(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitDecide(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitDivide(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitDuplicate(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitModulo(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitMultiply(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitNull(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitPop(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitPrintCharacter(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitPrintInteger(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitPush(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitReadCharacter(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitReadInteger(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitSelect(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitSubtract(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitSwap(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitTerminate(Instruction instruction) {
        return visitDefault(instruction);
    }

    @Override
    public T visitTransfer(Instruction instruction) {
        return visitDefault(instruction);
    }
    
    @Override
    public T visitInvalidOperation(Instruction instruction) {
        return visitDefault(instruction);
    }
    
    protected T visitDefault(Instruction instruction) {
        throw new NotImplementedException();
    }

}
