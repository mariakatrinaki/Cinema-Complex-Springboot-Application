package gr.mariakapa.cinema.Repository;

import gr.mariakapa.cinema.Entity.Movie;
import gr.mariakapa.cinema.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    public Product findAllByIdProduct(int idProduct);

    public Product findAllByProductName(String productName);


     @Query(value = "select * from product left JOIN "+
                      "cart_item On cart_item.id_product = product.id_product where cart_item.id_shopping_cart=:id_shopping_cart",nativeQuery = true)
     List<Product> findByCartItems_ShoppingCart_IdShoppingCart(@Param("id_shopping_cart") int idShoppingCart);

    @Query(value = "select * from product left JOIN " +
            "order_product On order_product.id_product = product.id_product " +
            "where order_product.id_order=:id_order",nativeQuery = true)

    public List<Product> findAllByOrderDetails(@Param("id_order") int idOrder);



    @Transactional
    @Modifying
    @Query(value = "delete order_product from order_product " +
            "where order_product.id_order=:id_order and " +
            "order_product.id_product=:id_product",nativeQuery = true)
    public void deleteAllByIdProduct(@Param("id_order") int idOrder,@Param("id_product") int idProduct);







}
