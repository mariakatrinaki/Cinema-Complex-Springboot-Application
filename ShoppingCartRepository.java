package gr.mariakapa.cinema.Repository;


import gr.mariakapa.cinema.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {
    ShoppingCart findByIdShoppingCart(int idShoppingCart);

    ShoppingCart findByUser_IdUser(int idUser);



    List<ShoppingCart> getAllByIdShoppingCart(int id);



}
