package com.roadrunner.pathfinder.data;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RoadGraph {

    private Map<String, LinkedHashSet<String>> map = new HashMap<String, LinkedHashSet<String>>();

    public void addOneWay(String node1, String node2) {
        LinkedHashSet<String> adjacent = map.get(node1);
        if (adjacent == null) {
            adjacent = new LinkedHashSet<String>();
            map.put(node1, adjacent);
        }
        adjacent.add(node2);
    }

    public void addTwoWay(String node1, String node2) {
        addOneWay(node1, node2);
        addOneWay(node2, node1);
    }

    public LinkedList<String> getAdjacentNodes(String node) {
        LinkedHashSet<String> adjacent = map.get(node);
        if (adjacent == null) {
            return new LinkedList<String>();
        }
        return new LinkedList<String>(adjacent);
    }
}