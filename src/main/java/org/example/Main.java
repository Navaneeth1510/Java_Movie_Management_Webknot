package org.example;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    private static Actor aObj = new Actor();
    private static Movie mObj = new Movie();
    private static Director dObj = new Director();

    public static void main(String[] args) {
        loadCSV();
        while(true) {
            displayMenu();
        }
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

    public static void displayMenu(){
        System.out.println("\n=======================================================================================");
        System.out.println("Hello, Welcome to the Movie Consultant");
        System.out.println("Please choose any one from the below menu");
        System.out.println("1. Get Movie information \n2. Top 10 rated movies\n3. Get movies by genre\n4. Get movie by director");
        System.out.println("5. Get movies by release year\n6. Get movies by release year range\n7. Add new movie\n8. Update movie details");
        System.out.println("9. Delete a movie \n10. Sort & return top 15 movies by release year\n11. Get directors with most movies\n12. Get actor who have worked in multiple movies");
        System.out.println("13. Get the movies of the actor who is the youngest as of 10-02-2025 \n14. Exit");
        System.out.println("-----------------------------------------------------");

        System.out.println("Please enter your choice : ");
        int choice = sc.nextInt();
        switch (choice){
            case 1: getMovie();break;
            case 2: top10RatedMovie();break;
            case 3: getMovieByGenre();break;
            case 4: getMovieByDirector();break;
            case 5: getMoviesByYear();break;
            case 6: getMoviesByYearRange();break;
            case 7: addMovie();break;
            case 8: updateMovie();break;
            case 9: deleteMovie();break;
            case 10: sortMoviesByReleaseYear();break;
            case 11: getTop5DirectorsWithMostMovies();break;
            case 12: getActorWithMostMovies();break;
            case 13: getMoviesOfYoungestActor();break;
            case 14:
                System.out.println("Thank you for using our application!\nThank you!");;
                System.exit(0);
            default:
                System.out.println("Please enter a valid option :)");
        }
    }

    private static void getMovie(){
        Movie.getMovie();
        return;
    }
    private static void top10RatedMovie(){
        Movie.top10RatedMovie();
        return;
    }
    private static void getMovieByGenre(){
        Movie.getMovieByGenre();
        return;
    }
    private static void getMovieByDirector(){
        Movie.getMovieByDirector();
        return;
    }
    private static void getMoviesByYear(){

        Movie.getMoviesByYear();
        return;
    }
    private static void getMoviesByYearRange(){
        Movie.getMoviesByYearRange();
        return;
    }
    private static void addMovie(){
        Movie.addMovie();
        return;
    }
    private static void updateMovie(){
        Movie.updateMovie();
        return;
    }
    private static void deleteMovie(){
        Movie.deleteMovie();
        return;
    }
    private static void sortMoviesByReleaseYear(){
        Movie.sortMoviesByReleaseYear();
        return;
    }
    private static void getTop5DirectorsWithMostMovies(){
        Director.getTop5DirectorsWithMostMovies();
        return;
    }
    private static void getActorWithMostMovies(){
        Actor.getActorWithMostMovies();
        return;
    }
    private static void getMoviesOfYoungestActor(){
        Actor.getMoviesOfYoungestActor();
        return;
    }
}