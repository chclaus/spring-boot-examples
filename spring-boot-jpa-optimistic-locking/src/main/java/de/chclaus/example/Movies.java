package de.chclaus.example;

import de.chclaus.example.domain.Movie;

/**
 * Helper that transforms Movies to a DTO representation and backwards.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
public class Movies {

  public static MovieDTO of(Movie movie) {
    return new MovieDTO()
        .setId(movie.getId())
        .setVersion(movie.getVersion())
        .setTitle(movie.getTitle())
        .setRating(movie.getRating());
  }

  public static Movie of(MovieDTO movie) {
    return new Movie()
        .setId(movie.getId())
        .setVersion(movie.getVersion())
        .setTitle(movie.getTitle())
        .setRating(movie.getRating());
  }
}
