package de.chclaus.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  @Query(value = "SELECT count(u.id) FROM t_user u WHERE u.deleted='true'", nativeQuery = true)
  long countDeletedEntries();
}
