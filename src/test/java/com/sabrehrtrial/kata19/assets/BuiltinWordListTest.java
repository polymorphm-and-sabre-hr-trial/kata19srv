package com.sabrehrtrial.kata19.assets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class BuiltinWordListTest {
    
    @Test
    public void lightTestOfFetching() {
        List<String> expectedWords = Arrays.asList(
                "deflator",
                "anthropometry",
                "scaring",
                "Unix",
                "Untouchable"
        );
        
        ArrayList<String> actualWords = new ArrayList<>();
        
        BuiltinWordList.fetch((line) -> {
            if (expectedWords.contains(line)) {
                actualWords.add(line);
            }
        });
        
        Collections.sort(expectedWords);
        Collections.sort(actualWords);
        
        Assert.assertArrayEquals(expectedWords.toArray(), actualWords.toArray());
    }
    
}
