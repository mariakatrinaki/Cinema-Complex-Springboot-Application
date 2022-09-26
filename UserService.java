package gr.mariakapa.cinema.Service;

import gr.mariakapa.cinema.Security.CustomUserDetails;
import gr.mariakapa.cinema.Entity.User;
import gr.mariakapa.cinema.Repository.UserRepository;
import gr.mariakapa.cinema.RequestResponse.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

     @Autowired
     UserRepository userRepository;



     public User save_user(User user){


         return userRepository.save(user);
     }




   public UserResponse findUserById_UserType(int id){
         UserResponse userResponse = new UserResponse();
        List<User> list  = userRepository.findAll();
        for(User user :list){
            if(user.getIdUser()== id){
                userResponse.setIdUser(user.getIdUser());
                userResponse.setName(user.getName());
                userResponse.setUserName(user.getUserName());

                userResponse.setPassword(user.getPassword());
                userResponse.setType(user.getUsertype().getType());
            }
        }

     return userResponse;
   }
    public List<UserResponse> getUsers_UserType () {
        List<User> list = userRepository.findAll();
        List<UserResponse> responseList = new ArrayList<>();
        list.forEach(u -> {
            UserResponse response = new UserResponse();
            response.setIdUser(u.getIdUser());
            response.setName(u.getName());
            response.setUserName(u.getUserName());
            response.setPassword(u.getPassword());
            response.setType(u.getUsertype().getType());
            responseList.add(response);
        });
        return responseList;
    }



    public List<UserResponse> getUsersByType(String type){
         List<UserResponse>  listUserResponse = new ArrayList<>();
         List<User> listUser = userRepository.findAll();
         for(User user : listUser){
             if(user.getUsertype().getType().equals(type)){
                 UserResponse userResponse = new UserResponse();
                 userResponse.setIdUser(user.getIdUser());
                 userResponse.setName(user.getName());
                 userResponse.setUserName(user.getUserName());
                 userResponse.setPassword(user.getPassword());
                 userResponse.setType(user.getUsertype().getType());
                 listUserResponse.add(userResponse);

             }
         }
         return listUserResponse;
    }


   public List<User>  getUserOrders(){
       return   userRepository.findAll();
   }

   public User findUserById(int id){
         return userRepository.findUserByIdUser(id);
   }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByUserName(username);
       if(user == null){
           throw new UsernameNotFoundException("User not found");
       }
       CustomUserDetails customUserDetails = new CustomUserDetails();
       customUserDetails.setUser(user);

       return  customUserDetails;
     }
}
