package gr.mariakapa.cinema.Service;

import gr.mariakapa.cinema.Entity.Seat;
import gr.mariakapa.cinema.Repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

     @Autowired
    SeatRepository seatRepository;


     public Seat saveSeat(Seat seat){

         return seatRepository.save(seat);
     }


     public Seat findByIdSeat(int idSeat){

         return seatRepository.findByIdSeat(idSeat);
     }

     public List<Seat> findAll(){

         return seatRepository.findAll();
     }


     public Seat findByNumberSeat(int numberSeat){
         return seatRepository.findByNumberSeat(numberSeat);
     }



}
