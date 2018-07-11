package com.sabrehrtrial.kata19.pathfinding;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WordNodeIndexTest {
    private DistanceEvaluator distEvalMock;
    private WordNodeIndex index;
    
    @Before
    public void setup() {
        distEvalMock = new DistanceEvaluatorSixWordsMock();
        index = new WordNodeIndex(distEvalMock);
    }
    
    @Test
    public void shouldBeEmptyByDefault() {
        Assert.assertEquals(0, index.size());
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptNullNode() {
        index.addWordNode(null);
        
        Assert.fail();
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptNullWord() {
        index.addWord(null);
        
        Assert.fail();
    }
    
    @Test
    public void onlyDifferentWordsIncreaseIndex() {
        index.addWord("ruby");
        int firstSize = index.size();
        
        index.addWord("code");
        int secondSize = index.size();
        
        index.addWord("code");
        index.addWord("ruby");
        
        Assert.assertTrue(secondSize > firstSize);
        Assert.assertEquals(secondSize, index.size());
    }
    
    @Test
    public void addingToIndexMakesNodesConnected() {
        WordNode firstNode = new WordNode("rubs");
        WordNode secondNode = new WordNode("robs");
        WordNode thirdNode = new WordNode("rods");
        
        Assert.assertFalse(firstNode.getNeighNodes().contains(secondNode));
        Assert.assertFalse(secondNode.getNeighNodes().contains(firstNode));
        Assert.assertFalse(secondNode.getNeighNodes().contains(thirdNode));
        Assert.assertFalse(thirdNode.getNeighNodes().contains(secondNode));
        Assert.assertFalse(thirdNode.getNeighNodes().contains(firstNode));
        Assert.assertFalse(firstNode.getNeighNodes().contains(thirdNode));
        
        index.addWordNode(firstNode);
        index.addWordNode(secondNode);
        index.addWordNode(thirdNode);
        
        Assert.assertTrue(firstNode.getNeighNodes().contains(secondNode));
        Assert.assertTrue(secondNode.getNeighNodes().contains(firstNode));
        Assert.assertTrue(secondNode.getNeighNodes().contains(thirdNode));
        Assert.assertTrue(thirdNode.getNeighNodes().contains(secondNode));
        Assert.assertFalse(thirdNode.getNeighNodes().contains(firstNode));
        Assert.assertFalse(firstNode.getNeighNodes().contains(thirdNode));
    }
    
    @Test
    public void shouldFindConnectedNodesAfterAddingWords() {
        index.addWord("rubs");
        index.addWord("robs");
        index.addWord("rods");
        
        WordNode firstNode = index.findNodeByWord("rubs");
        WordNode secondNode = index.findNodeByWord("robs");
        WordNode thirdNode = index.findNodeByWord("rods");
        
        Assert.assertTrue(firstNode.getNeighNodes().contains(secondNode));
        Assert.assertTrue(secondNode.getNeighNodes().contains(firstNode));
        Assert.assertTrue(secondNode.getNeighNodes().contains(thirdNode));
        Assert.assertTrue(thirdNode.getNeighNodes().contains(secondNode));
        Assert.assertFalse(thirdNode.getNeighNodes().contains(firstNode));
        Assert.assertFalse(firstNode.getNeighNodes().contains(thirdNode));
    }
    
    @Test
    public void shouldReturnNullIfFindingWasFailure() {
        Assert.assertNull(index.findNodeByWord("rode"));
    }
}
