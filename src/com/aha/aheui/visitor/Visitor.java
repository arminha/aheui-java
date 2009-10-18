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
