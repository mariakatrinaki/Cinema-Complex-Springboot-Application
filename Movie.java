package gr.mariakapa.cinema.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import gr.mariakapa.cinema.Entity.Genre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table
public class Movie {


     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)// AUTOINCREMENT 1,2,3 NOT SEQUENCE IN TABLE
     @Column(name ="id_movie")
     private int  idMovie;

    @Column(nullable = false,length= 100, unique=true)
     private String title;




     @Lob
     @Column(nullable = false)
     private String description;// mark as @Lob for long text


    @Lob
    @Column(nullable=false)
    private String actors;

    @Lob
    @Column(nullable = false)
    private String director;



    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String rating;

    @Column(nullable = false,length= 100,unique = true)
    private String image;

    private int movieDuration;

    @OneToMany(mappedBy = "movie",cascade =CascadeType.ALL)
    private List<ShowTime> listShowTime= new ArrayList<>();
    @ManyToMany(mappedBy = "movies")
    @JsonIgnore
     private Set<Genre>  genres = new HashSet<>();

    public Movie(String title, String description, String actors, String director, List<ShowTime> listShowTime
            , double price, String rating, Set<Genre> genres,String image,int movieDuration) {
        this.title = title;
        this.description = description;
        this.actors = actors;
        this.director = director;
        this.listShowTime = listShowTime;
        this.price = price;
        this.rating = rating;
        this.genres = genres;
        this.image = image;
        this.movieDuration= movieDuration;
    }

    //@OneToMany(mappedBy = "movie")
    // private Set<Ticket> tickets;


   // public Set<Ticket> getTickets() {
    //    return tickets;
   // }

  //  public void setTickets(Set<Ticket> tickets) {
     //   this.tickets = tickets;
    //}

    public Set<Genre> getGenres() {
        return genres;
    }



    public void setGenres (Set<Genre> genres){
        this.genres = genres;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
            }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.getMovies().remove(this);
    }

    public void setId_Movie(int id_Movie) {
        this.idMovie = id_Movie;
    }

    public Movie(){


    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_Movie() {
        return idMovie;
    }

    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
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

    public void setImage(String image){
        this.image = image;
    }

    public String getImage(){

        return this.image;
    }

    public int getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "idMovie=" + idMovie +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", actors='" + actors + '\'' +
                ", director='" + director + '\'' +
                ", price='" + price + '\'' +
                ", rating='" + rating + '\'' +
                ", image='" + image + '\'' +
                ", movieDuration=" + movieDuration +
                ", listShowTime=" + listShowTime +
                ", genres=" + genres +
                '}';
    }
}
