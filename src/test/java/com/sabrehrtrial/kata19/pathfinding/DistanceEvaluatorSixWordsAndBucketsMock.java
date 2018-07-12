package com.sabrehrtrial.kata19.pathfinding;

import java.util.Arrays;
import java.util.List;

/**
 * Mock version of <code>DistanceEvaluator</code> works only with 6 fixed words.
 * this mock also has <code>calcBucket()</code> implementation
 */
public class DistanceEvaluatorSixWordsAndBucketsMock
        extends DistanceEvaluatorSixWordsMock {
    @Override
    public List<Integer> calcBucket(String word) {
        return Arrays.asList(
                word.length(),
                Math.abs(word.length() - 1),
                word.length() + 1
        );
    }
}
