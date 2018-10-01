package com.timm.demo.thorntail.deltaspike.persistence;

import java.util.Optional;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Modifying;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import com.timm.demo.thorntail.deltaspike.entity.Role;

@Repository
public interface RoleRepository extends EntityRepository<Role, Long> {

	Long countByName(String name);

	Optional<Role> findByName(String name);

	@Query(named = Role.COUNT_BY_NAME)
	Long countByNameQuery(@QueryParam("name") String name);

	@Modifying
	@Query(named = Role.REMOVE_BY_ID)
	void remove(@QueryParam("id") Long id);
}