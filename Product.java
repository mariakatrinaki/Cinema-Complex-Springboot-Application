package gr.mariakapa.cinema.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name ="id_product")
    private int idProduct;

    @Column(name = "product_Name",nullable = false,length = 100,unique = true)
    private String productName;

    //@Column(name="quantity",nullable = false)
    //private int quantity;

   @Column(name = "image",nullable= false)
   private String image;

    @Column(name = "price",nullable= false)
    private double price;


    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade= CascadeType.ALL)
    private List<OrderProduct> orderDetails = new ArrayList<>();


    @OneToMany(mappedBy = "product",cascade= CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();



    public Product (){

    }

    public List<OrderProduct> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderProduct> orderDetails) {
        this.orderDetails = orderDetails;
    }


    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", productName='" + productName + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", orderDetails=" + orderDetails +
                ", cartItems=" + cartItems +
                '}';
    }
}
