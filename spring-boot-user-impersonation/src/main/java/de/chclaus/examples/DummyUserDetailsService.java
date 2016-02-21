package de.chclaus.examples;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * A very basic UserDetailsService which has two different users: 'admin' and 'user'.
 *
 * @author chclaus (ch.claus@me.com)
 */
@Service
public class DummyUserDetailsService implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    if ("admin".equals(username)) {
      return new User(username, "pass", AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"));
    } else if ("user".equals(username)) {
      return new User(username, "pass", AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

    throw new UsernameNotFoundException("Username not found: " + username);
  }
}
