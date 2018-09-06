package com.timm.demo.thorntail.deltaspike.persistence;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;

import com.timm.demo.thorntail.deltaspike.api.SecurityControllerIT;
import com.timm.demo.thorntail.deltaspike.entity.Role;
import com.timm.demo.thorntail.deltaspike.utils.ITDeployments;

@RunWith(Arquillian.class)
public class RoleRepositoryIT {

	// ======================================
	// = Injection Points =
	// ======================================

	@Inject
	private RoleRepository repository;

	// ======================================
	// = Deployment methods =
	// ======================================

	@Deployment
	public static Archive<?> createDeployment() throws IllegalArgumentException, Exception {
		Archive<?> archive = ITDeployments.initJAXRSDeployment(SecurityControllerIT.class.getSimpleName() + ".war");
		LoggerFactory.getLogger(RoleRepositoryIT.class).debug(archive.toString(true));
		return archive;
	}

	// ======================================
	// = Lifecycle methods =
	// ======================================

	// ======================================
	// = Test methods =
	// ======================================

	@Test
	@InSequence(1)
	public void testCountByName() {
		repository.countByName("Test");
	}

	@Test
	@Ignore
	@InSequence(2)
	public void testFindByName() {
		repository.findByName("Test");
	}

	@Test
	@Ignore
	@InSequence(3)
	public void testRemoveLong() {
		Role role = repository.findByName("Test");
		repository.remove(role.getId());
	}
}