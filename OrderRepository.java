package gr.mariakapa.cinema.Repository;

import gr.mariakapa.cinema.Entity.Movie;
import gr.mariakapa.cinema.Entity.Order;
import gr.mariakapa.cinema.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Integer> {



    public Order findOrderByIdOrder(int id);

    List<Order> findByUser(User user);



    @Transactional
    @Modifying
    @Query(value = "delete  from cinema.order " +
            "where order.id_order=:id_order  "
            ,nativeQuery = true)
    public void deleteAllByIdOrder(@Param("id_order") int idOrder);


    @Transactional
    @Modifying
    @Query(value = "update cinema.order  " +
            "set order.order_status=:order_status where " +
            "order.id_order=:id_order",nativeQuery = true)

    public void updateByIdOrder(@Param("id_order") int idOrder,@Param("order_status") String orderStatus);






}
