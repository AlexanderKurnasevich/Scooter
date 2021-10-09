package by.scooter.config;

import by.scooter.api.sevice.UserService;
import by.scooter.entity.enumerator.RoleValue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = RoleValue.ROLE_ADMIN.getTitle();
    private static final String USER = RoleValue.ROLE_USER.getTitle();
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers("/login", "/signup", "/scooters").not().fullyAuthenticated()
                    .antMatchers("/users/**", "/masters/**").permitAll()
                    .antMatchers("/", "/orders/**", "/cars/**", "/spots/**").permitAll()
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/")
                .and().httpBasic()
                .and().sessionManagement().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(getPasswordEncoder());
    }
}
