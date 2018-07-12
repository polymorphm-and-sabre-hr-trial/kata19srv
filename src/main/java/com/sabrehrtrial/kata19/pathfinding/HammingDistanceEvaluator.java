package com.sabrehrtrial.kata19.pathfinding;

import java.util.Collections;
import java.util.List;

/**
 * stateless class for implementing evaluating a distance by "Hamming Distance"
 * method.
 */
public class HammingDistanceEvaluator
        implements DistanceEvaluator {
    
    @Override
    public boolean evaluateDistance(String word1, String word2) {
        if (word1.length() != word2.length()) {
            // different words length makes "Hamming Distance" infinite
            
            return false;
        }
        
        char[] chr1 = word1.toCharArray();
        char[] chr2 = word2.toCharArray();
        int len = chr1.length;
        
        int dist = 0;
        
        for (int i = 0; i < len; ++i) {
            if (chr1[i] != chr2[i]) {
                ++dist;
                
                if (dist >= 2) {
                    // the distance begins from 2 is not appropriate
                    
                    return false;
                }
            }
        }
        
        return true;
    }
    
    @Override
    public List<Integer> calcBucket(String word) {
        return Collections.singletonList(word.length());
    }
    
}
