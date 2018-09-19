/**
 *
 */
package com.timm.demo.thorntail.deltaspike.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import com.timm.demo.thorntail.deltaspike.domain.RoleDTO;
import com.timm.demo.thorntail.deltaspike.domain.UserDTO;
import com.timm.demo.thorntail.deltaspike.entity.Role;
import com.timm.demo.thorntail.deltaspike.entity.User;
import com.timm.demo.thorntail.deltaspike.mapper.RoleMapper;
import com.timm.demo.thorntail.deltaspike.mapper.UserMapper;
import com.timm.demo.thorntail.deltaspike.persistence.RoleRepository;
import com.timm.demo.thorntail.deltaspike.persistence.UserRepository;
import com.timm.demo.thorntail.deltaspike.persistence.UserRoleRelRepository;

import fr.xebia.extras.selma.Selma;

/**
 * @author ruddy32
 */
@Default
@ApplicationScoped
public class Security {

	@Inject
	private UserRepository userRepository;

	@Inject
	private RoleRepository roleRepository;

	@Inject
	private UserRoleRelRepository userRoleRelRepository;

	public UserDTO getUser(Long id) {
		User user = userRepository.findBy(id);
		UserMapper mapper = Selma.builder(UserMapper.class).build();
		return mapper.asUserDTO(user);
	}

	public UserDTO findUser(String uid) {
		User user = userRepository.findByUid(uid).orElseThrow(() -> new WebApplicationException(404));
		UserMapper mapper = Selma.builder(UserMapper.class).build();
		return mapper.asUserDTO(user);
	}

	public UserDTO logon(String uid, String password) {
		return new UserDTO();
	}

	public Long addUser(UserDTO userDTO) {
		UserMapper mapper = Selma.builder(UserMapper.class).build();
		User user = mapper.asUser(userDTO, new User());
		user = userRepository.save(user);
		return user.getId();
	}

	public void editUser(Long id, UserDTO userDTO) {
		UserMapper mapper = Selma.builder(UserMapper.class).build();
		User user = mapper.asUser(userDTO, userRepository.findBy(id));
		user = userRepository.save(user);
	}

	public void removeUser(Long id) {
		userRepository.remove(userRepository.findBy(id));
	}

	public RoleDTO getRole(Long id) {
		Role role = roleRepository.findBy(id);
		RoleMapper mapper = Selma.builder(RoleMapper.class).build();
		return mapper.asRoleDTO(role);
	}

	public RoleDTO findRole(String code) {
		Role role = roleRepository.findByName(code).orElseThrow(() -> new WebApplicationException(404));
		RoleMapper mapper = Selma.builder(RoleMapper.class).build();
		return mapper.asRoleDTO(role);
	}

	public Long addRole(RoleDTO roleDTO) {
		RoleMapper mapper = Selma.builder(RoleMapper.class).build();
		Role role = mapper.asRole(roleDTO, new Role());
		role = roleRepository.save(role);
		return role.getId();
	}

	public void editRole(Long id, RoleDTO roleDTO) {
		RoleMapper mapper = Selma.builder(RoleMapper.class).build();
		Role role = mapper.asRole(roleDTO, roleRepository.findBy(id));
		role = roleRepository.save(role);
	}

	public void removeRole(Long id) {
		roleRepository.remove(roleRepository.findBy(id));
	}

	public boolean uidExists(String uid) {
		return userRepository.countByUid(uid) > 0;
	}

	public boolean notUidExists(String uid) {
		return userRepository.countByUid(uid) == 0;
	}
}