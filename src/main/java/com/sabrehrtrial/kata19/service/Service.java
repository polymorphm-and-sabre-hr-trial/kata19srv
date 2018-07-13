package com.sabrehrtrial.kata19.service;

import com.sabrehrtrial.kata19.assets.BuiltinWordList;
import com.sabrehrtrial.kata19.pathfinding.HammingDistanceEvaluator;
import com.sabrehrtrial.kata19.pathfinding.Pathfinder;
import com.sabrehrtrial.kata19.pathfinding.WordNodeIndex;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * service endpoint for Kata19
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Service {
    
    private static final int FINDING_SIZE_LIMIT = 1000;
    
    private HammingDistanceEvaluator distEval = new HammingDistanceEvaluator();
    private WordNodeIndex index = new WordNodeIndex(distEval);
    private Pathfinder pathfinder = new Pathfinder(index);
    
    public void init() {
        distEval = new HammingDistanceEvaluator();
        index = new WordNodeIndex(distEval);
        
        // use builtin word list
        BuiltinWordList.fetch((line) -> {
            index.addWord(line);
        });
        
        pathfinder = new Pathfinder(index);
    }
    
    @GET
    @Path("/findpath/{word1}/{word2}")
    public List<String> getAccount(
            @PathParam("word1") String word1,
            @PathParam("word2") String word2
    ) {
        List<String> innerPath = pathfinder.findPath(
                word1, word2, FINDING_SIZE_LIMIT);
        
        if (innerPath == null) {
            ErrorStruct errorStruct = new ErrorStruct(
                    "PATHFINDING_FAILURE",
                    "the path to these words is not found"
            );
            
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity(errorStruct)
                            .build()
            );
        }
        
        ArrayList<String> resultPath = new ArrayList<>();
        
        resultPath.add(word1);
        resultPath.addAll(innerPath);
        resultPath.add(word2);
        
        return resultPath;
    }
    
}
