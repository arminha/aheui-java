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

package com.aha.aheui.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Program {

    private final Map<String, CodeBlock> codeBlocks = new HashMap<String, CodeBlock>();
    private final Map<String, List<CodeBlock>> inverseReferences = new HashMap<String, List<CodeBlock>>();
    private CodeBlock startBlock;

    public CodeBlock get(Instruction instruction, Instruction previousInstruction) {
        String name = CodeBlock.getUniqueName(instruction, previousInstruction);
        return codeBlocks.get(name);
    }

    public CodeBlock get(Instruction instruction) {
        return get(instruction, null);
    }

    public void add(CodeBlock codeBlock) {
        if (codeBlocks.containsKey(codeBlock.getUniqueName())) {
            throw new IllegalArgumentException("Codeblock already registered.");
        }
        codeBlocks.put(codeBlock.getUniqueName(), codeBlock);
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
            inverseReferences.put(codeBlock.getUniqueName(), new ArrayList<CodeBlock>());
        }
        for (CodeBlock codeBlock : codeBlocks.values()) {
            if (codeBlock.getNext() != null) {
                inverseReferences.get(codeBlock.getNext().getUniqueName()).add(codeBlock);
            }
            if (codeBlock.getAlternativeNext() != null) {
                inverseReferences.get(codeBlock.getAlternativeNext().getUniqueName()).add(codeBlock);
            }
        }
    }

    public void mergeBlocks() {
        computeReferences();

        CodeBlock codeBlock = nextMergable();
        while (codeBlock != null) {
            CodeBlock next = codeBlock.getNext();
            codeBlocks.remove(next.getUniqueName());
            inverseReferences.remove(next.getUniqueName());

            // update inverse references
            if (next.getNext() != null) {
                List<CodeBlock> refs = inverseReferences.get(next.getNext().getUniqueName());
                refs.remove(next);
                refs.add(codeBlock);
            }
            if (next.getAlternativeNext() != null) {
                List<CodeBlock> refs = inverseReferences.get(next.getAlternativeNext().getUniqueName());
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
            if (!entry.getKey().equals(startBlock.getUniqueName()) && entry.getValue().size() == 1) {
                CodeBlock codeBlock = entry.getValue().get(0);
                if (codeBlock.getAlternativeNext() == null) {
                    return codeBlock;
                }
            }
        }
        return null;
    }
}
