package com.sabrehrtrial.kata19.pathfinding;

import java.util.ArrayList;
import java.util.List;

/**
 * a word's node structure. a basic element for word path indexing.
 * each node keeps connections to nodes which have appropriate distance
 * (e.g. hamming distance 1) to this node.
 */
public class WordNode {
    
    private final String word;
    private final ArrayList<WordNode> neighNodes;
    
    /**
     * create a node without any connections.
     * 
     * @param word the word
     */
    public WordNode(String word) {
        if (word == null) {
            throw new NullPointerException("word is null");
        }
        
        this.word = word;
        neighNodes = new ArrayList<>();
    }
    
    /**
     * get the word of the node.
     * 
     * @return the word of the node. can not be null.
     */
    public String getWord() {
        return word;
    }
    
    /**
     * get list of neighbour nodes.
     * 
     * @return list of neighbours. can not be null.
     */
    public List<WordNode> getNeighNodes() {
        return neighNodes;
    }
    
    /**
     * connect two nodes between each other
     * 
     * @param node1 one node.
     * @param node2 another node.
     */
    public static void connectNodes(WordNode node1, WordNode node2) {
        // we don't check that nodes can be already connected,
        // because this checking requirement is not in the class contract
        
        node1.neighNodes.add(node2);
        node2.neighNodes.add(node1);
    }
    
}
