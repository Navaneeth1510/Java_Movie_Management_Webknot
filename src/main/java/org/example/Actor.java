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

    static List<Actor> actorDataset = new ArrayList<>();
    public void loadActorCSV(){
        String line ="", splitby=",";
        try{
            BufferedReader br = new BufferedReader(new FileReader(Main.class.getClassLoader().getResource("actors.csv").getFile()));
            br.readLine();
            while((line = br.readLine())!=null){
                String[] data = line.split(splitby);
                actorDataset.add(new Actor(Integer.parseInt(data[0]), data[1], data[2], data[3]));
            }
        }
        catch(IOException | NumberFormatException e){
            throw new RuntimeException("Something went wrong while reading actors csv");
        }
    }
}