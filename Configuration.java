package gr.mariakapa.cinema.Security;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//@org.springframework.context.annotation.Configuration
//@EnableWebSecurity
public class Configuration extends WebSecurityConfigurerAdapter {


    private static final String[] AUTH_WHITELIST = {"/addMovie", "/addMovies","/addGenre" ,
         "/addGenres","/movie/**","/Genre/**","/MovieById/**/Genre/**",
     "/MovieById/{id}/genres","/Genres","/genre-movie/**","/saveGenre",
           "/delete-movie/{id}","/delete-genre/{id}",
            "/saveUserType","/usertype/{id}","/saveUser",
           "/user-UserType/{id}","/users-UserType","/saveOrder","/order/{id}",
           "/user-Orders","/user-UserType/name/{name}",
            "/Genres/name/{name}","/saveProduct","/product/name/{name}",
           "/saveOrder","/order/{id}","/user-Orders/{id}","/login",
            "/deleteProduct/{idOrder}/{idProduct}",
            "/deleteOrder/{idOrder}","/addShowTime","/showTimeMovies/{id}",
            "/addSeat","/saveTicket"
    };



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/csrf").permitAll()
                .anyRequest().authenticated();
    }



}
