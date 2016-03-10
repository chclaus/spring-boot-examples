package de.chclaus.example;

import de.chclaus.example.domain.Movie;
import de.chclaus.example.domain.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OptimisticLockingApplication.class)
public class OptimisticLockingApplicationTests {

	@Autowired
	private MovieRepository movieRepository;

	@Before
	public void setUp() throws Exception {
		movieRepository.save(new Movie("The great movie", 5));
		movieRepository.save(new Movie("The not so great movie", 4));
		movieRepository.save(new Movie("Yet another movie", 3));
		movieRepository.save(new Movie("The big crap", 1));
	}

	/**
	 * Tests the situation, that there are two concurrent users working with a web application
	 * and both operating on the same data at the same time.
	 */
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testConcurrencyWriting() {
		assertEquals("Number of movies in Database incorrect.", 4, movieRepository.count());

		Movie theGreatMovieForUserOne = movieRepository.findByTitle("The great movie");
		Movie theGreatMovieForUserTwo = movieRepository.findByTitle("The great movie");

		// At first, serialize the movies to transport them to a view.
		MovieDTO theGreatMovieDTOForUserOne = Movies.of(theGreatMovieForUserOne);
		MovieDTO theGreatMovieDTOForUserTwo = Movies.of(theGreatMovieForUserTwo);

		// The view modifies the transport objects.
		theGreatMovieDTOForUserOne.setRating(1);
		theGreatMovieDTOForUserTwo.setRating(0);

		// The view sends the transport objects to the backend.
		Movie theUpdatedGreatMovieForUserOne = Movies.of(theGreatMovieDTOForUserOne);
		Movie theUpdatedGreatMovieForUserTwo = Movies.of(theGreatMovieDTOForUserTwo);

		// The versions of the updateded movies are both 0.
		assertEquals(0, theUpdatedGreatMovieForUserOne.getVersion().intValue());
		assertEquals(0, theUpdatedGreatMovieForUserTwo.getVersion().intValue());

		// The backend tries to save both.
		movieRepository.save(theUpdatedGreatMovieForUserOne);

		// OUTCH! Exception!
		movieRepository.save(theUpdatedGreatMovieForUserTwo);
	}

}
