package gr.mariakapa.cinema.Service;


import gr.mariakapa.cinema.Entity.Movie;
import gr.mariakapa.cinema.Entity.ShowTime;
import gr.mariakapa.cinema.Repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service

public class ShowTimeService {

    @Autowired
    ShowTimeRepository showTimeRepository;

    boolean alreadyExistShowTime= false;
    public boolean checkShowTime(ShowTime showTime) throws ParseException {



        LocalDateTime ldt = LocalDateTime.parse(showTime.getStartMovie().toString(), DateTimeFormatter.ISO_DATE_TIME);
         String timeNewMovie = ldt.format(DateTimeFormatter.ofPattern("HH:mm"));
        System.out.println("Time: "+timeNewMovie);



        Date dateNewMovie = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(showTime.getStartMovie().toString());
        String newStrStartNewMovie = new SimpleDateFormat("yyyy-MM-dd").format(dateNewMovie);
        System.out.println("Date: "+newStrStartNewMovie);



        List<ShowTime> listShowTime = showTimeRepository.findAll();// correct this for all show time
        if(!listShowTime.isEmpty()) {
            System.out.println(listShowTime.isEmpty());
            for (ShowTime s : listShowTime) {

                Date dateOldMovie = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(s.getStartMovie().toString());
                String newStrStartOldMovie = new SimpleDateFormat("yyyy-MM-dd").format(dateOldMovie);
                System.out.println("Date: "+newStrStartOldMovie);


                if(newStrStartOldMovie.equals(newStrStartNewMovie)){
                    LocalDateTime ldtOld = LocalDateTime.parse(s.getStartMovie().toString(), DateTimeFormatter.ISO_DATE_TIME);
                    String timeOldMovie = ldtOld.format(DateTimeFormatter.ofPattern("HH:mm"));
                     SimpleDateFormat dfOld = new SimpleDateFormat("HH:mm");
                    Date d = dfOld.parse(timeOldMovie);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    cal.add(Calendar.MINUTE, s.getMovie().getMovieDuration()+30);
                    String newTime = dfOld.format(cal.getTime());
                   // System.out.println("Total of already showTime: "+newTime);
                    Date totalEndShowTimeOld = dfOld.parse(newTime);
                    SimpleDateFormat dfNew = new SimpleDateFormat("HH:mm");
                    Date showTimeNew = dfNew.parse(timeNewMovie);
                    System.out.println("Total of already showTime: "+totalEndShowTimeOld.toString());
                    System.out.println("Total of new showTime: "+showTimeNew.toString());
                    if(showTimeNew.after(totalEndShowTimeOld)){
                       // showTimeRepository.save(showTime);
                        System.out.println("The movie show time movie can store to database hey");
                        alreadyExistShowTime = false;
                      //  totalEndShowTimeOld = null;

                    }else{
                        System.out.println("The movie show time cant store to database");
                        alreadyExistShowTime = true;
                    }

                }else{
                    System.out.println("The  movie can store to database");
                    System.out.println("Movie "+showTime.getMovie());
                    System.out.println("ShowTime "+showTime.getStartMovie().toString());

                    alreadyExistShowTime = false;
                }

                }

            }
        else {
            alreadyExistShowTime = false;
        }

        listShowTime.clear();

        return alreadyExistShowTime;

    }

 public void save(ShowTime showTime){

        showTimeRepository.save(showTime);
 }

    public List<ShowTime> findByMovieId(int movieId){
       return showTimeRepository.findByMovie_IdMovie(movieId);

    }
    //   return  showTimeRepository.findByMovie_IdMovie(movie);
   // }


    public ShowTime findbyShowTimeId(int idShowTime){

        return showTimeRepository.findByIdShowTime(idShowTime);
    }


}
