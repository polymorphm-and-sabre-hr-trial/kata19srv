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
        this.word = word;
        neighNodes = new ArrayList<>();
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
        throw new UnsupportedOperationException();
    }
    
}
