package gr.mariakapa.cinema.Service;


import gr.mariakapa.cinema.Entity.Genre;
import gr.mariakapa.cinema.Entity.Movie;
import gr.mariakapa.cinema.Repository.GenreRepository;
import gr.mariakapa.cinema.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class GenreService {

     @Autowired
     GenreRepository genreRepository;

     @Autowired
     MovieRepository movieRepository;

     @Autowired
     MovieService movieService;

    public Genre saveGenre(Genre genre) {



        /*
        Genre newGenre  = new Genre();
        newGenre.setIdGenre(genre.getIdGenre());
        newGenre.setName(genre.getName());
        newGenre.getMovies().addAll(genre
                .getMovies()
                .stream()
                .map( m -> {

                    Movie mm = movieService.findMovieByTitle(m.getTitle());

                    mm.addGenre(newGenre);
                    return mm;

                }).collect(Collectors.toList()));


         */


        return genreRepository.save(genre);


    }

     public List<Genre> getGenres(){
        return genreRepository.findAll();
     }

     public Genre findByName(String name){

        return genreRepository.findAllByName(name);
     }




    public Genre findGenreById(int id_Genre) {

       return genreRepository.findByIdGenre(id_Genre);
    }

    /*
  public List<Movie> genre_movie(int id){

        return  movieRepository.findByMovies_GenreId(id);
    }
    */

    public List<Movie> genre_movie(int id){

        return movieRepository.findByMovies_GenreId(id);
    }
    public void deleteGenreById(int id){
        genreRepository.deleteById(id);

    }



}
