package com.timm.demo.thorntail.deltaspike.persistence;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Modifying;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import com.timm.demo.thorntail.deltaspike.entity.Role;

@Repository
public interface RoleRepository extends EntityRepository<Role, Long> {

	long countByName(String name);

	@Query(named = Role.SELECT_BY_NAME)
	Role findByName(@QueryParam("name") String name);

	@Modifying
	@Query(value = Role.REMOVE_BY_ID)
	void remove(@QueryParam("id") Long id);
}