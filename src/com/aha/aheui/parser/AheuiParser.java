package com.aha.aheui.parser;

import com.aha.aheui.ast.CodeBlocks;
import com.aha.aheui.ast.Instruction;
import com.aha.util.Matrix;
import com.aha.util.Tuple;

public class AheuiParser {

    public Tuple<Matrix<Instruction>, CodeBlocks> parse(String program) {
        Lexer lexer = new Lexer();
        Matrix<Instruction> instructionTable = lexer.getInstructions(program);
        instructionTable.apply(new ParseParameters());
        instructionTable.apply(new ParseDirection());
        CodeBlockGenerator blockGenerator = new CodeBlockGenerator();
        CodeBlocks codeBlocks = blockGenerator.createCodeBlocks(instructionTable);
        codeBlocks.mergeBlocks();
        
        return new Tuple<Matrix<Instruction>, CodeBlocks>(instructionTable, codeBlocks);
    }
    
}
