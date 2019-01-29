package com.roadrunner.pathfinder.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.roadrunner.pathfinder.data.RoadGraph;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PathFinder {

    private static RoadGraph graph = new RoadGraph();

    /**
     * This method will check if the path from start to end exists in the graph,
     * using BFS algorithm
     * 
     * @param start
     * @param end
     * @return true if exists else false
     */

    @Cacheable(value = "pathExists", key = "#start + #end")
    public static Boolean isPathExists(String start, String end) {

        boolean pathFound = false;

        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();

        // Create a queue for BFS
        LinkedList<String> queue = new LinkedList<String>();

        // Mark the current node as visited and enqueue it
        visited.put(start, true);
        queue.add(start);

        while (queue.size() != 0) {

            start = queue.poll();

            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<String> iter = graph.getAdjacentNodes(start).iterator();
            while (iter.hasNext()) {
                String nextNode = iter.next();
                if (nextNode.equals(end)) {
                    return true;
                }
                if (!visited.getOrDefault(nextNode, false)) {
                    visited.put(nextNode, true);
                    queue.add(nextNode);
                }
            }
        }
        return pathFound;
    }

    /**
     * This method reads the file add populates the graph object
     * 
     * @param filename
     * @throws IOException
     */
    public static void loadPathFromFile(String filename) throws IOException {
        File file = new File(filename);
        InputStream resource = new FileInputStream(file);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
            String cities;
            while ((cities = reader.readLine()) != null) {
                String[] city = cities.split(",");
                graph.addTwoWay(city[0].trim(), city[1].trim());
            }

        }
    }

}