package com.sabrehrtrial.kata19.pathfinding;

import java.util.Arrays;
import java.util.List;

/**
 * stateless class for implementing evaluating a distance by "Hamming Distance"
 * method.
 */
public class HammingDistanceEvaluator
        implements DistanceEvaluator {
    
    private final int BUCKET_HASH_ITERATIONS = 3;
    private final int BUCKET_HASH_INIT_VALUE = 1069;
    private final int BUCKET_HASH_MULTIPLIER = 37;
    private final int BUCKET_HASH_MODULE = 1000000;
    
    @Override
    public boolean evaluateDistance(String word1, String word2) {
        if (word1.length() != word2.length()) {
            // different words length makes "Hamming Distance" infinite
            
            return false;
        }
        
        char[] chr1 = word1.toCharArray();
        char[] chr2 = word2.toCharArray();
        int len = chr1.length;
        
        int dist = 0;
        
        for (int i = 0; i < len; ++i) {
            if (chr1[i] != chr2[i]) {
                ++dist;
                
                if (dist >= 2) {
                    // the distance begins from 2 is not appropriate
                    
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * update bucket hash value via simple formula.
     * 
     * @param hash previous value of hash.
     * @param ch character number value.
     * @return next value of hash.
     */
    private int updateBucketHash(int hash, int ch) {
        hash += ch;
        hash *= BUCKET_HASH_MULTIPLIER;
        hash %= BUCKET_HASH_MODULE;
        
        return hash;
    }
    
    @Override
    public List<Integer> calcBucket(String word) {
        // we will make two character sequences,
        // and then calculate hash of each one
        
        int seq1Hash = BUCKET_HASH_INIT_VALUE;
        int seq2Hash = BUCKET_HASH_INIT_VALUE;
        
        char[] chs = word.toCharArray();
        
        for (int t = 0; t < BUCKET_HASH_ITERATIONS; ++t) {
            for (int i = 0; i < chs.length; ++i) {
                int ch = chs[i];
                
                if (i % 2 == 0) {
                    seq1Hash = updateBucketHash(seq1Hash, ch);
                } else {
                    seq2Hash = updateBucketHash(seq2Hash, ch);
                }
            }
        }
        
        return Arrays.asList(seq1Hash, seq2Hash);
    }
    
}
