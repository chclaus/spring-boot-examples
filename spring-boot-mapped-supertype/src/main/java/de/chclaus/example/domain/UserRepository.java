package de.chclaus.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
