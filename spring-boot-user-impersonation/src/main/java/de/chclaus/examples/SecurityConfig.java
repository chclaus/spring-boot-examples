package de.chclaus.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
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
   * Adds an own UserDetailsService.
   *
   * @param auth the autowired AuthenticationManagerBuilder.
   * @throws Exception
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()

        // Endpoint permissions
        .antMatchers("/user/**", "/impersonate/logout").hasAnyRole("USER", "ADMIN")
        .antMatchers("/impersonate/**").hasRole("ADMIN")
        .antMatchers("/**").authenticated()

        // Login
        .and().formLogin().loginPage("/login").successHandler((req, resp, auth) -> resp.sendRedirect("/user")).permitAll()

        // Logout
        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()

        .and().addFilterAfter(switchUserFilter(), FilterSecurityInterceptor.class);
  }

  @Override
  protected UserDetailsService userDetailsService() {
    return new DummyUserDetailsService();
  }

  /**
   * Creates a SwitchUserFilter which handles requests to /impersonate to switch the current
   * user context.
   *
   * @return a new SwitchUserFilter with the common userDetailsService.
   */
  @Bean
  public SwitchUserFilter switchUserFilter() {
    SwitchUserFilter switchUserFilter = new SwitchUserFilter();
    switchUserFilter.setUserDetailsService(userDetailsService());
    switchUserFilter.setSwitchUserUrl("/impersonate/login");
    switchUserFilter.setExitUserUrl("/impersonate/logout");
    switchUserFilter.setSuccessHandler((request, response, authentication) -> response.sendRedirect("/user"));
    switchUserFilter.setFailureHandler((request, response, authentication) -> response.sendRedirect("/admin"));

    return switchUserFilter;
  }


}
