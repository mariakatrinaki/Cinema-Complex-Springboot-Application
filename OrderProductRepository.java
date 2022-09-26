package gr.mariakapa.cinema.Repository;

import gr.mariakapa.cinema.Entity.Order;
import gr.mariakapa.cinema.Entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
    List<OrderProduct> findByOrder(Order order);




}
