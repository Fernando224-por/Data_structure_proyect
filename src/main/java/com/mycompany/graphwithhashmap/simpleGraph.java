/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphwithhashmap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author andfe
 */
public class simpleGraph {

    private Map<String, Map<String, Double>> graph = new HashMap<>();

    public void addNodes(String node) {
        graph.putIfAbsent(node, new HashMap<>());
    }

    public void addEdges(String from, String to, Double weight) {
        graph.putIfAbsent(to, new HashMap<>());
        graph.putIfAbsent(from, new HashMap<>());

        graph.get(to).put(from, weight);
        graph.get(from).put(to, weight);
    }

    public void printGraph() {
        for (String node : graph.keySet()) {
            System.out.println("El nodo " + node + " esta unido a: " + graph.get(node));
        }
    }

    static class Node {

        String name;
        Double priority;

        Node(String name, Double priority) {
            this.name = name;
            this.priority = priority;
        }
    }

    public PathResult findShortestPath(String startNode, String finalNode) {
        Map<String, Double> distance = new HashMap<>();
        Map<String, String> previus = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(n -> n.priority));

        for (String node : graph.keySet()) {
            if (node.equals(startNode)) {
                distance.put(node, 0.0);
                queue.add(new Node(node, 0.0));
            } else {
                distance.put(node, Double.MAX_VALUE);
                queue.add(new Node(node, Double.MAX_VALUE));
            }
            previus.put(node, null);
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            String CurrentNode = current.name;

            if (CurrentNode.equals(finalNode)) {
                List<String> path = new ArrayList<>();
                String step = finalNode;
                while (step != null) {
                    path.add(0, step);
                    step = previus.get(step);
                }
                return new PathResult(path, distance.get(finalNode));
            }
            if (distance.get(CurrentNode) == Double.MAX_VALUE) {
                break;
            }

            for (Map.Entry<String, Double> neighbor : graph.get(CurrentNode).entrySet()) {
                String neighborNode = neighbor.getKey();
                Double newDist = (Double) (distance.get(CurrentNode) + neighbor.getValue());

                if (newDist < distance.get(neighborNode)) {
                    distance.put(neighborNode, newDist);
                    previus.put(neighborNode, CurrentNode);
                    queue.add(new Node(neighborNode, newDist));
                }
            }
        }
        return new PathResult(Collections.EMPTY_LIST, Double.MAX_VALUE);

    }

    public void sortResult(ArrayList<simpleGraph.PathResult> resultados) {
        Collections.sort(resultados, Comparator.comparingDouble(r -> r.distance));
        System.out.println("organizar rutas de menor a mayor");
        for (simpleGraph.PathResult resultado : resultados) {
            System.out.println(resultado);
        }
    }

    static class PathResult {

        List<String> path;
        Double distance;

        PathResult(List<String> path, Double distance) {
            this.path = path;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Camino: " + path + ", Distancia: " + distance;
        }
    }
}
