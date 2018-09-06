/**
 *
 */
package com.timm.demo.thorntail.deltaspike.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.timm.demo.thorntail.deltaspike.domain.Auth;
import com.timm.demo.thorntail.deltaspike.domain.RoleDTO;
import com.timm.demo.thorntail.deltaspike.domain.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Security operations.
 *
 * @author ruddy32
 */
@Path(value = "/security")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/security")
public interface ISecurity {

	/**
	 * Get user account from id.
	 *
	 * @param id
	 */
	@GET
	@Path(value = "/user/{id}")
	@ApiOperation(value = "Find user by ID", notes = "For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "User not found") })
	Response getUser(@PathParam("id") Long id);

	/**
	 * Find user accout from UID.
	 *
	 * @param uid
	 */
	@GET
	@Path(value = "/user/byUid//{uid}")
	@ApiOperation(value = "Find user by UID", notes = "For valid response try UID with not null and not empty value. Other values will generated exceptions", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "User not found") })
	Response findUser(@PathParam("uid") String uid);

	/**
	 * User authentication.
	 *
	 * @param auth
	 */
	@POST
	@Path(value = "/logon")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "User authentication", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid credential supplied"),
			@ApiResponse(code = 404, message = "User not found") })
	Response logon(Auth auth);

	/**
	 * Create user account.
	 *
	 * @param user
	 */
	@POST
	@Path(value = "/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Register user", response = UserDTO.class)
	@ApiResponses({ @ApiResponse(code = 400, message = "Invalid User") })
	Response addUser(UserDTO user);

	/**
	 * Update user account.
	 *
	 * @param id
	 * @param user
	 */
	@PUT
	@Path(value = "/user/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update user")
	@ApiResponses({ @ApiResponse(code = 400, message = "Invalid User") })
	Response editUser(@PathParam("id") Long id, UserDTO user);

	/**
	 * Remove user account.
	 *
	 * @param id
	 */
	@DELETE
	@Path(value = "/user/{id}")
	@ApiOperation(value = "Delete registered user by ID", notes = "For valid response try integer IDs with positive integer value. Negative or non-integer values will generate API errors")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "User not found") })
	Response removeUser(@PathParam("id") Long id);

	/**
	 * Get user role by id.
	 *
	 * @param id
	 */
	@GET
	@Path(value = "/role/{id}")
	@ApiOperation(value = "Find role by ID", notes = "For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "User not found") })
	Response getRole(@PathParam("id") Long id);

	/**
	 * Search user role by code.
	 *
	 * @param code
	 */
	@GET
	@Path(value = "/role")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find role by code", notes = "Unknown code will generated exceptions", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Role not found") })
	Response findRole(String code);

	/**
	 * Create user role.
	 *
	 * @param role
	 */
	@POST
	@Path(value = "/role")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Register role", response = RoleDTO.class)
	@ApiResponses({ @ApiResponse(code = 400, message = "Invalid role") })
	Response addRole(RoleDTO role);

	/**
	 * Update user role.
	 *
	 * @param id
	 * @param role
	 */
	@PUT
	@Path(value = "/role/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update role")
	@ApiResponses({ @ApiResponse(code = 400, message = "Invalid role") })
	Response editRole(@PathParam("id") Long id, RoleDTO role);

	/**
	 * Remove user role.
	 *
	 * @param id
	 */
	@DELETE
	@Path(value = "/role/{id}")
	@ApiOperation(value = "Delete registered role by ID", notes = "For valid response try integer IDs with positive integer value. Negative or non-integer values will generate API errors")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Role not found") })
	Response removeRole(@PathParam("id") Long id);
}