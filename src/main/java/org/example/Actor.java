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

    public static void getActorWithMostMovies() {
        try {
            if (Movie.movieDataset.isEmpty() || actorDataset.isEmpty()) {
                System.out.println("No movies or actors available.");
                return;
            }

            Map<Integer, Integer> actorMovieCount = new HashMap<>();

            // Count movies for each actor
            for (Movie movie : Movie.movieDataset) {
                for (Integer actorId : movie.actors) {
                    actorMovieCount.put(actorId, actorMovieCount.getOrDefault(actorId, 0) + 1);
                }
            }

            int maxMovies = 0;
            int bestActorId = -1;
            for (Map.Entry<Integer, Integer> entry : actorMovieCount.entrySet()) {
                if (entry.getValue() > maxMovies) {
                    maxMovies = entry.getValue();
                    bestActorId = entry.getKey();
                }
            }

            if (bestActorId == -1) {
                System.out.println("No actors found!");
                return;
            }

            for (Actor actor : actorDataset) {
                if (actor.ActorId == bestActorId) {
                    System.out.println("\n==== Actor Who Has Worked in Most Movies ====");
                    System.out.println("Actor: " + actor.name + " | Movies Count: " + maxMovies);
                    System.out.println("Nationality: " + actor.nationality);
                    System.out.println("Date of Birth: " + actor.dob);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving the actor with most movies: " + e.getMessage());
        }
    }

    public static void getMoviesOfYoungestActor() {
        try {
            if (actorDataset.isEmpty()) {
                System.out.println("No actors available.");
                return;
            }

            Actor youngestActor = null;

            for (Actor actor : actorDataset) {
                if (youngestActor == null || actor.dob.compareTo(youngestActor.dob) > 0) {
                    youngestActor = actor;
                }
            }

            if (youngestActor == null) {
                System.out.println("No valid actors found!");
                return;
            }

            System.out.println("\n==== Movies of the Youngest Actor ====");
            System.out.println("Actor: " + youngestActor.name + " | DOB: " + youngestActor.dob);

            List<Movie> actorMovies = new ArrayList<>();
            for (Movie movie : Movie.movieDataset) {
                if (movie.actors.contains(youngestActor.ActorId)) {
                    actorMovies.add(movie);
                }
            }

            if (actorMovies.isEmpty()) {
                System.out.println("No movies found for this actor.");
            } else {
                System.out.println("Movies:");
                for (Movie movie : actorMovies) {
                    System.out.println("- " + movie.title + " (" + movie.ReleaseYear + ")");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving movies of the youngest actor: " + e.getMessage());
        }
    }
}