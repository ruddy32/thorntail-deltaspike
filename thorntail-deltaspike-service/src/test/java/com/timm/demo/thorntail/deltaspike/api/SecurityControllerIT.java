package com.timm.demo.thorntail.deltaspike.api;

import java.net.URL;

import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import com.timm.demo.TransactionalTestCase;
import com.timm.demo.thorntail.deltaspike.domain.UserDTO;

@RunWith(Arquillian.class)
@DefaultDeployment
public class SecurityControllerIT extends TransactionalTestCase {

	// ======================================
	// = Injection Points =
	// ======================================

	@ArquillianResource
	private URL baseURL;

	private Long userId;

	// private Long roleId;

	// ======================================
	// = Lifecycle methods =
	// ======================================

	// ======================================
	// = Test methods =
	// ======================================

	@Test
	@RunAsClient
	@InSequence(1)
	public void testAddUser() {
		final UserDTO user = new UserDTO();
		user.setUid("test@test.fr");
		user.setName("test");
		user.setRoles(new String[] { "test" });

		ISecurity security = getSecurity("thorntail-deltaspike-service/user");
		Response response = security.addUser(user);

		MatcherAssert.assertThat(response.getStatus(), Is.is(Response.Status.OK.getStatusCode()));

		final String location = response.getHeaderString("location");
		userId = Long.getLong(location.substring(location.lastIndexOf("/") + 1));
	}

	@Test
	@RunAsClient
	@InSequence(2)
	public void testGetUser() {
		ISecurity security = getSecurity("thorntail-deltaspike-service/user");
		Response response = security.getUser(userId);
		UserDTO user = response.readEntity(UserDTO.class);

		MatcherAssert.assertThat(user.getName(), Is.is("test"));
		MatcherAssert.assertThat(user.getUid(), Is.is("test@test.fr"));
	}

	@Test
	@RunAsClient
	@InSequence(3)
	public void testFindUser() {
		ISecurity security = getSecurity("thorntail-deltaspike-service/user");
		Response response = security.findUser("test@test.fr");
		UserDTO user = response.readEntity(UserDTO.class);

		MatcherAssert.assertThat(user.getName(), Is.is("test"));
		MatcherAssert.assertThat(user.getUid(), Is.is("test@test.fr"));
	}

	@Test
	@RunAsClient
	@InSequence(4)
	public void testEditUser() {
		ISecurity security = getSecurity("thorntail-deltaspike-service/user");
		Response response = security.getUser(userId);
		UserDTO user = response.readEntity(UserDTO.class);
		user.setName("test2");

		response = security.editUser(userId, user);
		MatcherAssert.assertThat(response.getStatus(), Is.is(Response.Status.OK.getStatusCode()));
	}

	@Test
	@InSequence(5)
	public void testRemoveUser() {
		ISecurity security = getSecurity("thorntail-deltaspike-service/user");
		Response response = security.removeUser(userId);

		MatcherAssert.assertThat(response.getStatus(), Is.is(Response.Status.OK.getStatusCode()));
	}

	// ======================================
	// = Utility methods =
	// ======================================

	private ISecurity getSecurity(final String endpoint) {
		return RestClientBuilder.newBuilder().baseUrl(baseURL).build(ISecurity.class);
	}
}