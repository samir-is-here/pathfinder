package com.roadrunner.pathfinder.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.roadrunner.pathfinder.service.PathFinder;

import org.springframework.stereotype.Component;

@Component
@Path("/")
public class CitiesController {

    private static final String YES = "yes";
    private static final String NO = "no";

    /**
     * This API will return 'yes' there exists a path between orgin and destination,
     * else return 'no'
     * 
     * @param origin
     * @param destination
     * @return String - "yes"/"no"
     */
    @GET
    @Path("/connected")
    public String isConnected(@QueryParam("origin") String origin, @QueryParam("destination") String destination) {
        boolean isConnected = PathFinder.isPathExists(origin, destination);
        return isConnected ? YES : NO;
    }

}