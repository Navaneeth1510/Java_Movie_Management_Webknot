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

    static List<Movie> movieDataset = new ArrayList<>();
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
}