package com.sabrehrtrial.kata19.pathfinding;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PathfinderTest {
    
    private DistanceEvaluator distEvalMock;
    private WordNodeIndex index;
    private Pathfinder pathfinder;
    
    @Before
    public void setup() {
        distEvalMock = new DistanceEvaluatorSixWordsMock();
        index = new WordNodeIndex(distEvalMock);
        
        index.addWord("ruby");
        index.addWord("rubs");
        index.addWord("robs");
        index.addWord("rods");
        index.addWord("rode");
        index.addWord("code");
        index.addWord("unix");
        
        // "unix" is in the index but has no any connections
        
        pathfinder = new Pathfinder(index);
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptNullIndex() {
        Pathfinder nullPathfinder = new Pathfinder(null);
        
        Assert.fail();
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptNullWord1() {
        pathfinder.findPath(null, "rode", 10);
        
        Assert.fail();
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptNullWord2() {
        pathfinder.findPath("rubs", null, 10);
        
        Assert.fail();
    }
    
    @Test
    public void shouldFindIfItCould() {
        List<String> path = pathfinder.findPath("rubs", "rode", 10);
        
        Assert.assertArrayEquals(
                new String[] {"robs", "rods"},
                path.toArray()
        );
    }
    
    @Test
    public void shouldReturnNullIfFindingWasFailure() {
        List<String> path = pathfinder.findPath("ruby", "code", 3);
        
        Assert.assertNull(path);
    }
    
    @Test
    public void shouldBeAbleToFindWhenSizeIsUnlimited() {
        List<String> path = pathfinder.findPath("ruby", "code", -1);
        
        Assert.assertArrayEquals(
                new String[] {"rubs", "robs", "rods", "rode"},
                path.toArray()
        );
    }
    
    @Test
    public void findingWithUnlimitedSizeShouldAllowToReturnNull() {
        Assert.assertNull(pathfinder.findPath("ruby", "unix", -1));
    }
    
    @Test
    public void findingWithDirectConnectedWordsShouldReturnEmptyList() {
        List<String> path = pathfinder.findPath("robs", "rods", 10);
        
        Assert.assertTrue(path.isEmpty());
    }
    
    @Test
    public void findingUnknownWordsShouldReturnNull() {
        Assert.assertNull(pathfinder.findPath("ruby", "unknown", 10));
        Assert.assertNull(pathfinder.findPath("ruby", "unknown", -1));
        Assert.assertNull(pathfinder.findPath("unknown", "code", 10));
        Assert.assertNull(pathfinder.findPath("unknown", "code", -1));
        Assert.assertNull(pathfinder.findPath("unknown", "notinindex", 10));
        Assert.assertNull(pathfinder.findPath("unknown", "notinindex", -1));
    }
    
    @Test
    public void findingTheSameWordShouldReturnEmptyList() {
        Assert.assertTrue(pathfinder.findPath("rubs", "rubs", 10).isEmpty());
        Assert.assertTrue(pathfinder.findPath("rubs", "rubs", 0).isEmpty());
        Assert.assertTrue(pathfinder.findPath("rubs", "rubs", -1).isEmpty());
    }
    
    @Test
    public void findingTheSameWordShouldReturnEmptyListEvenTheWordWasUnknown() {
        Assert.assertTrue(pathfinder.findPath("unknown", "unknown", 10).isEmpty());
        Assert.assertTrue(pathfinder.findPath("unknown", "unknown", 0).isEmpty());
        Assert.assertTrue(pathfinder.findPath("unknown", "unknown", -1).isEmpty());
    }
    
}
