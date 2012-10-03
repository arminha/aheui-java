package com.aha.aheui.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class InputStreamInput implements ProgramInput {

    private InputStream in;
    private PrintStream err;
    
        
    public InputStreamInput(InputStream in, PrintStream err) {
        this.in = in;
        this.err = err;
    }

    @Override
    public char readCharacter() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;

            line = reader.readLine();
            if (line != null && line.length() > 0) {
                return line.charAt(0);
            } else {
                return '\n';
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int readInteger() {
        return readInt(in, err);
    }
    
    private int readInt(InputStream in, PrintStream err) {
        // TODO better exception management
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

}
