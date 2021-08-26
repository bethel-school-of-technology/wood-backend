//package com.woodapp.auth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private MySQLUserDetailsService mySQLUserDetailsService;
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth)
//      throws Exception {
//        auth
//          .inMemoryAuthentication()
//          .withUser("user")
//          .password(passwordEncoder.encode("123456")).roles("USER")
//          .and()
//          .withUser("admin").password(passwordEncoder.encode("123456")). roles("USER", "ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) 
//      throws Exception {
//    	http.authorizeRequests()
//        .antMatchers("/login").permitAll()
//        .antMatchers("/admin/**").hasRole("ADMIN")
//        .antMatchers("/**").hasAnyRole("ADMIN", "USER")
//        .and().formLogin()
//        .and().logout().logoutSuccessUrl("/login").permitAll()
//        .and().csrf().disable();
//    }
//}
