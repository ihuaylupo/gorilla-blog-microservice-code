package com.huaylupo.microservice.gateway.config;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.ORIGIN;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.huaylupo.microservice.gateway.filter.JwtAuthenticationFilter;


/**
 * WebsecurityConfiguration 
 * @author ihuaylupo
 * @version 1.0
 * @since Jun 26, 2018
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;


	public WebSecurityConfiguration() {
		/*
		 * Ignores the default configuration, useless in our case (session management, etc..)
		 */
		super(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#authenticationManagerBean()
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		/*
          Overloaded to expose Authenticationmanager's bean created by configure(AuthenticationManagerBuilder).
           This bean is used by the AuthenticationController.
		 */
		return super.authenticationManagerBean();
	}



	@Override
	public void configure(WebSecurity web) throws Exception {
		// TokenAuthenticationFilter will ignore the below paths
		web.ignoring().antMatchers("/authentication");
		web.ignoring().antMatchers("/authentication/**");
		web.ignoring().antMatchers("/v2/api-docs");
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");

	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		/* the secret key used to signe the JWT token is known exclusively by the server.
         With Nimbus JOSE implementation, it must be at least 256 characters longs.
		 */
		//In case we need to load the secret.key
		httpSecurity

		.csrf().disable()
		// make sure we use stateless session; session won't be used to store user's state.
		.sessionManagement().sessionCreationPolicy(STATELESS)	
		.and()
		//.addFilterAfter(corsFilter(), ExceptionTranslationFilter.class)
		// handle an authorized attempts 
		.exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)) 	
		.and()
		// Add a filter to validate the tokens with every request
		.addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		// authorization requests config
		.authorizeRequests()
		// allow all who are accessing "auth" service
		.antMatchers("/authentication").permitAll() 
		// must be an admin if trying to access admin area (authentication is also required here)
		// Any other request must be authenticated
		.anyRequest().authenticated(); 

	}

	private com.huaylupo.microservice.gateway.filter.CorsFilter corsFilter() {
		/*
         CORS requests are managed only if headers Origin and Access-Control-Request-Method are available on OPTIONS requests
         (this filter is simply ignored in other cases).

         This filter can be used as a replacement for the @Cors annotation.
		 */
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader(ORIGIN);
		config.addAllowedHeader(CONTENT_TYPE);
		config.addAllowedHeader(ACCEPT);
		config.addAllowedHeader(AUTHORIZATION);
		config.addAllowedMethod(GET);
		config.addAllowedMethod(PUT);
		config.addAllowedMethod(POST);
		config.addAllowedMethod(OPTIONS);
		config.addAllowedMethod(DELETE);
		config.addAllowedMethod(PATCH);
		config.setMaxAge(3600L);

		source.registerCorsConfiguration("/v2/api-docs", config);
		source.registerCorsConfiguration("/**", config);

		return new com.huaylupo.microservice.gateway.filter.CorsFilter();
	}
}
