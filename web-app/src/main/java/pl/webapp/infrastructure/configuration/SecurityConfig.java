package pl.webapp.infrastructure.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pl.webapp.buisness.dto.UserDTO;
import pl.webapp.buisness.services.RoleService;
import pl.webapp.buisness.services.UserRoleService;
import pl.webapp.buisness.services.UserService;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig {

    UserService userService;
    UserRoleService userRoleService;
    RoleService roleService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/redirector").permitAll()
                        .requestMatchers("/owner-panel").hasRole("OWNER")
                        .requestMatchers("/create-restaurant").hasRole("OWNER")
                        .requestMatchers("/add-street").hasRole("OWNER")
                        .requestMatchers("/show-orders").hasRole("OWNER")
                        .requestMatchers("/create-menu").hasRole("OWNER")
                        .requestMatchers("/create-menu/upload").hasRole("OWNER")
                        .requestMatchers("/customer-panel").hasRole("CUSTOMER")
                        .requestMatchers("/show-orders-customer").hasRole("CUSTOMER")
                        .requestMatchers("/order-history").hasRole("CUSTOMER")
                        .requestMatchers("/courier-panel").hasRole("DELIVERY")
                        .requestMatchers("/courier-panel/**").hasRole("DELIVERY")
                        .requestMatchers("/order-details").hasAnyRole("OWNER", "CUSTOMER"))
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/redirector", true)
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDTO> allUsers = userService.getAllUsers();
        List<UserDetails> usersDetails = allUsers.stream().map(user -> User.builder()
                .username(user.getUserUsername())
                .password(user.getUserPassword())
                .roles(roleService.getRoleById(userRoleService.getRoleIdByUserId(user.getUserId())).getRoleName())
                .build()).toList();
        return new InMemoryUserDetailsManager(usersDetails);
    }


}
