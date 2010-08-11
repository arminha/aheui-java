package com.aha.util.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamHelper {

    private static final int BOM_MARKER = 65279;

    public static String readAll(InputStream in, String encoding) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, encoding));
        StringBuilder result = new StringBuilder();
        String line = reader.readLine();
        // filter unicode BOM markers
        // see http://groups.google.com/group/comp.lang.java.programmer/browse_frm/thread/020adb9d6ad25412
        int firstChar = (int)line.charAt(0);
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
