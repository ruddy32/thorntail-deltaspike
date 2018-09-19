package com.timm.demo.thorntail.deltaspike.persistence;

import java.util.Optional;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Modifying;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import com.timm.demo.thorntail.deltaspike.entity.User;

@Repository
public interface UserRepository extends EntityRepository<User, Long> {

	Long countByUid(String uid);

	Optional<User> findByUid(String uid);

	@Modifying
	@Query(named = User.REMOVE_BY_ID)
	void remove(@QueryParam("id") Long id);
}