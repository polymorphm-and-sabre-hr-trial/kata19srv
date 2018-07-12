package com.sabrehrtrial.kata19.assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

/**
 * get builtin word list.
 */
public class BuiltinWordList {
    
    private static final String RESOURCE_NAME = "wordlist.utf-8.txt";
    private static final String RESOURCE_ENC = "utf-8";
    
    public static void fetch(Consumer<? super String> action) {
        try (
                InputStream is = BuiltinWordList.class.getResourceAsStream(RESOURCE_NAME);
                InputStreamReader isr = new InputStreamReader(is, RESOURCE_ENC);
                BufferedReader buffer = new BufferedReader(isr);
                ) {
            buffer.lines().forEach(action);            
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }
    
}
