/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.graphwithhashmap;

/**
 *
 * @author andfe
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GraphWithHashMap {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        simpleGraph g = new simpleGraph();
        simpleGraph g2 = new simpleGraph();
        simpleGraph g3 = new simpleGraph();

        File myFile = new File("testFile.json");

        if (myFile.exists()) {
            Random r = new Random();
            JsonNode readJson = objectMapper.readTree(new File("testFile.json"));

            JsonNode RouteOne = readJson.get("11_10_Bilbao");
            System.out.println("Paradas de la ruta 11-10 Bilbao");
            for (JsonNode ubicacion : RouteOne) {
                String from = ubicacion.get("from").asText();
                String to = ubicacion.get("to").asText();
                Double weight = ubicacion.get("weight").asDouble();

                g.addEdges(from, to, weight);

                System.out.println("Los nodos " + from + " a " + to + " con el peso " + weight + " Han sido añadidos");

            }
            JsonNode RouteTwo = readJson.get("C_104_Suba_Compartir");
            System.out.println("Paradas de la ruta C104 Suba Compartir");
            for (JsonNode ubicacion : RouteTwo) {
                String from = ubicacion.get("from").asText();
                String to = ubicacion.get("to").asText();
                Double weight = ubicacion.get("weight").asDouble();

                g2.addEdges(from, to, weight);

                System.out.println("Los nodos " + from + " a " + to + " con el peso " + weight + " Han sido añadidos");

            }

            JsonNode RouteThree = readJson.get("11_2_San_Andres");
            System.out.println("Paradas de la ruta 11_2_San_Andres");
            for (JsonNode ubicacion : RouteThree) {
                String from = ubicacion.get("from").asText();
                String to = ubicacion.get("to").asText();
                Double weight = ubicacion.get("weight").asDouble();

                g3.addEdges(from, to, weight);

                System.out.println("Los nodos " + from + " a " + to + " con el peso " + weight + " Han sido añadidos");

            }
        } else {
            System.out.println("El archivo no existe");
        }

        simpleGraph.PathResult resultTestOne = g.findShortestPath("Portal_Suba", "Mi_Casa");

        simpleGraph.PathResult resultTestTwo = g2.findShortestPath("Portal_Suba", "Mi_Casa");

        simpleGraph.PathResult resultTestThree = g3.findShortestPath("Portal_Suba", "Mi_Casa");

        ArrayList<simpleGraph.PathResult> paths = new ArrayList<>();

        paths.add(resultTestOne);
        paths.add(resultTestTwo);
        paths.add(resultTestThree);

        g.sortResult(paths);
    }
}
