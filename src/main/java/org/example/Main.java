package org.example;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    private static Actor aObj = new Actor();
    private static Movie mObj = new Movie();
    private static Director dObj = new Director();

    public static void main(String[] args) {
        loadCSV();
    }

    public static void loadCSV(){

        //Actur CSDS
        long startTime, endTime, duration;
        System.out.println("---------------------------------------------------------------------");
        startTime = System.currentTimeMillis();
        aObj.loadActorCSV();
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Time taken to load Actor CSV: " + duration + "ms");

        // Movie CSV
        startTime = System.currentTimeMillis();
        mObj.loadMovieCSV();
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Time taken to load Movie CSV: " + duration + "ms");

        // Drector CSV
        startTime = System.currentTimeMillis();
        dObj.loadDirectorCSV();
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Time taken to load Director CSV: " + duration + "ms");
    }
}