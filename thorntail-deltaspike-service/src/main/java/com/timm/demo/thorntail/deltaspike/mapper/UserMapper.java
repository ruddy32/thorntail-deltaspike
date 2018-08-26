package com.timm.demo.thorntail.deltaspike.mapper;

import com.timm.demo.thorntail.deltaspike.domain.UserDTO;
import com.timm.demo.thorntail.deltaspike.entity.User;

import fr.xebia.extras.selma.Mapper;

@Mapper(withIgnoreFields = { "User.id", "UserDTO.roles" })
public interface UserMapper {

	// This will build a fresh new OrderDto
	UserDTO asUserDTO(User in);

	// This will update the given order
	User asUser(UserDTO in, User out);
}
