package com.sabrehrtrial.kata19.pathfinding;

/**
 * a structure for keeping wave spread information about one word's node.
 */
public class WaveSpreadNodeInfo {
    
    private final WordNode node;
    
    private WaveName waveName;
    private int waveGen;
    
    /**
     * create a structure with empty wave spread information.
     * 
     * @param node a node related with wave spread information.
     */
    public WaveSpreadNodeInfo(WordNode node) {
        if (node == null) {
            throw new NullPointerException("node is null");
        }
        
        this.node = node;
    }
    
    /**
     * get a node related with the wave spread information.
     * 
     * @return a node related with the wave spread information.
     */
    public WordNode getNode() {
        return node;
    }
    
    /**
     * set a name of the wave.
     * 
     * @param waveName a name of the wave.
     */
    public void setWaveName(WaveName waveName) {
        this.waveName = waveName;
    }
    
    /**
     * get a name of the wave.
     * 
     * @return a name of the wave.
     */
    public WaveName getWaveName() {
        return waveName;
    }
    
    /**
     * set a generation of the wave.
     * 
     * @param waveGen a generation of the wave.
     */
    public void setWaveGen(int waveGen) {
        this.waveGen = waveGen;
    }
    
    /**
     * get a generation of the wave.
     * 
     * @return a generation of the wave.
     */
    public int getWaveGen() {
        return waveGen;
    }
}
