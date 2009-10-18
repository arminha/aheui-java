package com.aha.aheui.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CodeBlocks {

    private final Map<String, CodeBlock> codeBlocks = new HashMap<String, CodeBlock>();
    private final Map<String, List<CodeBlock>> inverseReferences = new HashMap<String, List<CodeBlock>>();
    private CodeBlock startBlock;
    
    public CodeBlock get(Instruction instruction, Instruction previousInstruction) {
        String name = CodeBlock.getName(instruction, previousInstruction);
        return codeBlocks.get(name);
    }
    
    public CodeBlock get(Instruction instruction) {
        String name = CodeBlock.getName(instruction);
        return codeBlocks.get(name);
    }
    
    public void add(CodeBlock codeBlock) {
        if (codeBlocks.containsKey(codeBlock.getName())) {
            throw new IllegalArgumentException("Codeblock already registered.");
        }
        codeBlocks.put(codeBlock.getName(), codeBlock);
    }
    
    public Collection<CodeBlock> getCodeBlocks() {
        return Collections.unmodifiableCollection(codeBlocks.values());
    }
    
    public CodeBlock getStartBlock() {
        return startBlock;
    }
    
    public void setStartBlock(CodeBlock startBlock) {
        this.startBlock = startBlock;
    }
    
    private void computeReferences() {
        inverseReferences.clear();
        for (CodeBlock codeBlock : codeBlocks.values()) {
            inverseReferences.put(codeBlock.getName(), new ArrayList<CodeBlock>());
        }
        for (CodeBlock codeBlock : codeBlocks.values()) {
            if (codeBlock.getNext() != null) {
                inverseReferences.get(codeBlock.getNext().getName()).add(codeBlock);
            }
            if (codeBlock.getAlternativeNext() != null) {
                inverseReferences.get(codeBlock.getAlternativeNext().getName()).add(codeBlock);
            }
        }
    }
    
    public void mergeBlocks() {
        computeReferences();
        
        CodeBlock codeBlock = nextMergable();
        while (codeBlock != null) {
            CodeBlock next = codeBlock.getNext();
            codeBlocks.remove(next.getName());
            inverseReferences.remove(next.getName());
            
            // update inverse references
            if (next.getNext() != null) {
                List<CodeBlock> refs = inverseReferences.get(next.getNext().getName());
                refs.remove(next);
                refs.add(codeBlock);
            }
            if (next.getAlternativeNext() != null) {
                List<CodeBlock> refs = inverseReferences.get(next.getAlternativeNext().getName());
                refs.remove(next);
                refs.add(codeBlock);
            }
            // merge
            codeBlock.mergeWithNext();
            
            codeBlock = nextMergable();
        }
    }
    
    private CodeBlock nextMergable() {
        Iterator<Map.Entry<String, List<CodeBlock>>> refIt = inverseReferences.entrySet().iterator();
        while (refIt.hasNext()) {
            Map.Entry<String, List<CodeBlock>> entry = refIt.next();
            if (!entry.getKey().equals(startBlock.getName())
                    && entry.getValue().size() == 1) {
                CodeBlock codeBlock = entry.getValue().get(0);
                if (codeBlock.getAlternativeNext() == null) {
                    return codeBlock;
                }
            }
        }
        return null;
    }
}
