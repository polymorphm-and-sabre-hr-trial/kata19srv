package com.sabrehrtrial.kata19.pathfinding;

/**
 * a structure for keeping all word's nodes with access to anyone by index.
 */
public class WordNodeIndex {
    
    /**
     * create empty index structure.
     * 
     * @param distEval reference to function for evaluating a distance.
     */
    public WordNodeIndex(DistanceEvaluator distEval) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * add a word's node to the index.
     * 
     * @param node a word's node for adding to the index
     */
    public void addWordNode(WordNode node) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * add a word to the index.
     * 
     * @param word a word for adding to the index 
     */
    public void addWord(String word) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * find a word's node by word.
     * 
     * @param word word to look for.
     * @return a found word's node or null.
     */
    public WordNode findNodeByWord(String word) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * get size of the index.
     * 
     * @return count of word's nodes in the index.
     */
    public int size() {
        throw new UnsupportedOperationException();
    }
    
}
