package gr.mariakapa.cinema.Controller;


import gr.mariakapa.cinema.Entity.User;
import gr.mariakapa.cinema.Repository.UserRepository;
import gr.mariakapa.cinema.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserRepository userRepository;


    @Autowired
    UserService userService;



    @GetMapping( "Home")
    public String homePage(Model model, @AuthenticationPrincipal UserDetails currentUser ) {
        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role",user.getUsertype().getType());
        return "Visitor/Home";
    }

}
