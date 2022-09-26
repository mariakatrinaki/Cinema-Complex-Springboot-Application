package gr.mariakapa.cinema.RequestResponse;

import gr.mariakapa.cinema.Entity.Movie;
import gr.mariakapa.cinema.Entity.Seat;
import gr.mariakapa.cinema.Entity.ShowTime;
import gr.mariakapa.cinema.Entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDto {


    private int idTickets;


    private User user;

    private ShowTime showTime;

    private List<ShowTime> showTimeList = new ArrayList<>();

    private List<Integer>  selectedSeats = new ArrayList<>();

    private List<Seat>  nonSelectedSeats = new ArrayList<>();

    private Seat seat;

    private double ticketCost;


    private String pay;

    private Movie movie;

    private boolean buttonClick;

    public TicketDto(int idTickets, User user, ShowTime showTime, Seat seat, double ticketCost, String pay, Movie movie) {
        this.idTickets = idTickets;
        this.user = user;
        this.showTime = showTime;
        this.seat = seat;
        this.ticketCost = ticketCost;
        this.pay = pay;
        this.movie = movie;
    }

    public TicketDto() {
    }

    public int getIdTickets() {
        return idTickets;
    }

    public void setIdTickets(int idTickets) {
        this.idTickets = idTickets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public int showTimeId(){

        return this.showTime.getIdShowTime();
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }


    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public double getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(double ticketCost) {
        this.ticketCost = ticketCost;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


    public List<ShowTime> getShowTimeList() {
        return showTimeList;
    }

    public void setShowTimeList(List<ShowTime> showTimeList) {
        this.showTimeList = showTimeList;
    }




    public List<Seat> getNonSelectedSeats() {
        return nonSelectedSeats;
    }

    public void setNonSelectedSeats(List<Seat> nonSelectedSeats) {
        this.nonSelectedSeats = nonSelectedSeats;
    }


    public List<Integer> getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(List<Integer> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public void  printArrayShowTime(){
        for(ShowTime s: this.showTimeList){
            System.out.println(s.getIdShowTime());
        }

   }


   public void addSelectedSeatS(int numberSeat){
        this.selectedSeats.add(numberSeat);
   }


    @Override
    public String toString() {
        return "TicketDto{" +
                "user=" + user +
                ", showTime=" + showTime +
                ", seat=" + seat +
                ", ticketCost=" + ticketCost +
                ", pay='" + pay + '\'' +
                ", movie=" + movie +
                '}';
    }
}
