package com.sabrehrtrial.kata19.cli;

import com.sabrehrtrial.kata19.assets.BuiltinWordList;
import com.sabrehrtrial.kata19.pathfinding.HammingDistanceEvaluator;
import com.sabrehrtrial.kata19.pathfinding.Pathfinder;
import com.sabrehrtrial.kata19.pathfinding.WordNodeIndex;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * simple command line user interface.
 */
public class Cli {
    
    private static final String CUSTOM_WORDLIST_ENC = "utf-8";
    private static final int FINDING_SIZE_LIMIT = 1000;
    
    /**
     * main entry point
     * 
     * @param args application arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println(
                    "arguments: [PATH-TO-CUSTOM-WORD-LIST] "
                    + "word1 word2 "
                    + "[word3 word4 "
                    + "[word5 word6 [...]]]"
            );
            
            return;
        }
        
        System.out.println("indexing word list...");
        
        HammingDistanceEvaluator distEval = new HammingDistanceEvaluator();
        WordNodeIndex index = new WordNodeIndex(distEval);
        
        ArrayList<String[]> wordPairs = new ArrayList<>();
        
        if (args.length % 2 == 1) {
            // use custom word list
            
            String wordlistPath = args[0];
            
            for (int i = 1; i < args.length; i += 2) {
                wordPairs.add(new String[]{args[i], args[i + 1]});
            }
            
            Files.lines(
                    Paths.get(wordlistPath),
                    Charset.forName(CUSTOM_WORDLIST_ENC)
            ).forEach((line) -> {
                index.addWord(line);
            });
        } else {
            // use builtin word list
            
            for (int i = 0; i < args.length; i += 2) {
                wordPairs.add(new String[]{args[i], args[i + 1]});
            }
            
            BuiltinWordList.fetch((line) -> {
                index.addWord(line);
            });
        }
        
        System.out.println("finding pathes...");
        
        Pathfinder pathfinder = new Pathfinder(index);
        
        wordPairs.forEach((pair) -> {
            String word1 = pair[0];
            String word2 = pair[1];
            
            List<String> path = pathfinder.findPath(
                    word1, word2, FINDING_SIZE_LIMIT);
            
            if (path != null) {
                System.out.println(
                        String.format(
                                "* %s -> %s -> %s",
                                word1,
                                String.join(" -> ", path),
                                word2
                        )
                );
            } else {
                System.out.println(
                        String.format("* %s {path not found} %s", word1, word2)
                );
            }
        });
    }
    
}
