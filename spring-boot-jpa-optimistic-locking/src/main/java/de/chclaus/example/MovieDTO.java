package de.chclaus.example;

import de.chclaus.example.domain.Movie;

import java.io.Serializable;

/**
 * DTO for {@link de.chclaus.example.domain.Movie}s.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
public class MovieDTO implements Serializable{

  private Integer id;
  private Integer version;
  private String title;
  private Integer rating;

  public Integer getId() {
    return id;
  }

  public MovieDTO setId(Integer id) {
    this.id = id;
    return this;
  }

  public Integer getVersion() {
    return version;
  }

  public MovieDTO setVersion(Integer version) {
    this.version = version;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public MovieDTO setTitle(String title) {
    this.title = title;
    return this;
  }

  public Integer getRating() {
    return rating;
  }

  public MovieDTO setRating(Integer rating) {
    this.rating = rating;
    return this;
  }
}
