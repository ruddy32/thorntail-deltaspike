package com.timm.demo.thorntail.deltaspike.mapper;

import com.timm.demo.thorntail.deltaspike.domain.RoleDTO;
import com.timm.demo.thorntail.deltaspike.entity.Role;

import fr.xebia.extras.selma.Mapper;

@Mapper(withIgnoreFields = { "Role.id" })
public interface RoleMapper {

	// This will build a fresh new OrderDto
	RoleDTO asRoleDTO(Role in);

	// This will update the given order
	Role asRole(RoleDTO in, Role out);
}
