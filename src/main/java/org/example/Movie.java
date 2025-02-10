package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Movie {
    int MovieId;
    String title;
    int ReleaseYear;
    String genre;
    Double rating;
    Double duration;
    int DirectorId;
    ArrayList<Integer> actors;

    static Scanner sc = new Scanner(System.in);

    public Movie(int movieId, String title, int releaseYear, String genre, Double rating, Double duration, int directorId, ArrayList<Integer> actors) {
        MovieId = movieId;
        this.title = title;
        ReleaseYear = releaseYear;
        this.rating = rating;
        this.genre = genre;
        this.duration = duration;
        DirectorId = directorId;
        this.actors = actors;
    }

    public Movie() {

    }

    @Override
    public String toString() {
        return "" +
                "MovieId=" + MovieId +
                ", title='" + title + '\'' +
                ", ReleaseYear=" + ReleaseYear +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", duration=" + duration +
                ", DirectorId=" + DirectorId +
                ", actors=" + actors +
                "";
    }

    static List<Movie> movieDataset = new ArrayList<>();
    public void loadMovieCSV(){
        String line ="", splitby=",";
        try{
            BufferedReader br = new BufferedReader(new FileReader(Main.class.getClassLoader().getResource("movies.csv").getFile()));
            br.readLine();
            while((line = br.readLine())!=null){
                String[] data = line.split(splitby);
                ArrayList<Integer> listOfActors = new ArrayList<>();

                String actors = (line.split("\""))[1];
                String[] actorarray = actors.split(",");
                for(String s: actorarray)
                    listOfActors.add(Integer.parseInt(s));
                movieDataset.add(new Movie(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), data[3], Double.parseDouble(data[4]), Double.parseDouble(data[5]), Integer.parseInt(data[6]), new ArrayList<>(listOfActors)));
            }
        }
        catch(IOException | NumberFormatException e){
            throw new RuntimeException("Something went wrong while reading movies csv");
        }
    }
}