package gr.mariakapa.cinema.Service;


import gr.mariakapa.cinema.Entity.Genre;
import gr.mariakapa.cinema.Entity.Movie;
import gr.mariakapa.cinema.Entity.ShowTime;
import gr.mariakapa.cinema.Repository.GenreRepository;
import gr.mariakapa.cinema.Repository.MovieRepository;
import gr.mariakapa.cinema.RequestResponse.MovieRequest;
import gr.mariakapa.cinema.Service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {


    @Autowired
   private MovieRepository movieRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
   private GenreRepository genreRepository;

    @Autowired
    private ShowTimeService showTimeService;


    public MovieRequest addMovie(MovieRequest movie) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);

        Date dateTime = cal.getTime();


        Set<Genre> genres = new HashSet<>();
       Movie newMovie = new Movie();
       Genre genre  = new Genre();
       List<ShowTime> listShowTime = new ArrayList<>();
      // newMovie.setId_Movie(movie.getId_Movie());
       newMovie.setDescription(movie.getDescription());
       newMovie.setTitle(movie.getTitle());
       newMovie.setActors(movie.getActors());
       newMovie.setDirector(movie.getDirector());

      // newMovie.setListShowTime(movie.getListShowTime());
       newMovie.setPrice(movie.getPrice());
       newMovie.setRating(movie.getRating());
       newMovie.setImage(movie.getImage());
       newMovie.setMovieDuration(movie.getMovieDuration());
        for(ShowTime showTime1: movie.getListShowTime()){
            LocalDateTime ldtOld = LocalDateTime.parse(showTime1.getStartMovie().toString(), DateTimeFormatter.ISO_DATE_TIME);
            String timeOldMovie = ldtOld.format(DateTimeFormatter.ofPattern("HH:mm"));
            SimpleDateFormat dfOld = new SimpleDateFormat("HH:mm");
            Date d = dfOld.parse(timeOldMovie);
            Calendar calOld = Calendar.getInstance();
            calOld.setTime(d);
            String newTime = dfOld.format(calOld.getTime());
            Date startShowTime = dfOld.parse(newTime);

           System.out.println(dateTime.toString());// να το ξαναδώ
            System.out.println(startShowTime.toString());
            if(startShowTime.before(dateTime)) {
                boolean isAlreadyExistShowTime = showTimeService.checkShowTime(showTime1);
                System.out.println(isAlreadyExistShowTime);
                if (isAlreadyExistShowTime == false) {
                    ShowTime showTime = new ShowTime();
                    showTime.setStartMovie(showTime1.getStartMovie());
                    showTime.setMovie(newMovie);
                    listShowTime.add(showTime);

                }
            }else{
                System.out.println("Time startMovie must be before "+dateTime);
            }

            // System.out.println("Time startMovie"+showTime.getStartMovie());


        }
        newMovie.setListShowTime(listShowTime);
       for(Integer integer: movie.getGenres()){
           genres.add(
                genre =  genreRepository.findByIdGenre(integer)
           );
           genre.addMovies(newMovie);
       }


       newMovie.setGenres(genres);
       if(!newMovie.getListShowTime().isEmpty()){
           movieRepository.save(newMovie);

           for(ShowTime s: listShowTime){
               showTimeService.save(s);
           }
       }


        listShowTime.clear();






        return movie;

    }

    public List<Movie> getMovies() {

        return movieRepository.findAll();
    }

    public List<Movie> getMoviesByGenre(int id){

        return movieRepository.findByMovies_GenreId(id);
    }

    public Movie findMovieById(int id) {

        return movieRepository.findById(id).orElse(null);
    }

    public List<Movie> findMovieByTitle(String title) {

        return movieRepository.findByKeyword(title);
    }

   public void deleteMovieById(int id){

       Movie movie = movieRepository.findByIdMovie(id);

   }




}
