package com.sabrehrtrial.kata19.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
     * add a word and its node to the index.
     * 
     * @param word a word for adding to the index.
     * @param node a word's node for adding to the index.
     */
    private void addWordWithNode(String word, WordNode node) {
        if (allNodeMap.containsKey(word)) {
            return;
        }
        
        List<Integer> buckets = distEval.calcBucket(word);
        
        // we will look for only in these maps, instead of doing brute-force
        List<HashMap<String, WordNode>> lookingNodeMaps;
        
        if (buckets != null) {
            lookingNodeMaps = new ArrayList<>();
            
            buckets.forEach((bucket) -> {
                lookingNodeMaps.add(nodeMapBuckets.get(bucket));
            });
        } else {
            // in this case we have to look the entire node map
            
            lookingNodeMaps = Collections.singletonList(allNodeMap);
        }
        
        ArrayList<WordNode> toConnectList = new ArrayList<>();
        
        lookingNodeMaps.forEach((lookingNodeMap) -> {
            lookingNodeMap.forEach((oWord, oNode) -> {
                boolean isGood = distEval.evaluateDistance(word, oWord);
                
                if (isGood) {
                    toConnectList.add(oNode);
                }
            });
        });
        
        toConnectList.forEach((oNode) -> {
            WordNode.connectNodes(node, oNode);
        });
        
        // all connections have been made,
        // only left to add the node to the index store
        
        allNodeMap.put(word, node);
        
        lookingNodeMaps.stream().filter((lookingNodeMap)
                -> lookingNodeMap != allNodeMap
        ).forEach((lookingNodeMap) -> {
            lookingNodeMap.put(word, node);
        });
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
        
        String word = node.getWord();
        
        addWordWithNode(word, node);
    }
    
    /**
     * add a word to the index.
     * 
     * @param word a word for adding to the index.
     */
    public void addWord(String word) {
        WordNode node = new WordNode(word);
        
        addWordWithNode(word, node);
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
