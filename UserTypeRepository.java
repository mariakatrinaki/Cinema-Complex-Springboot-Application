package gr.mariakapa.cinema.Repository;

import gr.mariakapa.cinema.Entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository  extends JpaRepository<UserType,Integer> {

    public UserType findUserTypeByIdUserType(int id);

}
