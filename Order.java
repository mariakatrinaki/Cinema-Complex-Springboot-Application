package gr.mariakapa.cinema.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table
public class Order {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name ="id_order")
    private int idOrder;



    @ManyToOne(optional = false)
    @JoinColumn(name ="id_user",nullable = false)
    private User user;

    @Column(name="order_status",nullable = false)
    private String orderStatus;
    @OneToMany(mappedBy = "order",cascade= CascadeType.ALL)
    private List<OrderProduct> orderDetails = new ArrayList<>();



    private double totalCost;




    public Order(int idOrder,String orderStatus,User user, List<OrderProduct> orderDetails, double totalCost) {
        this.idOrder = idOrder;
        this.user = user;
        this.orderDetails = orderDetails;
        this.orderStatus = orderStatus;
        this.totalCost= totalCost;

    }


    public void addOrderProduct(OrderProduct orderProduct){
        this.orderDetails.add(orderProduct);
    }



    public List<OrderProduct> getOrderDetails() {
        return orderDetails;
    }


    public void setOrderDetails(List<OrderProduct> orderDetails) {
        this.orderDetails = orderDetails;
    }



    /*
    @Column(unique = true)
    @ManyToMany(cascade = {   CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    @JoinTable(name ="order_product",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_product"))
    private Set<Product> products  = new HashSet<Product>();


  */

    public Order() {
    }

    /*
    public Order(User user,Set<Product>products){
        this.user = user;
        this.products = products;
    }
    public Set<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
*/
    public Order(User user){
        this.user =user;

    }




    public int getIdOrder() {
        return idOrder;
    }

    @JsonBackReference// να το κοιτάξω
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }




    public String loopOrderDetails(){
        ArrayList<String> nameproducts = new ArrayList<>();
        String productDetails="<table class=\"table table-striped\">";

        for(OrderProduct orderProduct:this.getOrderDetails()) {
            productDetails += "<tr><td> <strong>" + orderProduct.getProduct().getProductName()
                    + "</strong></td> <td>Quantity: <strong>" + Integer.toString(orderProduct.getQuantity())
                    + "</strong></td><td> Cost: <strong>" + Double.toString(orderProduct.getCost())
            + "</strong></td></tr>";

        }
        productDetails +="</table>";
       return productDetails;
    }


    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", user=" + user +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderDetails=" + orderDetails +
                ", totalCost=" + totalCost +
                '}';
    }
}
