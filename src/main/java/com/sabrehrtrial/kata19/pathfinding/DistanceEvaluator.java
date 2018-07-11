package com.sabrehrtrial.kata19.pathfinding;

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
    
}
