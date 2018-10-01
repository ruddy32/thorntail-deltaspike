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

import java.io.File;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.container.ClassContainer;
import org.jboss.shrinkwrap.api.container.ResourceContainer;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import com.timm.demo.thorntail.deltaspike.api.JAXRSActivator;

public abstract class ITDeployments {

	/**
	 * Create a basic deployment with dependencies, beans.xml and persistence
	 * descriptor.
	 *
	 * @return Basic web archive.
	 * @throws IllegalArgumentException
	 */
	public static <T extends Archive<?>> T initDeployment(String archiveName, Class<T> archiveClass)
			throws IllegalArgumentException {
		String packageName = StringUtils.removeEnd(JAXRSActivator.class.getName(),
				".api." + JAXRSActivator.class.getSimpleName());

		T archive = ShrinkWrap.create(archiveClass, archiveName);

		ClassContainer<?> cContainer = (ClassContainer<?>) archive;
		cContainer.addPackages(true, Filters.exclude(".*(Test|TI|TP|TU)\\.class"), packageName);

		ResourceContainer<?> rContainer = (ResourceContainer<?>) archive;
		rContainer.addAsResource("modules");
		rContainer.addAsResource("project-it.yml", "project-defaults.yml");
		rContainer.addAsResource("META-INF/beans-it.xml", "beans.xml");
		rContainer.addAsResource("META-INF/persistence-it.xml", "META-INF/persistence.xml");
		rContainer.addAsResource("META-INF/apache-deltaspike-it.properties", "META-INF/apache-deltaspike.properties");
		rContainer.addAsResource("db.changelog.xml");
		rContainer.addAsResource("changelogs");

		return archive;
	}

	/**
	 * Create a Web archive for Arquillian test.
	 *
	 * @param archiveName
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static WebArchive initWebDeployment(String archiveName) throws IllegalArgumentException {
		// Import Maven runtime dependencies
		WebArchive archive = initDeployment(archiveName, WebArchive.class);

		File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve()
				.withTransitivity().asFile();
		files = ArrayUtils.addAll(files,
				Maven.resolver().loadPomFromFile("pom.xml").resolve("com.h2database:h2").withTransitivity().asFile());
		archive.addAsLibraries(files);

		return archive;
	}

	/**
	 * Create a JAXRS archive for Arquillian test.
	 *
	 * @param archiveName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public static JAXRSArchive initJAXRSDeployment(String archiveName) throws IllegalArgumentException, Exception {
		JAXRSArchive archive = initDeployment(archiveName, JAXRSArchive.class);

		archive.addAllDependencies();

		return archive;
	}
}