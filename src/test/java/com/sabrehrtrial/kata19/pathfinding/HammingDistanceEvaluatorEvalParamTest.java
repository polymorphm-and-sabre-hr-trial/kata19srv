package com.sabrehrtrial.kata19.pathfinding;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class HammingDistanceEvaluatorEvalParamTest {
    
    private final String word1;
    private final String word2;
    private final boolean evalResult;
    private final HammingDistanceEvaluator distEval;
    
    public HammingDistanceEvaluatorEvalParamTest(
            String word1, String word2, boolean evalResult) {
        this.word1 = word1;
        this.word2 = word2;
        this.evalResult = evalResult;
        
        distEval = new HammingDistanceEvaluator();
    }
    
    @Parameterized.Parameters
    public static List<Object[]> params() {
        return Arrays.asList(new Object[][]{
            {"robs", "rods", true},
            {"rods", "robs", true},
            {"rubs", "rods", false},
            {"rods", "rubs", false},
            {"linu", "linux", false},
            {"linux", "linu", false},
        });
    }
    
    @Test
    public void test() {
        Assert.assertEquals(
                evalResult,
                distEval.evaluateDistance(word1, word2)
        );
    }
    
}
