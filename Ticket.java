package gr.mariakapa.cinema.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ticket",
        uniqueConstraints = { @UniqueConstraint(columnNames =
                { "id_showtime", "id_seat" }) })
public class Ticket  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket",nullable = false)
    private int idTickets;

    @ManyToOne
    @JoinColumn(name ="id_user",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name ="id_showtime",nullable = false)
    private ShowTime showTime;


    @OneToOne
    @JoinColumn(name = "id_seat", referencedColumnName = "id_seat",nullable = false)
    private Seat seat;


    @Column(name ="ticket_cost",nullable = false)
    private double ticketCost;

    @Column(name ="pay")
    private String pay;




    public Ticket(User user, ShowTime showTime, Seat seat,double ticketCost ,String pay) {

        this.user = user;
        this.showTime = showTime;
        this.seat = seat;
         this.ticketCost= ticketCost;
        this.pay = pay;
    }

    public Ticket() {
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

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }


    public double getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(double ticketCost) {
        this.ticketCost = ticketCost;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTickets=" + idTickets +
                ", user=" + user +
                ", showTime=" + showTime +
                ", seat=" + seat +
                ", ticketCost=" + ticketCost +
                ", pay=" + pay +
                '}';
    }
}
