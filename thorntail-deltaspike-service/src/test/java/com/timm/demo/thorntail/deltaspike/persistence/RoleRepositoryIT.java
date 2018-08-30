package com.timm.demo.thorntail.deltaspike.persistence;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import com.timm.demo.thorntail.deltaspike.entity.Role;

@RunWith(Arquillian.class)
@DefaultDeployment
public class RoleRepositoryIT {

	@Inject
	private RoleRepository repository;

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