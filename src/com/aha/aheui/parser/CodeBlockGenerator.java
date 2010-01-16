package com.aha.aheui.parser;

import com.aha.aheui.ast.CodeBlock;
import com.aha.aheui.ast.CodeBlocks;
import com.aha.aheui.ast.Instruction;
import com.aha.aheui.visitor.AbstractVisitor;
import com.aha.util.Matrix;
import com.aha.util.NotImplementedException;

// TODO encapsulate the visitor into an inner class
class CodeBlockGenerator extends AbstractVisitor<CodeBlock> {

    private ErrorLevel reachableInvalidInstruction = ErrorLevel.Error;
    private ErrorLevel reachableUndefinedInstruction = ErrorLevel.Error;
    
    // fields of the visitor
    private Matrix<Instruction> instructions;
    private CodeBlocks codeBlocks;
    private Instruction previousInstruction;
    
    public CodeBlocks createCodeBlocks(Matrix<Instruction> instructions) {
        codeBlocks = new CodeBlocks();
        this.instructions = instructions;
        codeBlocks.setStartBlock(visit(instructions.get(0, 0)));
        return codeBlocks;
    }
    
    @Override
    protected CodeBlock visitDefault(Instruction instruction) {
        // check if codeblock for this instruction is already available
        CodeBlock codeBlock = getCodeBlockForInstruction(instruction);
        if (codeBlock != null) {
            return codeBlock;
        }
        
        // create codeblock and insert
        if (isPreviousNeeded(instruction)) {
            codeBlock = new CodeBlock(instruction, previousInstruction);
        } else {
            codeBlock = new CodeBlock(instruction);
        }
        codeBlocks.add(codeBlock);
        
        // visit next instruction
        Instruction nextInstruction = getNextInstruction(instruction, false);
        previousInstruction = instruction;
        CodeBlock nextBlock = visit(nextInstruction);
        codeBlock.setNext(nextBlock);
        return codeBlock;
    }
    
    @Override
    public CodeBlock visitDecide(Instruction instruction) {
        // check if codeblock for this instruction is already available
        CodeBlock codeBlock = getCodeBlockForInstruction(instruction);
        if (codeBlock != null) {
            return codeBlock;
        }
        
        // create codeblock and insert
        if (isPreviousNeeded(instruction)) {
            codeBlock = new CodeBlock(instruction, previousInstruction);
        } else {
            codeBlock = new CodeBlock(instruction);
        }
        codeBlocks.add(codeBlock);
        
        // visit next instruction
        Instruction nextInstruction = getNextInstruction(instruction, false);
        previousInstruction = instruction;
        CodeBlock nextBlock = visit(nextInstruction);
        codeBlock.setNext(nextBlock);
        
        // visit alternative next instruction
        Instruction altNextInstruction = getNextInstruction(instruction, true);
        previousInstruction = instruction;
        CodeBlock altnextBlock = visit(altNextInstruction);
        codeBlock.setAlternativeNext(altnextBlock);
        return codeBlock;
    }
    
    @Override
    public CodeBlock visitInvalidOperation(Instruction instruction) {
        ErrorUtil.error(instruction, reachableInvalidInstruction, "reachable invalid instruction");
        CodeBlock codeBlock = getCodeBlockForInstruction(instruction);
        if (codeBlock == null) {
            codeBlock = new CodeBlock(instruction);
            codeBlocks.add(codeBlock);
        }
        return codeBlock;
    }
    
    @Override
    public CodeBlock visitTerminate(Instruction instruction) {
        CodeBlock codeBlock = getCodeBlockForInstruction(instruction);
        if (codeBlock == null) {
            codeBlock = new CodeBlock(instruction);
            codeBlocks.add(codeBlock);
        }
        return codeBlock;
    }
    
    private Instruction getNextInstruction(Instruction instruction, boolean mirror) {
        int columnDiff = 0;
        int lineDiff = 0;
        if (instruction.getDirectionModifier().isFixed()) {
            lineDiff = instruction.getDirectionModifier().getLineDiff();
            columnDiff = instruction.getDirectionModifier().getColumnDiff();
        } else {
            // use previous instruction
            columnDiff = instruction.getColumn() - previousInstruction.getColumn();
            lineDiff = instruction.getLine() - previousInstruction.getLine();
            switch (instruction.getDirectionModifier()) {
            case None:
                break;
            case MirrorHorizontal:
                columnDiff = - columnDiff;
                break;
            case MirrorVertical:
                lineDiff = -lineDiff;
                break;
            case MirrorBoth:
                columnDiff = - columnDiff;
                lineDiff = -lineDiff;
            default:
                throw new IllegalArgumentException();
            }
        }
        
        if (mirror) {
            if (lineDiff == 0) {
                columnDiff = -columnDiff;
            } else {
                lineDiff = -lineDiff;
            }
        }
        
        Instruction next = instructions.get(instruction.getLine() + lineDiff,
                instruction.getColumn() + columnDiff);
        if (next == null) {
            ErrorUtil.error(instruction, reachableUndefinedInstruction, "instruction points to undefined instruction outside of the code matrix.");
            // TODO
            throw new NotImplementedException();
        }
        return next;
    }
    
    private CodeBlock getCodeBlockForInstruction(Instruction instruction) {
        if (isPreviousNeeded(instruction)) {
            return codeBlocks.get(instruction, previousInstruction);
        }
        return codeBlocks.get(instruction);
    }

    private boolean isPreviousNeeded(Instruction instruction) {
        return !instruction.getDirectionModifier().isFixed();
    }
}
