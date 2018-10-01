package com.timm.demo.thorntail.deltaspike.api;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author Sylvain Bonnemaison
 */
@ApplicationScoped
@ApplicationPath("/")
public class JAXRSActivator extends Application {

	// @PersistenceContext(unitName = "thorntail-deltaspike")
	// @Produces
	// private EntityManager entityManager;
}