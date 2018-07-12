package com.sabrehrtrial.kata19.pathfinding;

import java.util.HashMap;
import java.util.List;

/**
 * stateless class implementing two phases algorithm for finding a path.
 * the first phase is spreading two waves from two points of an index,
 * expecting crossing these waves in the third point. the second phase is
 * recovering a path using these three points and waves information.
 */
public class WaveAlgorithm {
    
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
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }
}
