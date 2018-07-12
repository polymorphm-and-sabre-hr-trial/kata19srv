package com.sabrehrtrial.kata19.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * stateless class implementing two phases algorithm for finding a path.
 * the first phase is spreading two waves from two points of an index,
 * expecting crossing these waves in the third point. the second phase is
 * recovering a path using these three points and waves information.
 */
public class WaveAlgorithm {
    
    /**
     * enqueue word nodes to movement queue.
     * 
     * @param movQueue a movement queue.
     * @param gen a generation number for nodes spread info prototypes.
     * @param nodes neighbours to enqueue.
     */
    private void enqueueNodesToMovQueue(
            LinkedList<WaveSpreadNodeInfo> movQueue,
            int gen,
            List<WordNode> nodes) {
        nodes.forEach((node) -> {
            WaveSpreadNodeInfo spreadProto = new WaveSpreadNodeInfo(node);
            
            spreadProto.setWaveGen(gen);
            
            movQueue.add(spreadProto);
        });
    }
    
    /**
     * do one spread iteration.
     *
     * @param movQueue our movement queue.
     * @param ourNode our node.
     * @param oppositeNode the other (opposite) node.
     * @param ourWaveName out wave name.
     * @param oppositeWaveName the other (opposite) wave name.
     * @param waveSpreadInfo map for keeping there wave spread information about
     * used word's nodes.
     * @param sizeLimit don't spread a wave that size exceeds this value. -1
     * value means the wave spreading is unlimited.
     * @return the point related to interfered wave if there was the
     * interfering. would be returned null if there no was the interfering.
     */
    private WordNode spreadIteration(
            LinkedList<WaveSpreadNodeInfo> movQueue,
            WordNode ourNode,
            WordNode oppositeNode,
            WaveName ourWaveName,
            WaveName oppositeWaveName,
            HashMap<WordNode, WaveSpreadNodeInfo> waveSpreadInfo,
            int sizeLimit
    ) {
        WaveSpreadNodeInfo spreadProto = movQueue.pop();
        
        WordNode node = spreadProto.getNode();
        int waveGen = spreadProto.getWaveGen();
        
        if (node == ourNode || node == oppositeNode
                || sizeLimit != -1 && waveGen > sizeLimit) {
            // we don't want to process these nodes
            
            return null;
        }
        
        WaveSpreadNodeInfo spread = waveSpreadInfo.get(node);
        
        if (spread == null) {
            spread = new WaveSpreadNodeInfo(node);
            
            spread.setWaveName(ourWaveName);
            spread.setWaveGen(waveGen);
            
            waveSpreadInfo.put(node, spread);
            
            List<WordNode> neighNodes = node.getNeighNodes();
            int neighWaveGen = waveGen + 1;
            
            for (WordNode neighNode : neighNodes) {
                if (neighNode == oppositeNode) {
                    // it is not wave interfering,
                    // but the opposite node is so close.
                    // another chance won't be
                    
                    spread.setWaveName(WaveName.interfWave);
                    
                    return node;
                }
            }
            
            if (sizeLimit == -1 || neighWaveGen <= sizeLimit) {
                enqueueNodesToMovQueue(movQueue, neighWaveGen, neighNodes);
            }
        } else if (spread.getWaveName() == oppositeWaveName) {
            // we have reached wave interfering!
            
            spread.setWaveName(WaveName.interfWave);
            
            return node;
        }
        
        return null;
    }
    
