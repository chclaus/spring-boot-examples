package de.chclaus.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Simple repository to allow CRUD-Methods for Movies.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
public interface MovieRepository extends CrudRepository<Movie, Integer> {

  Movie findByTitle(String title);
}
