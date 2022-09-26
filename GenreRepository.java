package gr.mariakapa.cinema.Repository;

import gr.mariakapa.cinema.Entity.Genre;
import gr.mariakapa.cinema.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository  extends JpaRepository<Genre,Integer> {


   public Genre findAllByName(String name);

   // List<Genre> findByMovies_Genres_Id_Genre(int id_Genre);

    Genre findByIdGenre(int idGenre);



    List<Genre> findAll();









   /*
   SELECT coupon FROM Customer c JOIN c.coupons coupon
   WHERE c.id = :customerId AND coupon.id = :couponId

    */
}