    /**
     * the first phase. to spread two waves from two points of an index,
     * expecting crossing these waves in the third point.
     * 
     * @param sourceNode the first point.
     * @param targetNode the second point.
     * @param waveSpreadInfo pre-created empty map for keeping there wave spread
     * information about all used word's nodes. this map will be filled after
     * working the function.
     * @param index index to connected word's nodes.
     * @param sizeLimit don't spread a wave that size exceeds this value.
     * -1 value means the wave spreading is unlimited.
     * @return would be returned the third point, i.e., the point related to
     * interfered wave if there was the interfering. would be returned null if
     * there no was the interfering, considering the path size limit.
     */
    public WordNode spreadWaves(
            WordNode sourceNode,
            WordNode targetNode,
            HashMap<WordNode, WaveSpreadNodeInfo> waveSpreadInfo,
            WordNodeIndex index,
            int sizeLimit
    ) {
        // planning next movements will be kept here
        LinkedList<WaveSpreadNodeInfo> movSrcQueue = new LinkedList<>();
        LinkedList<WaveSpreadNodeInfo> movTrgQueue = new LinkedList<>();
        
        // bootstrap
        
        enqueueNodesToMovQueue(movSrcQueue, 1, sourceNode.getNeighNodes());
        enqueueNodesToMovQueue(movTrgQueue, 1, targetNode.getNeighNodes());
        
        // spreading waves cycle
        
        while (!movSrcQueue.isEmpty() && !movTrgQueue.isEmpty()) {
            // source wave spreading
            
            WordNode interfNode = spreadIteration(
                    movSrcQueue,
                    sourceNode, targetNode,
                    WaveName.sourceWave, WaveName.targetWave,
                    waveSpreadInfo, sizeLimit);
            
            if (interfNode != null) {
                return interfNode;
            }
            
            // target wave spreading
            
            interfNode = spreadIteration(
                    movTrgQueue,
                    targetNode, sourceNode,
                    WaveName.targetWave, WaveName.sourceWave,
                    waveSpreadInfo, sizeLimit);
            
            if (interfNode != null) {
                return interfNode;
            }
        }
        
        return null;
    }
    
    /**
     * to recovery a path going by only one wave.
     * 
     * @param centerNode a point that is in the centre of the spread wave.
     * @param borderNode a point that is on the border of the spread wave.
     * @param waveName recovering a path process will be limited using this
     * wave only.
     * @param waveSpreadInfo filled map with wave spread information about
     * all used word's nodes.
     * @return a path between centerNode and borderNode. can not be null.
     */
    private List<WordNode> recoveryWavePath(
            WordNode centerNode,
            WordNode borderNode,
            WaveName waveName,
            HashMap<WordNode, WaveSpreadNodeInfo> waveSpreadInfo
    ) {
        ArrayList<WordNode> path = new ArrayList<>();
        
        // we will go from the border to the centre of wave
        
        WordNode candidateNode = borderNode;
        int waveGen = -1;
        
        for (;;) {
            List<WordNode> neighNodes = candidateNode.getNeighNodes();
            
            // a cycle iteration will be last if candidateNode remains null
            candidateNode = null;
            
            for (WordNode neighNode : neighNodes) {
                if (neighNode == centerNode) {
                    // we've reached the centre of wave
                    
                    return path;
                }
                
                WaveSpreadNodeInfo spread = waveSpreadInfo.get(neighNode);
        
                if (spread == null) {
                    continue;
                }
                
                int candidateWaveGen = spread.getWaveGen();
                
                if (spread.getWaveName() != waveName
                        || waveGen != -1 && candidateWaveGen >= waveGen) {
                    continue;
                }
                
                // we've found a good candidate to inserting into the path!
                
                candidateNode = neighNode;
                waveGen = candidateWaveGen;
                
                // but we won't put it into the path immediately,
                // because we can find better
            }
            
            if (candidateNode == null) {
                // there are no other nodes to path recovering
                
                return path;
            }
            
            path.add(candidateNode);
        }
    }
    
    /**
     * the second phase. to recovery a path using three points and waves
     * information.
     * 
     * @param sourceNode a source word's node.
     * @param targetNode a target word's node.
     * @param middleNode a middle word's node, got during spreading waves phase.
     * @param waveSpreadInfo filled map with wave spread information about
     * all used word's nodes.
     * @return a path between sourceNode and targetNode. the path includes
     * middleNode, but doesn't include sourceNode and targetNode. can not be null.
     */
    public List<WordNode> recoveryPath(
            WordNode sourceNode,
            WordNode targetNode,
            WordNode middleNode,
            HashMap<WordNode, WaveSpreadNodeInfo> waveSpreadInfo
    ) {
        List<WordNode> sourcePath = recoveryWavePath(
                sourceNode, middleNode, WaveName.sourceWave, waveSpreadInfo);
        List<WordNode> targetPath = recoveryWavePath(
                targetNode, middleNode, WaveName.targetWave, waveSpreadInfo);
        
        Collections.reverse(sourcePath);
        
        ArrayList<WordNode> path = new ArrayList<>();
        
        path.addAll(sourcePath);
        path.add(middleNode);
        path.addAll(targetPath);
        
        return path;
    }
    
}
