package com.timm.demo.thorntail.deltaspike.persistence;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import com.timm.demo.thorntail.deltaspike.entity.UserRoleRel;
import com.timm.demo.thorntail.deltaspike.entity.UserRoleRelId;

@Repository
public interface UserRoleRelRepository extends EntityRepository<UserRoleRel, UserRoleRelId> {
}