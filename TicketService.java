package gr.mariakapa.cinema.Service;


import gr.mariakapa.cinema.Entity.*;
import gr.mariakapa.cinema.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class TicketService {


    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserService userService;

    @Autowired
    SeatService seatService;

    @Autowired
    ShowTimeService showTimeService;



    public Ticket saveTicket(Ticket ticket){
        Ticket newTicket = new Ticket();
        if(userService.findUserById(ticket.getUser().getIdUser())!=null){
            newTicket.setUser(ticket.getUser());
           // newTicket.getUser().addTicket(newTicket);
            // userService.save_user(newTicket.getUser());

        }if(seatService.findByIdSeat(ticket.getSeat().getIdSeat())!=null){

            newTicket.setSeat(ticket.getSeat());
           // newTicket.getSeat().setTicket(newTicket);
           // seatService.saveSeat(newTicket.getSeat());
        }if(showTimeService.findbyShowTimeId(ticket.getShowTime().getIdShowTime())!=null){
            newTicket.setShowTime(ticket.getShowTime());
           // newTicket.getShowTime().addTicket(newTicket);
           // showTimeService.save(newTicket.getShowTime());
        }

        newTicket.setTicketCost(ticket.getTicketCost());
        newTicket.setPay(ticket.getPay());
        System.out.println("Username: "+newTicket.getUser().getIdUser());
        System.out.println("idSeat: "+newTicket.getSeat().getIdSeat());
        System.out.println("ShowTime: "+newTicket.getShowTime().getIdShowTime());
        System.out.println("Cost: "+newTicket.getTicketCost());
        System.out.println("Pay: "+newTicket.getPay());

         ticketRepository.save(newTicket);
        return newTicket;

    }

    public List<Integer> findTicketByShowTimeId(int id){
       List<Integer> selectedSeats = new ArrayList<>();
        for(Ticket t : ticketRepository.findByShowTime_IdShowTime(id)){
            selectedSeats.add(t.getSeat().getIdSeat());
        }



     return selectedSeats;
    }

    public void updateTicketStatus(int idTicket,String pay){

        ticketRepository.updateByIdTicket(idTicket,pay);


    }




}
