package com.aha.aheui.parser;

import com.aha.aheui.ast.Program;
import com.aha.aheui.ast.Instruction;
import com.aha.util.Matrix;
import com.aha.util.Tuple;

public class AheuiParser {

    public Tuple<Matrix<Instruction>, Program> parse(String source) {
        Lexer lexer = new Lexer();
        Matrix<Instruction> instructionTable = lexer.getInstructions(source);
        instructionTable.apply(new ParseParameters());
        instructionTable.apply(new ParseDirection());
        CodeBlockGenerator blockGenerator = new CodeBlockGenerator();
        Program program = blockGenerator.createProgram(instructionTable);
        program.mergeBlocks();
        
        return new Tuple<Matrix<Instruction>, Program>(instructionTable, program);
    }
    
}
