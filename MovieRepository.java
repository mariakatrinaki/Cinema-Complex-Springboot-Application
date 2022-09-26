package gr.mariakapa.cinema.Repository;

import gr.mariakapa.cinema.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    public Movie findMovieByTitle(String title);

    Movie findByIdMovie(int idMovie);



    //List<Movie> findByGenres_Id_Genre(int id_Genre);


   @Query(value = "select * from movie left JOIN " +
           "genre_movie On genre_movie.id_movie = movie.id_movie " +
           "where genre_movie.id_genre=:id_genre",nativeQuery = true)
    List<Movie> findByMovies_GenreId(@Param("id_genre") int id_Genre);

    long deleteByIdMovie(int idMovie);


    @Query(value="select * from Movie m where m.title like %:keyword%",nativeQuery = true)
    List<Movie> findByKeyword(@Param("keyword") String keyword);



}
