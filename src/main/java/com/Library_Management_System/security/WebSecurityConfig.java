package com.Library_Management_System.security;

import com.Library_Management_System.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailService userDetailService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());

    }


//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailService);
//        provider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return provider;
//    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("api/auth").permitAll()
                // .antMatchers("/api/user", "api/book").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic().and()
                .formLogin().permitAll();
        // .httpBasic();


//                .antMatchers("/home")
//                  .hasAuthority("user")
//               // .antMatchers("/admin")
//                //.hasAuthority("admin")
//                .anyRequest()
//                //.fullyAuthenticated()
//                .authenticated()
//                .and()
//                .httpBasic();


        //  .antMatchers(HttpMethod.GET,   "/api").permitAll()


//
//                .antMatchers(HttpMethod.GET, "/api/auth").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                //.antMatcher("/api/auth").permitAll
//                .httpBasic();
//
//                //.authorizeRequests();


    }



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();

    }


//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService(){
//
//        UserDetails user = User.builder().username("muqtyjay").password(passwordEncoder().encode("muqtar4")).roles("user_roles").build();
//    }

}