package gr.mariakapa.cinema.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Genre {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private int idGenre;

    @Column(nullable = false,length= 100, unique=true)
    private String name;


    @Column(unique = true)
    @ManyToMany(cascade = {   CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    @JoinTable(name ="genre_movie",
            joinColumns = @JoinColumn(name = "id_genre"),
            inverseJoinColumns = @JoinColumn(name = "id_movie"))
    private Set<Movie> movies = new HashSet<>();


    public Genre(String name) {
        this.name = name;
    }

    public Genre(){


    }


    public void removeMovie(Movie movie){
        movies.remove(movie);
        movie.getGenres().remove(this);
    }

    public Set<Movie> getMovies() {
        return movies;
    }


    public void addMovies(Movie movie) {
        movies.add(movie);

       // this.movies = movies;
    }

    public void saveMovies(Set<Movie> movies){
        this.movies = movies;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id_Genre=" + idGenre +
                ", name='" + name + '\'' +
                '}';
    }
}
