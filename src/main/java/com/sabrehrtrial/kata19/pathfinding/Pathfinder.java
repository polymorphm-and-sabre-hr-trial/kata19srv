package com.sabrehrtrial.kata19.pathfinding;

import java.util.List;

/**
 * a high level class for finding words path in a prepared index.
 */
public class Pathfinder {
    
    /**
     * create an instance ready to do finding.
     * 
     * @param index index with connected word's nodes.
     */
    public Pathfinder(WordNodeIndex index) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * find a path from one word to another word.
     * 
     * @param word1 one word.
     * @param word2 another word.
     * @param sizeLimit don't find paths that size exceeds this value.
     * -1 value means the path size is unlimited.
     * @return return a path. the path doesn't contain passed words,
     * it has only elements between them, i.e., empty list will be returned if
     * the words direct connected. null value will be returned if the path
     * can not be found, considering the path size limit.
     */
    public List<String> findPath(String word1, String word2, int sizeLimit) {
        throw new UnsupportedOperationException();
    }
    
}
