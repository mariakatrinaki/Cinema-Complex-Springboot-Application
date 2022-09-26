package gr.mariakapa.cinema.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// AUTOINCREMENT 1,2,3 NOT SEQUENCE IN TABLE
    @Column(name ="id_user")
    private int idUser;

    @Column(name = "name",nullable = false,length = 100,unique = true)
    private String name;

    @Column(name="username",nullable = false,length = 100,unique = true)
    private String userName;

    @Column(name="password",nullable = false,length = 100,unique = true)
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name ="id_usertype",nullable = false)
    UserType usertype;

    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
    private List<Order> orders= new ArrayList<>();


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
     private List<Ticket> tickets = new ArrayList<>();


    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
     private ShoppingCart shoppingCart;




    public User(String userName, String password, UserType usertype){
        this.userName = userName;
        this.password = password;
        this.usertype = usertype;
    }
    public User(){

    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }



   public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
   }

    @JsonManagedReference
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdUser() {
        return idUser;
    }



    @JsonBackReference// να το κοιτάξω
    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", usertype=" + usertype +
                ", orders=" + orders +
                ", tickets=" + tickets +
                ", shoppingCart=" + shoppingCart +
                '}';
    }
}
