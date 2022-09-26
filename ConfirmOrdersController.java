package gr.mariakapa.cinema.Controller;


import gr.mariakapa.cinema.Entity.Order;
import gr.mariakapa.cinema.Entity.User;
import gr.mariakapa.cinema.Repository.UserRepository;
import gr.mariakapa.cinema.RequestResponse.UserResponse;
import gr.mariakapa.cinema.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller

@RequestMapping("/")
public class ConfirmOrdersController {


    @Autowired
    UserRepository userRepository;


    @Autowired
    UserService userService;



    @GetMapping( "ConfirmOrders")
    public String homePage(Model model, @AuthenticationPrincipal UserDetails currentUser ) {
        User user = userRepository.findByUserName(currentUser.getUsername());

        List<User> users = userRepository.findByUsertype_Type("visitor");
        for(User u: users){
            System.out.println(u.getUserName());
        }

        model.addAttribute("username", user.getUserName());
        model.addAttribute("role",user.getUsertype().getType());
        model.addAttribute("users",users);



        return "Employee/ConfirmOrders";
    }




}
