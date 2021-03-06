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

import com.timm.demo.thorntail.deltaspike.entity.Role;
import com.timm.demo.thorntail.deltaspike.utils.TestDeployments;

@RunWith(Arquillian.class)
public class RoleRepositoryTest {

	@Deployment
	public static Archive<?> createDeployment() throws IllegalArgumentException {
		WebArchive archive = TestDeployments.initWebDeployment(RoleRepositoryTest.class.getSimpleName() + ".war");
		LoggerFactory.getLogger(RoleRepositoryTest.class).debug(archive.toString(true));
		return archive;
	}

	@Inject
	private RoleRepository repository;

	@Test
	@InSequence(1)
	public void testFindByName() {
		repository.findByName("Test");
	}

	@Test
	@InSequence(2)
	public void testCountByName() {
		repository.countByNameQuery("Test");
	}

	@Test
	@Ignore
	@InSequence(3)
	public void testRemoveLong() {
		Role role = repository.findByName("Test").orElseThrow(() -> new WebApplicationException(404));
		repository.remove(role.getId());
	}
}