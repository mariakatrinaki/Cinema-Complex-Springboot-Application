package gr.mariakapa.cinema.Service;


import gr.mariakapa.cinema.Entity.CartItem;
import gr.mariakapa.cinema.Entity.ShoppingCart;
import gr.mariakapa.cinema.Repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;




    public void saveCartItem(CartItem cartItem, ShoppingCart shoppingCart){
       boolean  isSave = false;
        CartItem cartItem1 = cartItemRepository.findByProduct_IdProduct(cartItem.getProduct().getIdProduct());
         int totalQuantity=0;
         double cost=0;


        if(cartItem1== null){
            cartItem.setShoppingCart(shoppingCart);
            cost=cartItem.getCost();
            cartItemRepository.save(cartItem);


        }else if(cartItem1!=null){

            cartItemRepository.updateAllByIdProduct(cartItem1.getProduct().getIdProduct(),cartItem1.getQuantity()+cartItem.getQuantity(),
                                                    shoppingCart.getIdShoppingCart() );

            totalQuantity= cartItem1.getQuantity()+cartItem.getQuantity();

            cost += totalQuantity *cartItem1.getProduct().getPrice();

            cartItemRepository.updateAllByIdProduct(cartItem1.getProduct().getIdProduct(),cost,
                   shoppingCart.getIdShoppingCart());




        }



    }



}
