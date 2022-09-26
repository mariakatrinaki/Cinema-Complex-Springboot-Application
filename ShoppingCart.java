package gr.mariakapa.cinema.Entity;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@Table

public class ShoppingCart {


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)// AUTOINCREMENT 1,2,3 NOT SEQUENCE IN TABLE
    @Column(name ="id_shopping_cart")
    private int idShoppingCart;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;


    @OneToMany(mappedBy = "shoppingCart",cascade= CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();



    public ShoppingCart() {
    }

    public ShoppingCart(int idShoppingCart, User user, List<CartItem> cartItems) {
        this.idShoppingCart = idShoppingCart;
        this.user = user;
        this.cartItems = cartItems;

    }

    public void addShoppingCarts(List<CartItem> cartItems){
        this.getCartItems().addAll(cartItems);
    }


    public int getIdShoppingCart() {
        return idShoppingCart;
    }

    public void setIdShoppingCart(int idShoppingCart) {
        this.idShoppingCart = idShoppingCart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }


}
