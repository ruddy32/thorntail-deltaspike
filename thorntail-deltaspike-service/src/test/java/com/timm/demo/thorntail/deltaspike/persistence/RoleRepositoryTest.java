package com.timm.demo.thorntail.deltaspike.persistence;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.timm.demo.thorntail.deltaspike.entity.Role;
import com.timm.demo.thorntail.deltaspike.mapper.RoleMapper;
import com.timm.demo.thorntail.deltaspike.persistence.RoleRepository;

@RunWith(Arquillian.class)
public class RoleRepositoryTest {

	@Deployment
	public static Archive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, RoleRepositoryTest.class.getSimpleName() + ".war")
				.addPackage(Role.class.getPackage()).addPackage(RoleRepository.class.getPackage())
				.addPackage(RoleMapper.class.getPackage()).addAsResource("modules")
				.addAsResource("project-test.yml", "project-defaults.yml")
				.addAsResource("META-INF/beans-test.xml", "beans.xml")
				.addAsResource("META-INF/persistence-test.xml", "META-INF/persistence.xml")
				.addAsResource("META-INF/test-load.sql", "META-INF/test-load.sql");
	}

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