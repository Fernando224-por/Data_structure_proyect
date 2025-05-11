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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;

import java.util.Random;

/**
 *
 * @author andfe
 */
public class simpleGraph {

    Utilities tool = new Utilities();

    static class Node {

        String name;
        Double priority;

        Node(String name, Double priority) {
            this.name = name;
            this.priority = priority;
        }
    }

    private Map<String, Map<String, Double>> graph = new HashMap<>();

    private void addEdges(String from, String to, Double weight) {
        graph.putIfAbsent(to, new HashMap<>());
        graph.putIfAbsent(from, new HashMap<>());

        graph.get(to).put(from, weight);
        graph.get(from).put(to, weight);
    }

    public void graphByDistance(String fileName, String nameRoute) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File myFile = new File(fileName);
        if (myFile.exists()) {
            JsonNode readJson = objectMapper.readTree(new File(fileName));

            JsonNode Route = readJson.get(nameRoute);
            System.out.println();
            System.out.println("Paradas de la ruta " + nameRoute);
            for (JsonNode stop : Route) {
                String from = stop.get("from").asText();
                String to = stop.get("to").asText();
                Double weight = stop.get("weight").asDouble();

                addEdges(from, to, weight);

                System.out.println("Los nodos " + from + " a " + to + " con el peso " + weight + " Han sido añadidos");

            }
        } else {
            System.out.println("El archivo no existe");
        }
    }

    public void graphByTime(String fileName, String nameRoute) throws IOException {
        Random r = new Random();
        ObjectMapper objectMapper = new ObjectMapper();
        File myFile = new File(fileName);
        if (myFile.exists()) {
            JsonNode readJson = objectMapper.readTree(new File(fileName));

            JsonNode Route = readJson.get(nameRoute);
            System.out.println();
            System.out.println("Paradas de la ruta " + nameRoute);
            for (JsonNode stop : Route) {
                String from = stop.get("from").asText();
                String to = stop.get("to").asText();
                Double weight = tool.formatDecimal(r.nextDouble(8.0, 15.0));

                addEdges(from, to, weight);

                System.out.println("Los nodos " + from + " a " + to + " con el peso " + weight + " Han sido añadidos");

            }
        } else {
            System.out.println("El archivo no existe");
        }
    }

    public void graphByStop() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File myFile = new File("testFileTwo.json");
        if (myFile.exists()) {
            JsonNode readJson = objectMapper.readTree(new File("testFileTwo.json"));

            JsonNode Points = readJson.get("Puntos");
            System.out.println();
            for (JsonNode stop : Points) {
                String from = stop.get("from").asText();
                String to = stop.get("to").asText();
                Double weight = stop.get("weight").asDouble();

                addEdges(from, to, weight);

                System.out.println("Los nodos " + from + " a " + to + " con el peso " + weight + " Han sido añadidos");

            }
        } else {
            System.out.println("El archivo no existe");
        }
    }

    public void graphByStopTime() throws IOException {
        Random r = new Random();
        ObjectMapper objectMapper = new ObjectMapper();
        File myFile = new File("testFileTwo.json");
        if (myFile.exists()) {
            JsonNode readJson = objectMapper.readTree(new File("testFileTwo.json"));

            JsonNode Points = readJson.get("Puntos");
            System.out.println();
            for (JsonNode stop : Points) {
                String from = stop.get("from").asText();
                String to = stop.get("to").asText();
                Double weight = tool.formatDecimal(r.nextDouble(8.0, 15.0));

                addEdges(from, to, weight);

                System.out.println("Los nodos " + from + " a " + to + " con el peso " + weight + " Han sido añadidos");

            }
        } else {
            System.out.println("El archivo no existe");
        }
    }

    public void printGraph() {
        for (String node : graph.keySet()) {
            System.out.println("El nodo " + node + " esta unido a: " + graph.get(node));
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
                return new PathResult(path, tool.formatDecimal(distance.get(finalNode)));
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
