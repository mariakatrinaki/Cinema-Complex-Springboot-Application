package gr.mariakapa.cinema.Repository;

import gr.mariakapa.cinema.Entity.User;
import gr.mariakapa.cinema.Entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findUserByIdUser(int id);

    User findByUserName(String userName);


    List<User> findByUsertype_Type(String type);


}
