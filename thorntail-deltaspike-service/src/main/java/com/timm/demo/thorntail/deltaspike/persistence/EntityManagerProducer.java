package com.timm.demo.thorntail.deltaspike.persistence;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@ApplicationScoped
//public class EntityManagerProducer {
//
//	@Inject
//	@PersistenceUnitName("thorntail-deltaspike")
//	private EntityManagerFactory entityManagerFactory;
//
//	@Produces
//	@RequestScoped
//	public EntityManager create() {
//		return entityManagerFactory.createEntityManager();
//	}
//
//	public void closeEntityManager(@Disposes EntityManager entityManager) {
//		if (entityManager != null && entityManager.isOpen()) {
//			entityManager.close();
//		}
//	}
//}

/**
 * Entity manager producer.
 * <p>
 * It initializes the entity manager to be injected in EJBs
 *
 * @author Carlos Barragan (carlos.barragan@novatec-gmbh.de)
 */
@RequestScoped
public class EntityManagerProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerProducer.class);

	private static final String DEFAULT_PERSISTENCE_UNIT = "thorntail-deltaspike";

	@PersistenceContext(name = DEFAULT_PERSISTENCE_UNIT)
	@Produces
	private EntityManager em;

	/**
	 * Closes the entity manager and entity manager factory when the event
	 * {@link CdiContainerShutdown} is fired.
	 *
	 * @param containerShutdown the event that indicates that the container is about
	 *                          to shutdown.
	 */
	public static void closeEntityManager(@Disposes EntityManager entityManager) {
		if (entityManager != null) {
			try {
				// In case a transaction is still open.
				if (entityManager.getTransaction().isActive() && !entityManager.getTransaction().getRollbackOnly()) {
					LOGGER.debug("Rollback current transaction");
					entityManager.getTransaction().commit();
				}
			} finally {
				LOGGER.debug("Closing entity manager");
				entityManager.close();
			}
		}
	}
}