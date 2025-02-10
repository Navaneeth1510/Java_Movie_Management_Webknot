package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Director {

    int DirectorId;
    String name;
    String dob;
    String nationality;

    static Scanner sc = new Scanner(System.in);


    public Director(int directorId, String name, String dob, String nationality) {
        DirectorId = directorId;
        this.name = name;
        this.dob = dob;
        this.nationality = nationality;
    }

    public Director() {

    }

    @Override
    public String toString() {
        return "Director{" +
                "DirectorId=" + DirectorId +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}