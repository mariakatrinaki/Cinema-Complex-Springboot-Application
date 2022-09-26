package gr.mariakapa.cinema.RequestResponse;

import gr.mariakapa.cinema.Entity.Product;
import gr.mariakapa.cinema.Entity.ShoppingCart;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import javax.persistence.*;

public class CartItemDao {

    private int idCartItem;








    private Product product;


    private int quantity;


    public CartItemDao() {
    }

    public CartItemDao(int idCartItem, Product product, int quantity) {
        this.idCartItem = idCartItem;

        this.product = product;
        this.quantity = quantity;
    }

    public int getIdCartItem() {
        return idCartItem;
    }

    public void setIdCartItem(int idCartItem) {
        this.idCartItem = idCartItem;
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



}
