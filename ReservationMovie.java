package gr.mariakapa.cinema.RequestResponse;

import gr.mariakapa.cinema.Entity.Movie;
import gr.mariakapa.cinema.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class ReservationMovie {

     private User user;

     private List<Movie> movies = new ArrayList<>();


     public ReservationMovie() {
     }


     public ReservationMovie(User user, List<Movie> movies) {
          this.user = user;
          this.movies = movies;
     }

     public User getUser() {
          return user;
     }

     public void setUser(User user) {
          this.user = user;
     }

     public List<Movie> getMovies() {
          return movies;
     }

     public void setMovies(List<Movie> movies) {
          this.movies = movies;
     }
}
