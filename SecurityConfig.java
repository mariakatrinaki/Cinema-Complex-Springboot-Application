package gr.mariakapa.cinema.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // default spring security interface help do the authentication

    @Autowired
    private UserDetailsService userDetailsService;



    @Bean
    PasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        // data access object from database
        DaoAuthenticationProvider provider
                = new DaoAuthenticationProvider();


        provider.setUserDetailsService(this.userDetailsService);
        provider.setPasswordEncoder(bcryptPasswordEncoder());

        return provider;
    }

    private static final String[] AUTH_WHITELIST = {
            "/addMovie","/movies","/movieById/{id}",
            "/movie/{title}","/genre-movie/{id}",
            "/delete-movie/{id}","/saveUserType","/saveUser",
            "/login","/saveOrder","/user-Orders/{id}","/order/{id}"
    };





    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http. authorizeRequests()
                //.csrf().disable().authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/saveUser")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                //  name=username, name =password in login.html
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/success", true)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login");





    }

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http
             .authorizeRequests()
             .anyRequest().authenticated()
             .and()
             .formLogin()
             .loginPage("/login")
             .permitAll();
    }

*/
}
