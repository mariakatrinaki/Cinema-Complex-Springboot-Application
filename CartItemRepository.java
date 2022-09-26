package gr.mariakapa.cinema.Repository;

import gr.mariakapa.cinema.Entity.CartItem;
import gr.mariakapa.cinema.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CartItemRepository  extends JpaRepository<CartItem,Integer> {
    CartItem findByProduct_IdProduct(int idProduct);

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);



    @Transactional
    @Modifying
    @Query(value = "update cart_item  " +
            "set cart_item.quantity=:quantity where " +
            "cart_item.id_product=:id_product and cart_item.id_shopping_cart=:idShoppingCart",nativeQuery = true)
    public void updateAllByIdProduct(@Param("id_product") int idProduct,@Param("quantity") int quantity,
                                      @Param("idShoppingCart") int idShoppingCart);


    @Transactional
    @Modifying
    @Query(value = "update cart_item  " +
            "set cart_item.cost=:cost where " +
            "cart_item.id_product=:id_product and cart_item.id_shopping_cart=:idShoppingCart",
            nativeQuery = true)
    public void updateAllByIdProduct(@Param("id_product") int idProduct,@Param("cost") double cost,
                                     @Param("idShoppingCart") int idShoppingCart);




    @Transactional
    @Modifying
    @Query(value = "delete cart_item from cart_item " +
            "where cart_item.id_shopping_cart=:idShoppingCart and " +
            "cart_item.id_product=:id_product",nativeQuery = true)
    public void deleteAllByIdCartItem(@Param("idShoppingCart") int idShoppingCart,@Param("id_product") int idProduct);




    @Transactional
    @Modifying
    @Query(value = "delete cart_item from cart_item " +
            "where cart_item.id_shopping_cart=:idShoppingCart "
            ,nativeQuery = true)
    public void  deleteByShoppingCart(@Param("idShoppingCart") int idShoppingCart);


}
