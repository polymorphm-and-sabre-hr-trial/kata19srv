package com.sabrehrtrial.kata19.pathfinding;

import java.util.HashMap;

/**
 * a structure for keeping all word's nodes with access to anyone by index.
 */
public class WordNodeIndex {
    
    private final DistanceEvaluator distEval;
    private final HashMap<String, WordNode> allNodeMap;
    private final HashMap<Integer, HashMap<String, WordNode>> nodeMapBuckets;
    
    /**
     * create empty index structure.
     * 
     * @param distEval reference to function for evaluating a distance.
     */
    public WordNodeIndex(DistanceEvaluator distEval) {
        if (distEval == null) {
            throw new NullPointerException("distEval is null");
        }
        
        this.distEval = distEval;
        
        allNodeMap = new HashMap<>();
        nodeMapBuckets = new HashMap<>();
    }
    
    /**
     * add a word's node to the index.
     * 
     * @param node a word's node for adding to the index.
     */
    public void addWordNode(WordNode node) {
        if (node == null) {
            throw new NullPointerException("node is null");
        }
        
        
        
        // XXX    it is WRONG (incomplete) implementation
        allNodeMap.put(node.getWord(), node);
        
        
    }
    
    /**
     * add a word to the index.
     * 
     * @param word a word for adding to the index.
     */
    public void addWord(String word) {
        WordNode node = new WordNode(word);
        
        addWordNode(node);
    }
    
    /**
     * find a word's node by word.
     * 
     * @param word word to look for.
     * @return a found word's node or null.
     */
    public WordNode findNodeByWord(String word) {
        return allNodeMap.get(word);
    }
    
    /**
     * get size of the index.
     * 
     * @return count of word's nodes in the index.
     */
    public int size() {
        return allNodeMap.size();
    }
    
}
