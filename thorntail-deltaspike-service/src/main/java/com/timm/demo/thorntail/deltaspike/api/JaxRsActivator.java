package com.timm.demo.thorntail.deltaspike.api;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author Sylvain Bonnemaison
 */
@ApplicationScoped
@ApplicationPath("/")
public class JaxRsActivator extends Application {

	// @PersistenceContext(name = "sample")
	// @Produces
	// private EntityManager entityManager;
}