package de.chclaus.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author chclaus (ch.claus@me.com)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * Adds an in memory authentication to the application.
   *
   * @param auth the autowired AuthenticationManagerBuilder.
   * @throws Exception
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user").password("pass").roles("USER");
    auth.inMemoryAuthentication().withUser("admin").password("pass").roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()

        // Pages
        .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/**").authenticated()

        // Login
        .and().formLogin().loginPage("/login").permitAll()

        // Logout
        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()

        .and().addFilterAfter(switchUserFilter(), FilterSecurityInterceptor.class);
  }

  @Bean
  public SwitchUserFilter switchUserFilter() {
    SwitchUserFilter switchUserFilter = new SwitchUserFilter();

    // TODO add own userDetailsService to allow the switch user filter to make a successful user lookup
    switchUserFilter.setUserDetailsService(userDetailsService());
    switchUserFilter.setSwitchUserUrl("/admin/impersonate/login");
    switchUserFilter.setExitUserUrl("/admin/impersonate/logout");
    switchUserFilter.setSuccessHandler((request, response, authentication) -> response.sendRedirect("/user"));
    switchUserFilter.setFailureHandler((request, response, authentication) -> response.sendRedirect("/admin"));

    return switchUserFilter;
  }

}
