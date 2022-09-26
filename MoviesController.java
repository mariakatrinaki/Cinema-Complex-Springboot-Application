package gr.mariakapa.cinema.Controller;

import gr.mariakapa.cinema.Entity.*;
import gr.mariakapa.cinema.Repository.UserRepository;
import gr.mariakapa.cinema.RequestResponse.ReservationMovie;
import gr.mariakapa.cinema.RequestResponse.TicketDto;
import gr.mariakapa.cinema.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")

public class MoviesController {


    @Autowired
    UserRepository userRepository;


    @Autowired
    UserService userService;


    @Autowired
    MovieService movieService;

    @Autowired
    ShowTimeService showTimeService;


    @Autowired
    SeatService seatService;

    @Autowired
    TicketService ticketService;


    @Autowired
    GenreService genreService;

   private Integer idMovie;

   private TicketDto ticketDto = new TicketDto();








    @GetMapping( "Movies")
    public String moviesPage(Model model,String keyword,String category ,@AuthenticationPrincipal UserDetails currentUser) {
        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role",user.getUsertype().getType());
        List<Movie> moviesByTitle = movieService.getMovies();
        List<Movie> moviesByCategory = new ArrayList<Movie>();

        ReservationMovie reservationMovie = new ReservationMovie();

       if(keyword !=null || category!=null) {

           if(keyword!=null) {
               reservationMovie.setMovies(movieService.findMovieByTitle(keyword));

               model.addAttribute("reservationMovie", reservationMovie);
           }
           if(category!=null){
               if(category.equals("all")){
                   reservationMovie.setMovies(moviesByTitle);
                   model.addAttribute("reservationMovie", reservationMovie);
               }else {

                   for (Movie m : genreService.findByName(category).getMovies()) {
                       moviesByCategory.add(m);
                   }

                   reservationMovie.setMovies(moviesByCategory);
                   model.addAttribute("reservationMovie", reservationMovie);
               }
           }

       }else{

           reservationMovie.setMovies(moviesByTitle);
           model.addAttribute("reservationMovie",reservationMovie);
       }







        return "Visitor/Movies";
    }

    @PostMapping( "MovieDetails")
    public String movieDetails(Model model, @AuthenticationPrincipal UserDetails currentUser,
                               @RequestParam(name = "item") int id) {
        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role",user.getUsertype().getType());


        idMovie=id;

        System.out.println("Movie id"+idMovie);

        String url = "redirect:/MovieDetails";

        return url;
    }



    @GetMapping( "MovieDetails")
    public String movieDetails(Model model, @AuthenticationPrincipal UserDetails currentUser)
                                {
        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role",user.getUsertype().getType());
        Movie movie = movieService.findMovieById(idMovie);

        List<ShowTime> showTimeList = showTimeService.findByMovieId(movie.getId_Movie());

        ticketDto.setMovie(movie);
        ticketDto.setShowTimeList(showTimeList);
        ticketDto.setUser(user);


        model.addAttribute("ticketDto",ticketDto);


        return "Visitor/MovieDetails";
    }

    @PostMapping( "Seat")
    public String Seat(Model model, @AuthenticationPrincipal UserDetails currentUser,
                             @RequestParam(name = "idShowTime", required = false) int idShowTime
                            ) {
        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role", user.getUsertype().getType());

       // System.out.println("Movie Title" + ticketDto.getMovie().getId_Movie());
        if (idShowTime!=0) {

            ticketDto.setShowTime(showTimeService.findbyShowTimeId(idShowTime));
            ticketDto.setNonSelectedSeats(seatService.findAll());
            ticketDto.setSelectedSeats(ticketService.findTicketByShowTimeId(ticketDto.getShowTime().getIdShowTime()));
            for(Seat s: ticketDto.getNonSelectedSeats() ){
               // System.out.println("Not occupied seats"+ s.getIdSeat());
            }
            for(Integer i: ticketDto.getSelectedSeats() ){
               // System.out.println("occupied seats"+ i);
            }


            model.addAttribute("ticketDto",ticketDto);


        }

        return "Visitor/Seat";
    }
    @GetMapping("Seat")
    public String selectSeat(Model model, @AuthenticationPrincipal UserDetails currentUser)
                             {
        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role", user.getUsertype().getType());
                                 for(Seat s: ticketDto.getNonSelectedSeats() ){
                                      System.out.println("Not occupied seats"+ s.getIdSeat());
                                 }
                                 for(Integer i: ticketDto.getSelectedSeats() ){
                                      System.out.println("occupied seats"+ i);
                                 }


        model.addAttribute("ticketDto",ticketDto);



        return "Visitor/Seat";
    }

    @PostMapping("BookingSeat")
    public String selectSeat(Model model, @AuthenticationPrincipal UserDetails currentUser,
                             @RequestParam(value = "action", required = false) String action,
                             @RequestParam(name = "seatNumbers", required = false) String[] seatNumbers){
        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role", user.getUsertype().getType());

        Ticket ticket = null;

       ArrayList<TicketDto> ticketsList = new ArrayList<>();
         // "3, 5, 7, 10"
        if(!seatNumbers.equals(" ")) {
            for (String s : seatNumbers) {
                if(!s.equals(",")) {
                    TicketDto ticketDto1 = new TicketDto();
                    ticketDto1.setUser(ticketDto.getUser());
                    ticketDto1.setMovie(ticketDto.getMovie());
                    ticketDto1.setShowTime(ticketDto.getShowTime());
                    ticketDto.addSelectedSeatS(Integer.parseInt(s));
                    ticketDto1.setSeat(seatService.findByNumberSeat(Integer.parseInt(s)));
                    ticketDto1.setTicketCost(ticketDto.getMovie().getPrice());
                    ticketDto1.setPay(action);
                    ticketsList.add(ticketDto1);
                }
            }
        }

        for (TicketDto t : ticketsList) {
              ticket  = new Ticket(t.getUser(),t.getShowTime(),t.getSeat(),t.getTicketCost()
              ,t.getPay());
            ticketService.saveTicket(ticket);

        }

       // System.out.println("Movie: "+ ticket.g);
      //  System.out.println("ShowTime: "+ ticket.getShowTime().getStartMovie());
       // System.out.println("Seat: "+ ticket.getSeat().getIdSeat());
       // System.out.println("TicketCost: "+ ticket.getTicketCost());
        //System.out.println("Pay: "+ ticket.getPay());



        String url = "redirect:/BookMovies";

        return url;
    }

    @GetMapping( "BookMovies")
    public String bookingMovie(Model model, @AuthenticationPrincipal UserDetails currentUser){

        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role", user.getUsertype().getType());


        model.addAttribute("tickets",user.getTickets());


        return "Visitor/BookMovies";
    }

    @PostMapping("changeStatusMovie")
    public String changeStatusOrder(Model model,@AuthenticationPrincipal UserDetails currentUser,
                                    @RequestParam(value="action", required=false)  String action,
                                    @RequestParam("idTicket") int idTicket) {

        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role", user.getUsertype().getType());



        if (action.equals("Pay")) {
            ticketService.updateTicketStatus(idTicket, "Pay");
        }


        String url = "redirect:/BookMovies";

        return url;


    }

}
