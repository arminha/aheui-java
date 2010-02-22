package com.aha.util.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamHelper {

    public static String readAll(InputStream in, String encoding) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, encoding));
        StringBuilder result = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            result.append(line);
            line = reader.readLine();
        }
        return result.toString();
    }
    
}
