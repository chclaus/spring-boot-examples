package de.chclaus.example;

import de.chclaus.example.domain.User;
import de.chclaus.example.domain.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Test to validate the correctness of the soft-delete implementation.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MappedSupertypeApplication.class)
public class DeleteRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void testDeletion() throws Exception {
    // Validates if there are no users in the repository.
    assertEquals(0, userRepository.count());
    assertEquals(0, userRepository.countDeletedEntries());

    // Creates a new user and saves it.
    User user = new User();
    user.setUsername("foo");
    user.setPassword("bar");
    User dbUser = userRepository.saveAndFlush(user);

    // Now there is one user in the Database.
    assertEquals(1, userRepository.count());
    assertEquals(0, userRepository.countDeletedEntries());

    dbUser = userRepository.findOne(dbUser.getId());
    // Deletes the user.
    userRepository.delete(dbUser.getId());

    // Ensures that the repository-methods doesn't return the deleted user.
    assertEquals(0, userRepository.count());
    // But there should be one user which is still reachable by a native sql-query.
    assertEquals(1, userRepository.countDeletedEntries());
  }
}
