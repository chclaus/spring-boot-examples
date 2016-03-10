package de.chclaus.example.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represents a movie with a title and a general rating.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
@Entity
@Table(name = "t_movie")
public class Movie implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Version
  private Integer version;

  private String title;

  private Integer rating;


  public Movie() {
    // empty constructor for (de)serialization
  }

  public Movie(String title, Integer rating) {
    this.title = title;
    this.rating = rating;
  }

  public Integer getId() {
    return id;
  }

  public Movie setId(Integer id) {
    this.id = id;
    return this;
  }

  public Integer getVersion() {
    return version;
  }

  public Movie setVersion(Integer version) {
    this.version = version;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public Movie setTitle(String title) {
    this.title = title;
    return this;
  }

  public Integer getRating() {
    return rating;
  }

  public Movie setRating(Integer rating) {
    this.rating = rating;
    return this;
  }
}