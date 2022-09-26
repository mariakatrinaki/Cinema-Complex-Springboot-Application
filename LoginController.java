package gr.mariakapa.cinema.Controller;

import gr.mariakapa.cinema.Entity.User;
import gr.mariakapa.cinema.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class LoginController {

     @Autowired
     UserRepository userRepository;
    @GetMapping(value = "login")
    public String loginPage(Model model) {
        return "login";
    }


    @GetMapping("/employee")
    public String homeEmployee(Model model, @AuthenticationPrincipal UserDetails currentUser ){
        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role",user.getUsertype().getType());
        return "Employee/employee";
    }

    @GetMapping("/visitor")
    public String homeVisitor(Model model, @AuthenticationPrincipal UserDetails currentUser){
        User user = userRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("username", user.getUserName());
        model.addAttribute("role",user.getUsertype().getType());
        return "Visitor/visitor";
    }

    @RequestMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException, IOException {

        String role =  authResult.getAuthorities().toString();

        if(role.contains("ROLE_EMPLOYEE")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/employee"));
        }
        else if(role.contains("ROLE_VISITOR")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/visitor"));
        }
    }
}








