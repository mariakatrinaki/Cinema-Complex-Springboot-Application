package gr.mariakapa.cinema.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table

public class ShowTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// AUTOINCREMENT 1,2,3 NOT SEQUENCE IN TABLE
    @Column(name ="id_showtime")

    private int idShowTime;




    @Column(name="start_movie",unique = true)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
  //  @Temporal(TemporalType.TIMESTAMP)
    private java.time.LocalDateTime startMovie;




    @OneToMany(mappedBy = "showTime",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();



    @ManyToOne(optional = false)
    @JoinColumn(name ="id_movie",nullable = false)
    private Movie movie;


    public ShowTime(int idShowTime, LocalDateTime  startMovie, Movie movie) {
        this.idShowTime = idShowTime;
        this.startMovie = startMovie;

        this.movie = movie;
    }


    public ShowTime(LocalDateTime startMovie, Movie movie) {
        this.startMovie = startMovie;

        this.movie = movie;
    }

    public ShowTime(LocalDateTime  startMovie) {
        this.startMovie = startMovie;


    }

    public ShowTime() {
    }


    public int getIdShowTime() {
        return idShowTime;
    }

    public void setIdShowTime(int idShowTime) {
        this.idShowTime = idShowTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


    public LocalDateTime  getStartMovie() {
        return startMovie;
    }

    public void setStartMovie(LocalDateTime  startMovie) {

        this.startMovie = startMovie;
    }


    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }


}
