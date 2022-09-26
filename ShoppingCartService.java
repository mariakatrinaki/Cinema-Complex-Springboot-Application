package gr.mariakapa.cinema.Service;


import gr.mariakapa.cinema.Entity.*;
import gr.mariakapa.cinema.Repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {


    @Autowired
    ProductService productService;


     @Autowired
    ShoppingCartRepository shoppingCartRepository;


    public void saveShoppingCart(ShoppingCart cart) {


             ShoppingCart newCart = new ShoppingCart();
             newCart.setUser(cart.getUser());
             newCart.getCartItems().addAll((
                     cart.getCartItems()
                             .stream()
                             .map(cartItem -> {
                                 Product product = productService.findProductById(cartItem.getProduct().getIdProduct());
                                 // OrderProduct orderProduct = OrderProductRepository.findByIdOrderAndIdProduct(Long id)

                                 CartItem newCartItem = new CartItem();
                                 newCartItem.setProduct(product);
                                 newCartItem.setShoppingCart(newCart);
                                 newCartItem.setQuantity(cartItem.getQuantity());
                                 return newCartItem;

                             })
                             .collect(Collectors.toList())
             ));
             shoppingCartRepository.save(newCart);
         }





    public void update(ShoppingCart oldcart,ShoppingCart newCart) {


        oldcart.addShoppingCarts(newCart.getCartItems());



            System.out.println("hello");
            shoppingCartRepository.save(oldcart);

        }
/*
        public void save(ShoppingCart shoppingCart){
         ShoppingCart shoppingCart1=  shoppingCartRepository.findByIdShoppingCart(shoppingCart.getIdShoppingCart());

          if(shoppingCart1.getCartItems().isEmpty()){
              //shoppingCart.setTotalCost(totalCost);
              shoppingCartRepository.save(shoppingCart);
              System.out.println("hey");
          }else if(!shoppingCart1.getCartItems().isEmpty()){
              shoppingCartRepository.updateByIdShoppingCart(shoppingCart1.getIdShoppingCart(),
                      shoppingCart.getTotalCost());
              System.out.println("bye");
          }



        }
*/

}
