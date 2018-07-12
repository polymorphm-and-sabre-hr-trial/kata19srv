package com.sabrehrtrial.kata19.pathfinding;

import java.util.List;

/**
 * reference to function for evaluating a distance.
 */
public interface DistanceEvaluator {
    
    /**
     * evaluate a distance between two words.
     * 
     * @param word1 one word.
     * @param word2 another word.
     * @return true if distance is appropriative. otherwise false.
     */
    boolean evaluateDistance(String word1, String word2);
    
    /**
     * calculate bucket identifier values. these values can be used to prevent
     * brute-force operation over whole index during evaluating a distance.
     * 
     * @param word calculation is over this word.
     * @return one or a few bucket identifiers. null can be returned if
     * an interface implementation is not able to calculate buckets.
     */
    default List<Integer> calcBucket(String word) {
        return null;
    }
    
}
