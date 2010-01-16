package com.aha.aheui.parser;

import com.aha.aheui.ast.DirectionModifier;
import com.aha.aheui.ast.Instruction;
import com.aha.util.Method;

class ParseDirection implements Method<Instruction> {

    private ErrorLevel invalidDirection = ErrorLevel.Ignore;
    
    @Override
    public void apply(Instruction instruction) {
        switch (instruction.getSyllable().getMedialJamo()) {
        case A:
            instruction.setDirectionModifier(DirectionModifier.Rigth);
            break;
        case Eo:
            instruction.setDirectionModifier(DirectionModifier.Left);
            break;
        case U:
            instruction.setDirectionModifier(DirectionModifier.Down);
            break;
        case O:
            instruction.setDirectionModifier(DirectionModifier.Up);
            break;
        case Ya:
            instruction.setDirectionModifier(DirectionModifier.DoubleRigth);
            break;
        case Yeo:
            instruction.setDirectionModifier(DirectionModifier.DoubleLeft);
            break;
        case Yu:
            instruction.setDirectionModifier(DirectionModifier.DoubleDown);
            break;
        case Yo:
            instruction.setDirectionModifier(DirectionModifier.DoubleUp);
            break;
        case Eu:
            instruction.setDirectionModifier(DirectionModifier.MirrorVertical);
            break;
        case I:
            instruction.setDirectionModifier(DirectionModifier.MirrorHorizontal);
            break;
        case Ui:
            instruction.setDirectionModifier(DirectionModifier.MirrorBoth);
            break;
        default:
            ErrorUtil.error(instruction, invalidDirection, "invalid direction modifier");
            break;
        }
    }
}
