package gr.mariakapa.cinema.RequestResponse;

import gr.mariakapa.cinema.Entity.User;

import java.util.HashSet;
import java.util.Set;

public class UserTypeResponce {

    private int idUserType;

    private String type;

    private Set<User> users ;

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

    public int getIdUserType() {
        return idUserType;
    }

    public void setIdUserType(int idUserType) {
        this.idUserType = idUserType;
    }
}
