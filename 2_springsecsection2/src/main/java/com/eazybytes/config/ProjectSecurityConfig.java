package com.eazybytes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * /myAccount - Secured /myBalance - Secured /myLoans - Secured /myCards -
	 * Secured /notices - Not Secured /contact - Not Secured
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http .authorizeRequests() .antMatchers("/myAccount").authenticated()
		  .antMatchers("/myBalance").authenticated()
		  .antMatchers("/myLoans").authenticated()
		  .antMatchers("/myCards").authenticated() .antMatchers("/notices").permitAll()
		  .antMatchers("/contact").permitAll() .and() .formLogin().and() .httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("12345")
				.authorities("admin").and().withUser("user")
				.password("12345").authorities("read").and()
				.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
}
