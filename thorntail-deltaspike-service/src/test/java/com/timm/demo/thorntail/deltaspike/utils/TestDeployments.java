/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.timm.demo.thorntail.deltaspike.utils;

import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.UnknownExtensionTypeException;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import com.timm.demo.TransactionalTestCase;

public abstract class TestDeployments {

	public static String DS_PROPERTIES_WITH_ENV_AWARE_TX_STRATEGY = "globalAlternatives.org.apache.deltaspike.jpa.spi.transaction.TransactionStrategy="
			+ "org.apache.deltaspike.jpa.impl.transaction.ResourceLocalTransactionStrategy";
	// "org.apache.deltaspike.jpa.impl.transaction.BeanManagerUserTransactionStrategy";
	// "org.apache.deltaspike.jpa.impl.transaction.EnvironmentAwareTransactionStrategy";

	/**
	 * Create a basic deployment with dependencies, beans.xml and persistence
	 * descriptor.
	 *
	 * @return Basic web archive.
	 * @throws Exception
	 * @throws UnknownExtensionTypeException
	 * @throws IllegalArgumentException
	 */
	public static JAXRSArchive initDeployment(String archiveName)
			throws IllegalArgumentException, UnknownExtensionTypeException, Exception {
		// Import Maven runtime dependencies
		// File[] files =
		// Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve()
		// .withTransitivity().asFile();
		// for (File file : files) {
		// LoggerFactory.getLogger(TestDeployments.class).debug(file.getAbsolutePath());
		// }

		String packageName = "com.timm.demo.thorntail.deltaspike";

		JAXRSArchive archive = ShrinkWrap.create(JAXRSArchive.class, archiveName)
				.addPackages(true, Filters.exclude(".*(Test|TI|TP|TU)\\.class"), packageName)
				.addClasses(TransactionalTestCase.class)
				.addAsWebInfResource(new ClassLoaderAsset("beans-test.xml", TestDeployments.class.getClassLoader()),
						"beans.xml")
				.addAsWebInfResource(new ClassLoaderAsset("persistence-h2.xml", TestDeployments.class.getClassLoader()),
						"classes/META-INF/persistence.xml")
				.addAsWebInfResource(new StringAsset(DS_PROPERTIES_WITH_ENV_AWARE_TX_STRATEGY),
						"classes/META-INF/apache-deltaspike.properties")
				// .addAsWebInfResource(new
				// ClassLoaderAsset("thorntail-deltaspike-service-test-ds.xml",
				// TestDeployments.class.getClassLoader()), "thorntail-deltaspike-service-ds.xml")
				.addAsResource(
						new ClassLoaderAsset("project-defaults-test.yml", TestDeployments.class.getClassLoader()),
						"project-defaults.yml")
				// .addAsWebInfResource(new File("src/main/resources/import.sql"),
				// "classes/import.sql")
				.addAllDependencies()
		// .addAsLibraries(files)
		//
		;

		return archive;
	}
}