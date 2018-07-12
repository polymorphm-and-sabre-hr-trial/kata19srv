package com.sabrehrtrial.kata19.pathfinding;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class HammingDistanceEvaluatorBucketParamTest {
    
    private final String word;
    private final List<Integer> bucketResult;
    private final HammingDistanceEvaluator distEval;
    
    public HammingDistanceEvaluatorBucketParamTest(
            String word, List<Integer> bucketResult) {
        this.word = word;
        this.bucketResult = bucketResult;
        
        distEval = new HammingDistanceEvaluator();
    }
    
    @Parameterized.Parameters
    public static List<Object[]> params() {
        return Arrays.asList(new Object[][]{
            {"ruby", Collections.singletonList(4)},
            {"linux", Collections.singletonList(5)},
        });
    }
    
    @Test
    public void test() {
        Assert.assertArrayEquals(
                bucketResult.toArray(),
                distEval.calcBucket(word).toArray()
        );
    }
    
}
