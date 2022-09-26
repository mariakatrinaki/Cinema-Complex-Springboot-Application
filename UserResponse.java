package gr.mariakapa.cinema.RequestResponse;

import java.util.List;
import java.util.Set;

public class UserResponse {

   private int idUser;


    private String name;
    private String userName;
    private String password;
    private String type;

    private List<Integer> orders;


    public List<Integer> getOrder() {
        return orders;
    }

    public void setOrders(List<Integer> orders) {
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

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
