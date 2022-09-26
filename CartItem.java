package gr.mariakapa.cinema.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import javax.persistence.*;

@Entity

@Table
public class CartItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// AUTOINCREMENT 1,2,3 NOT SEQUENCE IN TABLE
    @Column(name ="id_cartItem")
    private int idCartItem;


    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_shoppingCart")
    private ShoppingCart  shoppingCart;



    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_product")
    private Product product;


    private int quantity;


    private double cost;


    public CartItem() {
    }

    public CartItem(int idCartItem, ShoppingCart shoppingCart, Product product, int quantity, double cost) {
        this.idCartItem = idCartItem;
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getIdCartItem() {
        return idCartItem;
    }

    public void setIdCartItem(int idCartItem) {
        this.idCartItem = idCartItem;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
