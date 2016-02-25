package de.chclaus.example.domain;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * A simple user representation with a username and password.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
@Entity
@Where(clause = "is_deleted='false'")
public class User extends BaseEntity {

  @Column(length = 100)
  private String username;

  @Column(length = 100)
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
