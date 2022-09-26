package gr.mariakapa.cinema.Entity;


import javax.persistence.*;

@Entity
@Table
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seat")
    private int idSeat;


    @Column(name= "number_seat",nullable = false,length = 100,unique = true)
    private int numberSeat;

    @OneToOne(mappedBy = "seat")
    private Ticket ticket;


    public Seat(int idSeat, int numberSeat, Ticket ticket) {
        this.idSeat = idSeat;
        this.numberSeat = numberSeat;
        this.ticket = ticket;
    }

    public Seat() {
    }

    public Seat(int idSeat, int numberSeat) {
        this.idSeat = idSeat;
        this.numberSeat = numberSeat;
    }

    public int getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(int idSeat) {
        this.idSeat = idSeat;
    }

    public int getNumberSeat() {
        return numberSeat;
    }

    public void setNumberSeat(int numberSeat) {
        this.numberSeat = numberSeat;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "idSeat=" + idSeat +
                ", numberSeat=" + numberSeat +
                ", ticket=" + ticket +
                '}';
    }
}
