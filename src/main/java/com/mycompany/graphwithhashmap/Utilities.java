/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphwithhashmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 *
 * @author andfe
 */
public class Utilities {
    public void sortResult(ArrayList<simpleGraph.PathResult> resultados) {
        Collections.sort(resultados, Comparator.comparingDouble(r -> r.distance));
        System.out.println();
        System.out.println("organizar rutas de menor a mayor");
        for (simpleGraph.PathResult resultado : resultados) {
            System.out.println(resultado);
        }
    }
    
    public Double formatDecimal (Double data){
        Double dataFormatted;
        dataFormatted = Math.round(data * Math.pow(10, 3))/Math.pow(10, 3);
        return dataFormatted;
    }
}
