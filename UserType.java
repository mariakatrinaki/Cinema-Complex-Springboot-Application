package gr.mariakapa.cinema.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import gr.mariakapa.cinema.Entity.User;
import gr.mariakapa.cinema.RequestResponse.UserTypeRequest;
import gr.mariakapa.cinema.RequestResponse.UserTypeResponce;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idUserType")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// AUTOINCREMENT 1,2,3 NOT SEQUENCE IN TABLE
    @Column(name ="id_usertype")
    private int idUserType;


    @Column(nullable = false,length = 10,unique= true)
    private String type;

    @OneToMany(mappedBy = "usertype",cascade ={ CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    private Set<User> users= new HashSet<>();


    public UserType(){


    }

    public int getIdUserType() {
        return idUserType;
    }

    public void setIdUserType(int idUserType) {
        this.idUserType = idUserType;
    }



    public User addUser(User user){
        this.users.add(user);
        user.setUsertype(this);
         return user;
    }

    @JsonManagedReference // να το κοιτάξω
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

    /*


    @Override
    public String toString() {
        return "UserType{" +
                "idUserType=" + idUserType +
                ", type='" + type + '\'' +
                ", users=" + users +
                '}';
    }
*/

}
