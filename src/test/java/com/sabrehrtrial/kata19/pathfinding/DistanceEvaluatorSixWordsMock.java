package com.sabrehrtrial.kata19.pathfinding;

/**
 * Mock version of <code>DistanceEvaluator</code> works only with 6 fixed words.
 */
public class DistanceEvaluatorSixWordsMock
        implements DistanceEvaluator {
    
    @Override
    public boolean evaluateDistance(String word1, String word2) {
        return "ruby".equals(word1) && "rubs".equals(word2)
                || "rubs".equals(word1) && "robs".equals(word2)
                || "robs".equals(word1) && "rods".equals(word2)
                || "rods".equals(word1) && "rode".equals(word2)
                || "rode".equals(word1) && "code".equals(word2)
                || "ruby".equals(word2) && "rubs".equals(word1)
                || "rubs".equals(word2) && "robs".equals(word1)
                || "robs".equals(word2) && "rods".equals(word1)
                || "rods".equals(word2) && "rode".equals(word1)
                || "rode".equals(word2) && "code".equals(word1);
    }
    
}
