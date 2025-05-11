/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.graphwithhashmap;

/**
 *
 * @author andfe
 */
import java.io.IOException;
import java.util.ArrayList;

public class GraphWithHashMap {

    public static void main(String[] args) throws IOException {

        simpleGraph DistanceRouteOne = new simpleGraph();
        simpleGraph DistanceRouteTwo = new simpleGraph();
        simpleGraph DistanceRouteThree = new simpleGraph();

        Utilities a = new Utilities();

        ArrayList<simpleGraph.PathResult> pathsDistance = new ArrayList<>();

        System.out.println("Evaluacion de rutas por Distancia");
        DistanceRouteOne.graphByDistance("testFile.json", "11_10_Bilbao");
        DistanceRouteTwo.graphByDistance("testFile.json", "C_104_Suba_Compartir");
        DistanceRouteThree.graphByDistance("testFile.json", "11_2_San_Andres");

        simpleGraph.PathResult resultTestOne = DistanceRouteOne.findShortestPath("Portal_Suba", "Mi_Casa");

        simpleGraph.PathResult resultTestTwo = DistanceRouteTwo.findShortestPath("Portal_Suba", "Mi_Casa");

        simpleGraph.PathResult resultTestThree = DistanceRouteThree.findShortestPath("Portal_Suba", "Mi_Casa");

        pathsDistance.add(resultTestOne);
        pathsDistance.add(resultTestTwo);
        pathsDistance.add(resultTestThree);

        a.sortResult(pathsDistance);

        pathsDistance.clear();

        //evaluar rutas de manera individual (por tiempo)
        simpleGraph TimeRouteOne = new simpleGraph();
        simpleGraph TimeRouteTwo = new simpleGraph();
        simpleGraph TimeRouteThree = new simpleGraph();

        System.out.println("");
        System.out.println("Evaluacion de rutas por Tiempo");

        TimeRouteOne.graphByTime("testFile.json", "11_10_Bilbao");
        TimeRouteTwo.graphByTime("testFile.json", "C_104_Suba_Compartir");
        TimeRouteThree.graphByTime("testFile.json", "11_2_San_Andres");

        simpleGraph.PathResult resultTimeRouteOne = TimeRouteOne.findShortestPath("Portal_Suba", "Mi_Casa");

        simpleGraph.PathResult resultTimeRouteTwo = TimeRouteTwo.findShortestPath("Portal_Suba", "Mi_Casa");

        simpleGraph.PathResult resultTimeRouteThree = TimeRouteThree.findShortestPath("Portal_Suba", "Mi_Casa");

        pathsDistance.add(resultTimeRouteOne);
        pathsDistance.add(resultTimeRouteTwo);
        pathsDistance.add(resultTimeRouteThree);

        a.sortResult(pathsDistance);
        //evaluacion del trayecto 
        simpleGraph diferentRoutesDistance = new simpleGraph();
        simpleGraph diferentRoutesTime = new simpleGraph();

        diferentRoutesDistance.graphByStop();
        diferentRoutesTime.graphByStopTime();

        simpleGraph.PathResult resultDiferentDistance = diferentRoutesDistance.findShortestPath("Portal_Suba", "Mi_Casa");
        System.out.println(resultDiferentDistance);

        simpleGraph.PathResult resultDiferentTime = diferentRoutesTime.findShortestPath("Portal_Suba", "Mi_Casa");
        System.out.println(resultDiferentTime);

    }
}
