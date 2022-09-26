package gr.mariakapa.cinema.RequestResponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gr.mariakapa.cinema.Entity.Genre;
import gr.mariakapa.cinema.Entity.ShowTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieRequest {
    private int  id_Movie;

    private String title;

    private String description;


    private String actors;

    private String director;


    private List<ShowTime> listShowTime;


    private double price;


    private String rating;


    private String image;

    private int movieDuration;


    private Set<Integer> genres = new HashSet<>();



    public Integer findGenre(int idGenre){
        int intGenre = 0;
        for(Integer in : genres){
           if(in == idGenre){
               intGenre = in;
           }
        }

        return intGenre;
    }

    public Set<Integer> getGenres() {
        return genres;
    }

    public void setGenres(Set<Integer> genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId_Movie() {
        return id_Movie;
    }

    public void setId_Movie(int id_Movie) {
        this.id_Movie = id_Movie;
    }


    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<ShowTime> getListShowTime() {
        return listShowTime;
    }

    public void setListShowTime(List<ShowTime> listShowTime) {
        this.listShowTime = listShowTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }


    @Override
    public String toString() {
        return "MovieRequest{" +
                "id_Movie=" + id_Movie +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", actors='" + actors + '\'' +
                ", director='" + director + '\'' +
                ", listShowTime=" + listShowTime +
                ", price='" + price + '\'' +
                ", rating='" + rating + '\'' +
                ", image='" + image + '\'' +
                ", movieDuration=" + movieDuration +
                ", genres=" + genres +
                '}';
    }
}
