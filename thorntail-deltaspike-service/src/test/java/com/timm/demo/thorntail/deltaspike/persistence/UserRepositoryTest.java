package com.timm.demo.thorntail.deltaspike.persistence;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;

import com.timm.demo.thorntail.deltaspike.entity.User;
import com.timm.demo.thorntail.deltaspike.utils.TestDeployments;

@RunWith(Arquillian.class)
public class UserRepositoryTest {

	@Deployment
	public static Archive<?> createDeployment() throws IllegalArgumentException {
		WebArchive archive = TestDeployments.initWebDeployment(UserRepositoryTest.class.getSimpleName() + ".war");
		LoggerFactory.getLogger(UserRepositoryTest.class).debug(archive.toString(true));
		return archive;
	}

	@Inject
	private UserRepository repository;

	@Test
	@InSequence(1)
	public void testCountByUid() {
		repository.countByUidQuery("test@domain.fr");
	}

	@Test
	@InSequence(2)
	public void testFindByUid() {
		repository.findByUid("test@domain.fr");
	}

	@Test
	@Ignore
	@InSequence(3)
	public void testRemoveLong() {
		User user = repository.findByUid("test@domain.fr").orElseThrow(() -> new WebApplicationException(404));
		repository.remove(user.getId());
	}

}
