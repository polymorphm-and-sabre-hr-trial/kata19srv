package com.sabrehrtrial.kata19.pathfinding;

import org.junit.Assert;
import org.junit.Test;

public class WordNodeTest {
    
    @Test
    public void emptyNeighListShouldNotBeNull() {
        WordNode aloneNode = new WordNode("alone"); // forever alone ;)
        
        Assert.assertNotNull(aloneNode.getNeighNodes());
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptNullWord() {
        WordNode nullNode = new WordNode(null);
        
        Assert.fail();
    }
    
    @Test
    public void connectMethodMakesConnection() {
        WordNode oneNode = new WordNode("rods");
        WordNode anotherNode = new WordNode("rode");
        
        Assert.assertFalse(oneNode.getNeighNodes().contains(anotherNode));
        Assert.assertFalse(anotherNode.getNeighNodes().contains(oneNode));
        
        WordNode.connectNodes(oneNode, anotherNode);
        
        Assert.assertTrue(oneNode.getNeighNodes().contains(anotherNode));
        Assert.assertTrue(anotherNode.getNeighNodes().contains(oneNode));
    }
    
}
