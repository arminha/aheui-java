package com.aha.aheui.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.aha.aheui.ast.CodeBlock;
import com.aha.aheui.ast.Program;
import com.aha.aheui.ast.Instruction;
import com.aha.aheui.ast.Storage;
import com.aha.aheui.visitor.AbstractVisitor;
import com.aha.util.NotImplementedException;

public class AheuiInterpreter {

    private List<Deque<Integer>> storages;
    private Storage currentStorage;
    private PrintStream out;
    private InputStream in;
    private PrintStream err;
    
    private boolean alternateNext;
    
    protected void init() {
        storages = new ArrayList<Deque<Integer>>(Storage.values().length - 1);
        for (int i = 0; i < Storage.values().length -1; i++) {
            storages.add(new ArrayDeque<Integer>());
        }
        currentStorage = Storage.DefaultStack;
        try {
            out = new PrintStream(System.out, true, "UTF-8");
            err = new PrintStream(System.err, true, "UTF-8");
            in = System.in;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public void run(Program codeBlocks) {
        init();
        
        Executer executer = new Executer();
        CodeBlock codeBlock = codeBlocks.getStartBlock();
        boolean done = false;
        while (!done) {
            alternateNext = false;
            for (Instruction instruction : codeBlock.getInstructions()) {
                done = !executer.visit(instruction);
                if (done) {
                    break;
                }
            }
            if (codeBlock.getAlternativeNext() != null && alternateNext) {
                codeBlock = codeBlock.getAlternativeNext();
            } else if (codeBlock.getNext() != null) {
                codeBlock = codeBlock.getNext();
            } else {
                done = true;
            }
        }
    }
    
    protected int getValue() {
        if (currentStorage == Storage.Extension) {
            throw new NotImplementedException("No extension available");
        }
        return storages.get(currentStorage.ordinal()).removeFirst();
    }
    
    protected int peekValue() {
        if (currentStorage == Storage.Extension) {
            throw new NotImplementedException("No extension available");
        }
        return storages.get(currentStorage.ordinal()).peekFirst();
    }
    
    protected void setValue(int value) {
        setValue(currentStorage, value);
    }
    
    // TODO exception management
    private int readInt(InputStream in) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
        
            line = reader.readLine();
            boolean done = false;
            int value = 0;
            while (line != null && !done) {
                try {
                    value = Integer.parseInt(line);
                    done = true;
                } catch (NumberFormatException e) {
                    err.println("Could not parse number: " + line);
                    line = reader.readLine();
                }
            }
            if (!done) {
                throw new RuntimeException("Could not read integer. End of stream occured.");
            }
            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected void setValue(Storage storage, int value) {
        if (storage == Storage.Extension) {
            throw new NotImplementedException("No extension available");
        }
        Deque<Integer> deque = storages.get(storage.ordinal());
        if (storage.getType() == Storage.StorageType.Queue) {
            deque.addLast(value);
        } else {
            deque.addFirst(value);
        }
    }
    
    private class Executer extends AbstractVisitor<Boolean> {
        @Override
        public Boolean visitAdd(Instruction instruction) {
            setValue(getValue() + getValue());
            return true;
        }

        @Override
        public Boolean visitCompare(Instruction instruction) {
            int former = getValue();
            int later = getValue();
            setValue(later >= former ? 1 : 0);
            return true;
        }

        @Override
        public Boolean visitDecide(Instruction instruction) {
            alternateNext = getValue() == 0;
            return true;
        }

        @Override
        public Boolean visitDivide(Instruction instruction) {
            int denominator = getValue();
            int numerator = getValue();
            setValue(numerator / denominator);
            return true;
        }

        @Override
        public Boolean visitDuplicate(Instruction instruction) {
            setValue(peekValue());
            return true;
        }

        @Override
        public Boolean visitInvalidOperation(Instruction instruction) {
            System.err.println("Invalid operation: " + instruction);
            return false;
        }

        @Override
        public Boolean visitModulo(Instruction instruction) {
            int former = getValue();
            int later = getValue();
            setValue(later % former);
            return true;
        }

        @Override
        public Boolean visitMultiply(Instruction instruction) {
            setValue(getValue() * getValue());
            return true;
        }

        @Override
        public Boolean visitNull(Instruction instruction) {
            return true;
        }

        @Override
        public Boolean visitPop(Instruction instruction) {
            getValue();
            return true;
        }

        @Override
        public Boolean visitPrintCharacter(Instruction instruction) {
            int ord = getValue();
            out.print((char) ord);
            return true;
        }

        @Override
        public Boolean visitPrintInteger(Instruction instruction) {
            out.print(getValue());
            return true;
        }

        @Override
        public Boolean visitPush(Instruction instruction) {
            setValue(instruction.getNumber());
            return true;
        }

        @Override
        public Boolean visitReadCharacter(Instruction instruction) {
            // TODO Auto-generated method stub
            return super.visitReadCharacter(instruction);
        }

        @Override
        public Boolean visitReadInteger(Instruction instruction) {
            setValue(readInt(in));
            return true;
        }

        @Override
        public Boolean visitSelect(Instruction instruction) {
            currentStorage = instruction.getStorage();
            return true;
        }

        @Override
        public Boolean visitSubtract(Instruction instruction) {
            int subtrahend = getValue();
            int minuend = getValue();
            setValue(minuend - subtrahend);
            return true;
        }

        @Override
        public Boolean visitSwap(Instruction instruction) {
            // TODO behaviour in queue
            int first = getValue();
            int second = getValue();
            setValue(first);
            setValue(second);
            return true;
        }

        @Override
        public Boolean visitTerminate(Instruction instruction) {
            return false;
        }

        @Override
        public Boolean visitTransfer(Instruction instruction) {
            setValue(instruction.getStorage(), getValue());
            return true;
        }
    }
}
