package com.sabrehrtrial.kata19.pathfinding;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WaveAlgorithmTest {
    
    private WaveAlgorithm waveAlgo;
    private HashMap<WordNode, WaveSpreadNodeInfo> waveSpreadInfo;
    private DistanceEvaluator distEvalMock;
    private WordNodeIndex index;
    private WordNode node1;
    private WordNode node2;
    private WordNode node3;
    private WordNode node4;
    private WordNode node5;
    private WordNode node6;
    private WordNode node7;
    
    @Before
    public void setup() {
        waveAlgo = new WaveAlgorithm();
        waveSpreadInfo = new HashMap<>();
        
        distEvalMock = new DistanceEvaluatorSixWordsMock();
        index = new WordNodeIndex(distEvalMock);
        
        index.addWordNode(node1 = new WordNode("ruby"));
        index.addWordNode(node2 = new WordNode("rubs"));
        index.addWordNode(node3 = new WordNode("robs"));
        index.addWordNode(node4 = new WordNode("rods"));
        index.addWordNode(node5 = new WordNode("rode"));
        index.addWordNode(node6 = new WordNode("code"));
        index.addWordNode(node7 = new WordNode("unix"));
        
        // the "unix" node is in the index but has no any connections
    }
    
    @Test
    public void shouldFindMiddleNodeIfItCould() {
        WordNode middleNode = waveAlgo.spreadWaves(
                node2, node6,
                waveSpreadInfo, index, 10
        );
        
        Assert.assertSame(node3, middleNode);
    }
    
    @Test
    public void shouldReturnNullIfSpreadingHadNoInterfering() {
        Assert.assertNull(waveAlgo.spreadWaves(
                node1, node7,
                waveSpreadInfo, index, 10
        ));
    }
    
    @Test
    public void shouldReturnNullIfSpreadingWasNotEnough() {
        Assert.assertNull(waveAlgo.spreadWaves(
                node1, node6,
                waveSpreadInfo, index, 1
        ));
    }
    
    @Test
    public void shouldFindMiddleNodeIfItCouldAndSpreadingWasUnlimited() {
        WordNode middleNode = waveAlgo.spreadWaves(
                node2, node6,
                waveSpreadInfo, index, -1
        );
        
        Assert.assertSame(node3, middleNode);
    }
    
    @Test
    public void shouldReturnNullIfUnlimitedSpreadingHadNoInterfering() {
        Assert.assertNull(waveAlgo.spreadWaves(
                node1, node7,
                waveSpreadInfo, index, -1
        ));
    }
    
    @Test
    public void recoveryPathShouldBeCorrect() {
        WordNode middleNode = waveAlgo.spreadWaves(
                node1, node6,
                waveSpreadInfo, index, 10
        );
        
        List<WordNode> path = waveAlgo.recoveryPath(
                node1, node6, middleNode, waveSpreadInfo);
        
        Assert.assertArrayEquals(
                new String[]{"rubs", "robs", "rods", "rode"},
                path.stream().map(WordNode::getWord).toArray()
        );
        
        // and I don't know what is in middleNode either "robs" or "rods"
    }
    
}
