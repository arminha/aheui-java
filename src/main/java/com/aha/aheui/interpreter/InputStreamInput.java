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
