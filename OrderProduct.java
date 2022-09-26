package gr.mariakapa.cinema.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="order_product")
public class OrderProduct  {

    @Id
    @Column(name = "id_order_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderProductId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_order")
    @JsonIgnore
    private Order order;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_product")
    private Product product;


    private int quantity;


    private double cost;




    public OrderProduct() {
    }

    public OrderProduct(int orderProductId, Order order, Product product, int quantity,double cost) {
        this.orderProductId = orderProductId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;


    }

    public OrderProduct(Product product, int quantity,double cost) {

        this.product = product;
        this.quantity = quantity;
        this.cost = cost;


    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity>0){
        this.quantity = quantity;
        }
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
    }


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
