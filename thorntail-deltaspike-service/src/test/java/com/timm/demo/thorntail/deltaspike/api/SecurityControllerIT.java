package com.timm.demo.thorntail.deltaspike.api;

import java.net.URL;

import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;

import com.timm.demo.thorntail.deltaspike.domain.UserDTO;
import com.timm.demo.thorntail.deltaspike.utils.ITDeployments;

@RunWith(Arquillian.class)
public class SecurityControllerIT {

	// ======================================
	// = Injection Points =
	// ======================================

	@ArquillianResource
	private URL baseURL;

	private Long userId;

	// private Long roleId;

	// ======================================
	// = Deployment methods =
	// ======================================

	@Deployment
	public static Archive<?> createDeployment() throws IllegalArgumentException, Exception {
		Archive<?> archive = ITDeployments.initJAXRSDeployment(SecurityControllerIT.class.getSimpleName() + ".war");
		LoggerFactory.getLogger(SecurityControllerIT.class).debug(archive.toString(true));
		return archive;
	}

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
		user.setUid("test2@domain.fr");
		user.setName("test2");
		user.setRoles(new String[] { "Test" });

		ISecurity security = getSecurity("thorntail-deltaspike-service/user");
		Response response = security.addUser(user);

		MatcherAssert.assertThat(response.getStatus(), Is.is(Response.Status.CREATED.getStatusCode()));

		final String location = response.getHeaderString("location");
		userId = Long.getLong(location.substring(location.lastIndexOf("/") + 1));
	}

	@Test
	@Ignore
	@RunAsClient
	@InSequence(2)
	public void testGetUser() {
		ISecurity security = getSecurity("thorntail-deltaspike-service/user");
		Response response = security.getUser(userId);
		UserDTO user = response.readEntity(UserDTO.class);

		MatcherAssert.assertThat(user.getName(), Is.is("test2"));
		MatcherAssert.assertThat(user.getUid(), Is.is("test2@domain.fr"));
	}

	@Test
	@Ignore
	@RunAsClient
	@InSequence(3)
	public void testFindUser() {
		ISecurity security = getSecurity("thorntail-deltaspike-service/user");
		Response response = security.findUser("test@domain.fr");
		UserDTO user = response.readEntity(UserDTO.class);

		MatcherAssert.assertThat(user.getName(), Is.is("Testeur"));
		MatcherAssert.assertThat(user.getUid(), Is.is("test@domain.fr"));
	}

	@Test
	@Ignore
	@RunAsClient
	@InSequence(4)
	public void testEditUser() {
		ISecurity security = getSecurity("thorntail-deltaspike-service/user");
		Response response = security.getUser(userId);
		UserDTO user = response.readEntity(UserDTO.class);
		user.setName("test21");

		response = security.editUser(userId, user);
		MatcherAssert.assertThat(response.getStatus(), Is.is(Response.Status.OK.getStatusCode()));
	}

	@Test
	@Ignore
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