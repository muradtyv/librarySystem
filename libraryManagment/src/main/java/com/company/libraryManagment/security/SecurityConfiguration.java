package com.company.libraryManagment.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserPrincipalDetailsService userPrincipalDetailsService;

//
//	private final DataSource dataSource;
//
//
//	private final BCryptPasswordEncoder pwEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/employee/**").hasRole("EMPLOYEE")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/login/**").permitAll()
		.antMatchers("/register/**").permitAll()
		.antMatchers("/logout/**").permitAll()
		.antMatchers("/CSS/**").permitAll()
		.antMatchers("/Images/**").permitAll()
		.antMatchers("/**").authenticated().and().formLogin().loginPage("/login");

//		http.csrf().ignoringAntMatchers("/h2-console/**");
//		http.headers().frameOptions().disable();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(createPwEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

		return daoAuthenticationProvider;
	}
    @Bean
    public PasswordEncoder createPwEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
