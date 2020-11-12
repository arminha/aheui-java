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

package com.aha.util.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class StreamHelper {

    private StreamHelper() {
    }

    private static final int BOM_MARKER = 65279;

    public static String readAll(InputStream in, String encoding) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, encoding));
        StringBuilder result = new StringBuilder();
        String line = reader.readLine();
        // filter unicode BOM markers
        // see http://groups.google.com/group/comp.lang.java.programmer/browse_frm/thread/020adb9d6ad25412
        int firstChar = line.charAt(0);
        if (firstChar == BOM_MARKER) {
            line = line.substring(1);
        }
        while (line != null) {
            result.append(line);
            result.append("\n");
            line = reader.readLine();
        }
        return result.toString();
    }

}
