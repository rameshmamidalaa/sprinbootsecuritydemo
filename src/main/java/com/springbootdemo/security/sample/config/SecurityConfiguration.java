/**
 * 
 */
package com.springbootdemo.security.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ramesh_Mamidala
 *
 *@Configuration and @EnableWebSecurity annotations are required, so that spring framework know that this class will be used for spring security configuration.
 * <Annotation>@EnableWebSecurity</Annotation>: Add this annotation to an @Configuration class to have the Spring Security configuration 
 *  defined in any WebSecurityConfigurer or more likely by extending the WebSecurityConfigurerAdapter base class and overriding individual methods.
 * <Annotation>@Configuration</Annotation>:Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime
 */
@Configuration
@EnableWebSecurity 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 * Spring Security should completely ignore URLs starting with /resources/ 
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/resources/**");
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 * 
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/user").hasRole("USER") //USER role only can access /user
			.antMatchers("/admin").hasRole("ADMIN") //ADMIN role only can access /address
			.antMatchers("/home").permitAll() // anyone can access /home
			.anyRequest().authenticated() //any other request just need authentication
			.and()
		.formLogin();

	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 * 
	 * Enable in memory based authentication with a user named "user" and "admin" along with role authorization
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder authMgrBuldr) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		authMgrBuldr.inMemoryAuthentication()
		.withUser("user").password(encoder.encode("user@123")).roles("USER")
		.and().withUser("admin").password(encoder.encode("admin@123")).roles("ADMIN");
	}

}
