package gr.mariakapa.cinema.RequestResponse;

import gr.mariakapa.cinema.Entity.User;

import java.util.Set;

public class UserTypeRequest {


     private String type;

     private Set<User> users;


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
