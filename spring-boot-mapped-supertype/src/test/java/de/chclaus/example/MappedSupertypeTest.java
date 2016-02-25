package de.chclaus.example;

import de.chclaus.example.domain.User;
import de.chclaus.example.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the mapped supertype demo.
 *
 * @author chclaus (ch.claus@me.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MappedSupertypeApplication.class)
public class MappedSupertypeTest {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Tests if last modified date and creation date will be injected automatically
	 * to the entity
	 */
	@Test
	public void testMappedSupertypeWithAuditing() {
		User user = new User();
		user.setUsername("foo");
		user.setPassword("bar");

		Timestamp beforeSaving = now();
		userRepository.saveAndFlush(user);

		List<User> allUsers = userRepository.findAll();
		Timestamp afterSaving = now();

		assertEquals(1, allUsers.size());
		User dbUser = allUsers.get(0);

		assertEquals("foo", dbUser.getUsername());
		assertEquals("bar", dbUser.getPassword());
		assertEquals(Boolean.FALSE, dbUser.getDeleted());

		// Check if id was set
		assertNotNull(dbUser.getId());

		// Check if there is a created date in the correct range
		assertNotNull(dbUser.getCreatedDate());
		Timestamp createdDate = Timestamp.from(dbUser.getCreatedDate().toInstant());
		assertTrue(beforeSaving.before(createdDate));
		assertTrue(createdDate.before(afterSaving));

		// Check if there is a last modified date in the correct range
		assertNotNull(dbUser.getLastModifiedDate());
		Timestamp lastModifiedDate = Timestamp.from(dbUser.getLastModifiedDate().toInstant());
		assertTrue(beforeSaving.before(lastModifiedDate));
		assertTrue(lastModifiedDate.before(afterSaving));

		// Update entity and check if just the last modified date has changed and the created date is still the same
		dbUser.setUsername("foo2");
		userRepository.saveAndFlush(dbUser);
		User updatedDbUser = userRepository.findOne(dbUser.getId());

		assertEquals(createdDate, Timestamp.from(updatedDbUser.getCreatedDate().toInstant()));
		assertNotEquals(lastModifiedDate, Timestamp.from(updatedDbUser.getLastModifiedDate().toInstant()));

		// Check if the version has changed
		assertTrue((dbUser.getVersion().intValue() + 1) == updatedDbUser.getVersion().intValue());

		updatedDbUser.setDeleted(true);
		userRepository.saveAndFlush(updatedDbUser);

		assertEquals(0, userRepository.count());
	}

	private Timestamp now() {
		return new Timestamp(new Date().getTime());
	}

}