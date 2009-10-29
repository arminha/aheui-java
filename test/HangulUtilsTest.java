import java.io.PrintStream;

import com.aha.aheui.ast.CodeBlock;
import com.aha.aheui.ast.CodeBlocks;
import com.aha.aheui.ast.Instruction;
import com.aha.aheui.interpreter.AheuiInterpreter;
import com.aha.aheui.parser.AheuiParser;
import com.aha.hangul.FinalJamo;
import com.aha.hangul.HangulSyllable;
import com.aha.hangul.InitialJamo;
import com.aha.hangul.MedialJamo;
import com.aha.util.Matrix;
import com.aha.util.Tuple;

public class HangulUtilsTest {

    public final static String fibbonacciExample = "살뷸바우박우뭏뻐설멍뻐선썬퍼\n"
            + "삭방손삳볻타삭뿌박투빠쑨싿솓\n" + "도벌후멓설멍섣차볻타솓산도해\n";
    
    public final static String helloWorldExample = "밤밣따빠밣밟따뿌\n" + "빠맣파빨받밤뚜뭏\n"
            + "돋밬탕빠맣붏두붇\n" + "볻뫃박발뚷투뭏붖\n" + "뫃도뫃희멓뭏뭏붘\n" + "뫃봌토범더벌뿌뚜\n"
            + "뽑뽀멓멓더벓뻐뚠\n" + "뽀덩벐멓뻐덕더벅\n";

    public static void main(String[] args) throws Exception {
        //testParser();
        testHello();
    }
    
    public static void testHello() throws Exception {
        AheuiParser parser = new AheuiParser();
        Tuple<Matrix<Instruction>, CodeBlocks> result = parser.parse(fibbonacciExample);
        
        CodeBlocks codeBlocks = result.getSecond();
        AheuiInterpreter interpreter = new AheuiInterpreter();
        interpreter.run(codeBlocks);
    }
    
    public static void testParser() throws Exception {
        PrintStream out = new PrintStream(System.out, true, "UTF-8");

        HangulSyllable syllable = new HangulSyllable('민');
        out.println(syllable.getInitialJamo());
        out.println(syllable.getMedialJamo());
        out.println(syllable.getFinalJamo());
        out.println(syllable.character());
        
        HangulSyllable po = new HangulSyllable(InitialJamo.Pieup, MedialJamo.Eo, FinalJamo.None);
        out.println(po.character());
        out.println((int) po.character());
        out.println(HangulSyllable.isHangulSyllable(po.character()));

        AheuiParser parser = new AheuiParser();
        Tuple<Matrix<Instruction>, CodeBlocks> result = parser.parse(fibbonacciExample);
        Matrix<Instruction> instructions = result.getFirst();
        for (int j = 0; j < instructions.getRows(); j++) {
            out.println("Line " + j + ":");
            for (int i = 0; i < instructions.getColumns(); i++) {
                out.println(instructions.get(j, i));
            }
        }
        
        out.println();
        
        CodeBlocks codeBlocks = result.getSecond();
        
        out.println("Number of code blocks: " + codeBlocks.getCodeBlocks().size());
        for (CodeBlock codeBlock : codeBlocks.getCodeBlocks()) {
            out.println("Block " + codeBlock.getName());
            for (Instruction instruction : codeBlock.getInstructions()) {
                out.println("    " + instruction.toString());
            }
            if (codeBlock.getNext() != null) {
                out.println("  Next = " + codeBlock.getNext().getName());
            }
            if (codeBlock.getAlternativeNext() != null) {
                out.println("  AlternativeNext = " + codeBlock.getAlternativeNext().getName());
            }
        }
        
        out.println();
        
        out.flush();
    }

}
