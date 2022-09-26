package gr.mariakapa.cinema.Repository;

import gr.mariakapa.cinema.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Integer> {

    Seat findByIdSeat(int idSeat);

    Seat findByNumberSeat(int numberSeat);


    List<Seat> findAll();







}
