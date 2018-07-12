package com.sabrehrtrial.kata19.pathfinding;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HammingDistanceEvaluatorBucketTest {
    
    private HammingDistanceEvaluator distEval;
    
    @Before
    public void setup() {
        distEval = new HammingDistanceEvaluator();
    }
    
    @Test
    public void similarWordsShouldHaveCommonBuckets_ex1() {
        // the third character is different
        
        List<Integer> word1Buckets = distEval.calcBucket("robs");
        List<Integer> word2Buckets = distEval.calcBucket("rods");
        
        Assert.assertTrue(
                word1Buckets.stream().anyMatch(word2Buckets::contains));
    }
    
    @Test
    public void similarWordsShouldHaveCommonBuckets_ex2() {
        // the second character is different
        
        List<Integer> word1Buckets = distEval.calcBucket("lead");
        List<Integer> word2Buckets = distEval.calcBucket("load");
        
        Assert.assertTrue(
                word1Buckets.stream().anyMatch(word2Buckets::contains));
    }
    
    @Test
    public void differentWordsShouldNotProbablyHaveCommonBuckets() {
        // it is only some probability, but we're going to the risk
        
        List<Integer> word1Buckets = distEval.calcBucket("ruby");
        List<Integer> word2Buckets = distEval.calcBucket("code");
        
        Assert.assertFalse(
                word1Buckets.stream().anyMatch(word2Buckets::contains));
    }
}
