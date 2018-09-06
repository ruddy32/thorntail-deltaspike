/**
 *
 */
package com.timm.demo.thorntail.deltaspike.api;

import java.net.URI;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import com.timm.demo.thorntail.deltaspike.domain.Auth;
import com.timm.demo.thorntail.deltaspike.domain.RoleDTO;
import com.timm.demo.thorntail.deltaspike.domain.UserDTO;
import com.timm.demo.thorntail.deltaspike.service.Security;

/**
 * @author ruddy32
 */
@RequestScoped
@Transactional
public class SecurityController implements ISecurity {

	@Inject
	private Security security;

	@Override
	public Response findUser(String uid) {
		UserDTO user = security.findUser(uid);
		return Response.ok(user).build();
	}

	@Override
	public Response logon(Auth auth) {
		UserDTO user = security.logon(auth.getUid(), auth.getCode());
		return Response.ok(user).build();
	}

	@Override
	public Response getUser(Long id) {
		UserDTO user = security.getUser(id);
		return Response.ok(user).build();
	}

	@Override
	public Response addUser(UserDTO user) {
		Long id = security.addUser(user);
		return Response.created(URI.create("/" + id)).entity(user).build();
	}

	@Override
	public Response editUser(Long id, UserDTO user) {
		security.editUser(id, user);
		return Response.ok(user).build();
	}

	@Override
	public Response removeUser(Long id) {
		security.removeUser(id);
		return Response.ok().build();
	}

	@Override
	public Response getRole(Long id) {
		return Response.ok().build();
	}

	@Override
	public Response findRole(String code) {
		return Response.ok().build();
	}

	@Override
	public Response addRole(RoleDTO role) {
		return Response.ok().build();
	}

	@Override
	public Response editRole(Long id, RoleDTO role) {
		return Response.ok().build();
	}

	@Override
	public Response removeRole(Long id) {
		return Response.ok().build();
	}
}