package gr.mariakapa.cinema.Repository;

import gr.mariakapa.cinema.Entity.Movie;
import gr.mariakapa.cinema.Entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime,Integer> {


    ShowTime findByIdShowTime(int idShowTime);

    List<ShowTime> findByMovie_IdMovie(int idMovie);



    //List<ShowTime> findByIdShowTime(int idShowTime);










}
