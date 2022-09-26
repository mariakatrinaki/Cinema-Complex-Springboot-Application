package gr.mariakapa.cinema.Service;


import gr.mariakapa.cinema.Entity.User;
import gr.mariakapa.cinema.Entity.UserType;
import gr.mariakapa.cinema.Repository.UserRepository;
import gr.mariakapa.cinema.Repository.UserTypeRepository;
import gr.mariakapa.cinema.RequestResponse.UserTypeRequest;
import gr.mariakapa.cinema.RequestResponse.UserTypeResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserTypeService {

    @Autowired
    UserTypeRepository userTypeRepository;




    public UserType saveUserType(UserType userType ){

        return userTypeRepository.save(userType);



    }

    public List<UserType> getUserType(){
      return userTypeRepository.findAll();
    }











    public UserType findUserTypeById(int id){

        return  userTypeRepository.findUserTypeByIdUserType(id);
    }

}
/*
 UserType newUserType = new UserType();
          newUserType.setType(userType.getType());
          newUserType.setUsers(userType.
                   getUsers().
                  stream().
                 map( u ->
                  {
                      User uu = userService.findUserById(u.getIdUser());
                      uu.setUsertype(newUserType);
                  return uu;
                  }).collect(Collectors.toSet()));

 */