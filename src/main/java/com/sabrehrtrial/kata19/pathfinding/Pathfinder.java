package com.sabrehrtrial.kata19.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * a high level class for finding words path in a prepared index.
 */
public class Pathfinder {
    
    private final WordNodeIndex index;
    private final WaveAlgorithm waveAlgo;
    
    /**
     * create an instance ready to do finding.
     * 
     * @param index index with connected word's nodes.
     */
    public Pathfinder(WordNodeIndex index) {
        if (index == null) {
            throw new NullPointerException("index is null");
        }
        
        this.index = index;
        
        waveAlgo = new WaveAlgorithm();
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
        if (word1 == null) {
            throw new NullPointerException("word1 is null");
        }
        if (word2 == null) {
            throw new NullPointerException("word2 is null");
        }
        
        // firstly, we are checking special cases
        
        if (word1.equals(word2)) {
            // case: the same word
            
            return Collections.emptyList();
        }
        
        WordNode word1Node = index.findNodeByWord(word1);
        WordNode word2Node = index.findNodeByWord(word2);
        
        if (word1Node == null || word2Node == null) {
            // case: no in the index
            
            return null;
        }
        
        if (word1Node.getNeighNodes().contains(word2Node)) {
            // case: direct connected
            
            return Collections.emptyList();
        }
        
        // secondly, we are finding a path in general cases
        
        HashMap<WordNode, WaveSpreadNodeInfo> waveSpreadInfo = new HashMap<>();
        
        int algoSizeLimit = sizeLimit;
        
        if (algoSizeLimit != -1) {
            // wave algorithm uses limit with two sides.
            // so we have to decrease it in two times
            
            algoSizeLimit = (int) Math.ceil(
                    ((double) sizeLimit) / 2.0
            );
        }
        
        WordNode middleNode = waveAlgo.spreadWaves(
                word1Node, word2Node,
                waveSpreadInfo, index, algoSizeLimit
        );
        
        if (middleNode == null) {
            // the path can not be found
            
            return null;
        }
        
        List<WordNode> nodePath = waveAlgo.recoveryPath(
                word1Node, word2Node, middleNode, waveSpreadInfo);
        
        // we've found something, and now we need to translate nodes to words
        
        ArrayList<String> path = new ArrayList<>();
        
        nodePath.stream().map(WordNode::getWord).forEach((word) -> {
            path.add(word);
        });
        
        return path;
    }
    
}
