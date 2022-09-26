package gr.mariakapa.cinema.RequestResponse;

import gr.mariakapa.cinema.Entity.CartItem;
import gr.mariakapa.cinema.Entity.User;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDao {


    private int idShoppingCart;


    private User user;


    private List<CartItem> cartItems = new ArrayList<>();

      private List<CartItemDao> cartItemDaos = new ArrayList<>();


      private double totalCost;
    public ShoppingCartDao() {
    }

    public ShoppingCartDao(int idShoppingCart, User user, List<CartItem> cartItems) {
        this.idShoppingCart = idShoppingCart;
        this.user = user;
        this.cartItems = cartItems;
    }

    public void addShoppingCarts(List<CartItem> cartItems) {
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


    public List<CartItemDao> getCartItemDaos() {
        return cartItemDaos;
    }

    public void setCartItemDaos(List<CartItemDao> cartItemDaos) {
        this.cartItemDaos = cartItemDaos;
    }

    public double getTotalCost() {
        double result=0;
        for(CartItem i: this.getCartItems()){
            result+= i.getCost();

        }


        return this.totalCost=result;

    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}