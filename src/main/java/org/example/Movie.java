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

    public static void getMovie() {
        try {
            System.out.print("Enter movie title or ID: ");
            String movie = sc.next();
            Movie answer = null;
            for (Movie m : movieDataset) {
                if (String.valueOf(m.MovieId).equals(movie) || m.title.equalsIgnoreCase(movie)) {
                    answer = m;
                    break;
                }
            }
            if (answer == null) {
                System.out.println("No movie found !!");
                return;
            }

            Director director = null;
            for (Director d : Director.directorDataset) {
                if (d.DirectorId == answer.DirectorId) {
                    director = d;
                    break;
                }
            }

            if (director == null) {
                System.out.println("Director details not found.");
                return;
            }

            List<String> actors = new ArrayList<>();
            for (Integer actorId : answer.actors) {
                for (Actor actor : Actor.actorDataset) {
                    if (actor.ActorId == actorId) {
                        actors.add(actor.name);
                    }
                }
            }

            System.out.println("\n==== Movie Details ====");
            System.out.println("Movie ID    : " + answer.MovieId);
            System.out.println("Title       : " + answer.title);
            System.out.println("Director    : " + director.name);
            System.out.print("Actors      : ");
            if (actors.isEmpty()) {
                System.out.print("No actors found.");
            } else {
                System.out.println(String.join(" , ", actors));
            }
            System.out.println("Release Year: " + answer.ReleaseYear);
            System.out.println("Genre       : " + answer.genre);
            System.out.println("Rating      : " + answer.rating);
            System.out.println("Duration    : " + answer.duration + " mins");
        } catch (Exception e) {
            System.out.println("An error occurred while fetching movie details: " + e.getMessage());
        }
    }

    public static void top10RatedMovie() {
        try {
            if (movieDataset.isEmpty()) {
                System.out.println("No movies available.");
                return;
            }
            List<Movie> sortedMovies = new ArrayList<>(movieDataset);
            sortedMovies.sort((m1, m2) -> Double.compare(m2.rating, m1.rating));

            System.out.println("\n==== Top 10 Rated Movies ====");
            int count = 0;
            for (Movie movie : sortedMovies) {
                if (count == 10) break;
                System.out.println((count + 1) + ". " + movie.title + " (" + movie.ReleaseYear + ") - Rating: " + movie.rating);
                count++;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while fetching top-rated movies: " + e.getMessage());
        }
    }

    public static void getMovieByGenre() {
        try {
            System.out.print("Enter genre: ");
            String genre = sc.next();
            List<Movie> answer = new ArrayList<>();
            for (Movie movie : movieDataset) {
                if (movie.genre.equalsIgnoreCase(genre)) {
                    answer.add(movie);
                }
            }
            if (answer.isEmpty()) {
                System.out.println("No movies found in the genre: " + genre);
                return;
            }
            System.out.println("\n==== Movies in Genre: " + genre + " ====");
            for (Movie movie : answer) {
                System.out.println(movie.title + " (" + movie.ReleaseYear + ") - Rating: " + movie.rating);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while fetching movies by genre: " + e.getMessage());
        }
    }

    public static void getMovieByDirector() {
        try {
            System.out.print("Enter director ID: ");
            int id = sc.nextInt();
            List<Movie> movielist = new ArrayList<>();
            for (Movie m : movieDataset) {
                if (m.DirectorId == id) {
                    movielist.add(m);
                }
            }
            System.out.println("\n==== Movies of the Director with ID: " + id + " ====");
            for (Movie movie : movielist) {
                System.out.println(movie.title + " (" + movie.ReleaseYear + ") - Rating: " + movie.rating);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while fetching movies by director: " + e.getMessage());
        }
    }

    public static void getMoviesByYear() {
        try {
            System.out.print("Enter release year: ");
            int year = sc.nextInt();

            ArrayList<Movie> movielist = new ArrayList<>();
            for (Movie m : movieDataset) {
                if (m.ReleaseYear == year) {
                    movielist.add(m);
                }
            }

            System.out.println("\n==== Movies released in the year: " + year + " ====");
            if (movielist.isEmpty()) {
                System.out.println("No movies found for the given year.");
            } else {
                for (Movie movie : movielist) {
                    System.out.println(movie.title + " (" + movie.ReleaseYear + ") - Rating: " + movie.rating);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid year.");
            sc.nextLine(); // Clear scanner buffer
        } catch (Exception e) {
            System.out.println("An error occurred while fetching movies by year: " + e.getMessage());
        }
    }

    public static void getMoviesByYearRange() {
        try {
            System.out.print("Enter start year: ");
            int from = sc.nextInt();
            System.out.print("Enter end year: ");
            int to = sc.nextInt();

            if (from > to) {
                System.out.println("Invalid year range. Start year must be less than or equal to end year.");
                return;
            }

            ArrayList<Movie> movielist = new ArrayList<>();
            for (Movie m : movieDataset) {
                if (m.ReleaseYear >= from && m.ReleaseYear <= to) {
                    movielist.add(m);
                }
            }

            System.out.println("\n==== Movies released between the years " + from + " and " + to + " ====");
            if (movielist.isEmpty()) {
                System.out.println("No movies found in the given range.");
            } else {
                for (Movie movie : movielist) {
                    System.out.println(movie.title + " (" + movie.ReleaseYear + ") - Rating: " + movie.rating);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid years.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred while fetching movies by year range: " + e.getMessage());
        }
    }

    public static void addMovie() {
        try {
            System.out.print("Enter Movie ID: ");
            int MovieId = sc.nextInt();

            System.out.print("Enter Title: ");
            sc.nextLine();
            String title = sc.nextLine();

            System.out.print("Enter Release Year: ");
            int ReleaseYear = sc.nextInt();

            System.out.print("Enter Genre: ");
            sc.nextLine(); // Clear the buffer
            String genre = sc.nextLine();

            System.out.print("Enter Rating: ");
            double rating = sc.nextDouble();

            System.out.print("Enter Duration (mins): ");
            double duration = sc.nextDouble();

            System.out.print("Enter Director ID: ");
            int DirectorId = sc.nextInt();

            System.out.print("Enter the number of actors: ");
            int n = sc.nextInt();
            ArrayList<Integer> actors = new ArrayList<>();

            System.out.println("Enter Actor IDs:");
            for (int i = 0; i < n; i++) {
                actors.add(sc.nextInt());
            }

            Movie newMovie = new Movie(MovieId, title, ReleaseYear, genre, rating, duration, DirectorId, actors);
            movieDataset.add(newMovie);

            System.out.println("\n==== New movie added ====");
            System.out.println(newMovie);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred while adding the movie: " + e.getMessage());
        }
    }

    public static void updateMovie() {
        try {
            System.out.print("Enter the Movie ID to update: ");
            int movieId = sc.nextInt();
            Movie movieToUpdate = null;

            for (Movie m : movieDataset) {
                if (m.MovieId == movieId) {
                    movieToUpdate = m;
                    break;
                }
            }

            if (movieToUpdate == null) {
                System.out.println("Movie not found!");
                return;
            }

            System.out.print("Enter new rating (-1 to skip): ");
            double newRating = sc.nextDouble();
            if (newRating != -1) movieToUpdate.rating = newRating;

            System.out.print("Enter new duration (-1 to skip): ");
            double newDuration = sc.nextDouble();
            if (newDuration != -1) movieToUpdate.duration = newDuration;

            System.out.println("Movie updated successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred while updating the movie: " + e.getMessage());
        }
    }

    public static void deleteMovie() {
        try {
            System.out.print("Enter the movie ID to delete: ");
            int movieId = sc.nextInt();

            boolean removed = movieDataset.removeIf(movie -> movie.MovieId == movieId);

            if (removed) {
                System.out.println("Movie deleted successfully!");
            } else {
                System.out.println("Movie not found!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid Movie ID.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the movie: " + e.getMessage());
        }
    }

    public static void sortMoviesByReleaseYear() {
        try {
            if (movieDataset.isEmpty()) {
                System.out.println("No movies available to sort.");
                return;
            }

            List<Movie> sortedMovies = new ArrayList<>(movieDataset);
            sortedMovies.sort(Comparator.comparingInt(m -> m.ReleaseYear));

            System.out.println("\n==== Top 15 Movies Sorted by Release Year ====");
            int count = 0;
            for (Movie movie : sortedMovies) {
                if (count == 15) break;
                System.out.println((count + 1) + ". " + movie.title + " (" + movie.ReleaseYear + ") - Rating: " + movie.rating);
                count++;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while sorting movies: " + e.getMessage());
        }
    }
}