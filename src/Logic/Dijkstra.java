package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    private Graph g;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private Set<Node> settledNodes;
    private Set<Node> unsettledNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Integer> distance;

    public Dijkstra (Graph graph){
        this.g = graph;
        this.nodes = g.getNodes();
        this.edges = g.getEdges();
    }

    public void execute(int sourceId){
        Node source = g.getNode(sourceId);
        settledNodes = new HashSet<Node>();
        unsettledNodes = new HashSet<Node>();
        distance = new HashMap<Node, Integer>();
        predecessors = new HashMap<Node, Node>();
        distance.put(source, 0);
        unsettledNodes.add(source);
        while(unsettledNodes.size() > 0){
            Node node = getMinimum(unsettledNodes);
            settledNodes.add(node);
            unsettledNodes.remove(node);
            findMinimalDistance(node);
        }
    }

    private Node getMinimum(Set<Node> nodes){
        Node minimum = null;
        for(Node node: nodes){
            if (minimum == null){
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(minimum)){
                    minimum = node;
                }
            }
        }
        return minimum;
    }

    private int getShortestDistance(Node node){
        Integer d = distance.get(node);
        if (d == null){
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private void findMinimalDistance(Node node){
        List<Node> neighbours = getNeighbours(node);
        for (Node target: neighbours){
            if(getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)){
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unsettledNodes.add(target);
            }
        }
    }

    public Path getPath(int targetId){
        Node target = g.getNode(targetId);
        ArrayList<Node> path = new ArrayList<Node>();
        Node step = target;
        if (predecessors.get(step) == null){
            return null;
        }
        path.add(step);
        while(predecessors.get(step) != null){
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        int total = calculatePathWeight(path);
        Path pathObject = new Path(path, total);
        return pathObject;
    }

    private List<Node> getNeighbours(Node node){
        List<Node> neighbours = new ArrayList<Node>();
        for(Edge edge: edges) {
            if (edge.getStartId() == node.getId() && !isSettled(g.getNode(edge.getEndId()))) {
                neighbours.add(g.getNode(edge.getEndId()));
            }
        }
        return neighbours;
    }

    private int getDistance(Node node, Node target){
        for (Edge edge: edges){
            if(edge.getStartId() == node.getId() && edge.getEndId() == target.getId()){
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private int calculatePathWeight(ArrayList<Node> path){
        int weight = 0;
        Node tempNode = null;
        for (Node node: path){
            if (tempNode == null){
                tempNode = node;
            } else {
                for(Edge edge: edges){
                    if (edge.getStartId() == tempNode.getId() && edge.getEndId() == node.getId()){
                        weight = weight + edge.getWeight();
                    }
                }
                tempNode = node;
            }
        }
        return weight;
    }

    private boolean isSettled(Node node){
        return settledNodes.contains(node);
    }

    private class Path{
        private ArrayList<Node> path;
        private int totalWeight;
        public Path(ArrayList<Node> camino, int weight){
            this.path = camino;
            this.totalWeight = weight;
        }

        public ArrayList<Node> getPath(){
            return this.path;
        }

        public int getTotalWeight(){
            return this.totalWeight;
        }
    }
}
