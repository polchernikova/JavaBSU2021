package by.polchernikova.lab04spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/addstudent").hasRole("ADMIN")
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .disable().cors();
        http.csrf().disable().authorizeRequests()
                .antMatchers("/addclassroom").hasRole("ADMIN")
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .disable().cors();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("organizer")
                .roles("ADMIN").password("123456")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
