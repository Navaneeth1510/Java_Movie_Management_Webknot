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

    static List<Director> directorDataset = new ArrayList<>();
    public void loadDirectorCSV(){
        String line ="", splitby=",";
        try{
            BufferedReader br = new BufferedReader(new FileReader(Main.class.getClassLoader().getResource("directors.csv").getFile()));
            br.readLine();
            while((line = br.readLine())!=null){
                String[] data = line.split(splitby);
                directorDataset.add(new Director(Integer.parseInt(data[0]), data[1], data[2], data[3]));
            }
        }
        catch(IOException | NumberFormatException e){
            throw new RuntimeException("Something went wrong while reading directors csv");
        }
    }
}