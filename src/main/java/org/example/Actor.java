package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Actor {
    int ActorId;
    String name;
    String dob;
    String nationality;

    static Scanner sc = new Scanner(System.in);


    public Actor() {

    }

    public Actor(int actorid, String name, String dob, String nationality) {
        this.ActorId = actorid;
        this.name = name;
        this.dob = dob;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "ActorId=" + ActorId +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}