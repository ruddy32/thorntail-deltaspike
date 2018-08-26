/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this
 * file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.timm.demo.thorntail.deltaspike.utils;

import java.io.File;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.exporter.ExplodedExporter;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;

/**
 * This class contains helpers for building frequently used archives
 */
public class ArchiveUtils {

	private static final String BEANS_XML_CONTENT_ANNOTATED = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<beans xmlns=\"http://xmlns.jcp.org/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
			+ " xsi:schemaLocation=\"http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd\" version=\"1.1\""
			+ " bean-discovery-mode=\"annotated\">" + "{0}{1}" + "</beans>";

	public static StringAsset getResourceBeanXMLWithDiscoveryModeAnnotated() {
		return new StringAsset(MessageFormat.format(BEANS_XML_CONTENT_ANNOTATED, ""));
	}

	private static final String BEANS_XML_CONTENT_ALTERNATIVES = "<alternatives>" + "{0}" + "</alternatives>";

	private static final String BEANS_XML_CONTENT_INTERCEPTORS = "<interceptors>" + "{0}" + "</interceptors>";

	private static final String BEANS_XML_CONTENT_CLASS = "<class>" + "{0}" + "</class>";

	public final static String WEBAPP_SRC = "src/main/webapp";

	/**
	 *
	 * @param testClass
	 * @return
	 */
	public static WebArchive createBaselineWar(Class<?> testClass) {
		return createBaselineWar(testClass, StringUtils.EMPTY);
	}

	/**
	 *
	 * @param testClass
	 * @param nameSuffix
	 * @return
	 */
	public static WebArchive createBaselineWar(Class<?> testClass, String nameSuffix) {
		return createBaselineWar(testClass, StringUtils.EMPTY, new String[] {});
	}

	/**
	 *
	 * @param testClass
	 * @param nameSuffix
	 * @return
	 */
	public static WebArchive createBaselineWar(Class<?> testClass, String[] dependencies) {
		return createBaselineWar(testClass, StringUtils.EMPTY, dependencies);
	}

	/**
	 *
	 * @param testClass
	 * @param nameSuffix
	 * @return
	 */
	public static WebArchive createBaselineWar(Class<?> testClass, String nameSuffix, String[] dependencies) {
		StringBuffer moduleName = new StringBuffer(testClass.getSimpleName());
		if (StringUtils.isNotBlank(nameSuffix)) {
			moduleName.append("-").append(nameSuffix);
		}
		moduleName.append(".war");

		WebArchive war = ShrinkWrap.create(WebArchive.class, moduleName.toString());

		addDependencies(war, dependencies);

		return war;
	}

	/**
	 *
	 * @param testClass
	 * @return
	 */
	public static JavaArchive createBaselineJar(Class<?> testClass) {
		return createBaselineJar(testClass, StringUtils.EMPTY);
	}

	/**
	 *
	 * @param testClass
	 * @param nameSuffix
	 * @return
	 */
	public static JavaArchive createBaselineJar(Class<?> testClass, String nameSuffix) {
		return createBaselineJar(testClass, nameSuffix, new String[] {});
	}

	/**
	 *
	 * @param testClass
	 * @return
	 */
	public static JavaArchive createBaselineJar(Class<?> testClass, String[] dependencies) {
		return createBaselineJar(testClass, StringUtils.EMPTY, dependencies);
	}

	/**
	 *
	 * @param testClass
	 * @param nameSuffix
	 * @return
	 */
	public static JavaArchive createBaselineJar(Class<?> testClass, String nameSuffix, String[] dependencies) {
		PomEquippedResolveStage resolver = Maven.resolver().loadPomFromFile("pom.xml");
		Collection<String> mavenDeps = new HashSet<>();
		for (String dependency : dependencies) {
			mavenDeps.add(dependency);
		}
		File[] libs = resolver.resolve(mavenDeps).withTransitivity().asFile();

		StringBuffer moduleName = new StringBuffer(testClass.getSimpleName());
		if (StringUtils.isNotBlank(nameSuffix)) {
			moduleName.append("-").append(nameSuffix);
		}
		moduleName.append(".jar");

		JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class, moduleName.toString());
		for (File lib : libs) {
			javaArchive.addAsResource(lib);
		}
		return javaArchive;
	}

	/**
	 *
	 * @param testClass
	 * @param nameSuffix
	 * @return
	 */
	public static WebArchive addDependencies(WebArchive war, String[] dependencies) {
		if (dependencies.length == 0) {
			return war;
		}

		PomEquippedResolveStage resolver = Maven.resolver().loadPomFromFile("pom.xml");
		Collection<String> mavenDeps = new HashSet<>();
		for (String dependency : dependencies) {
			mavenDeps.add(dependency);
		}
		File[] libs = resolver.resolve(mavenDeps).withTransitivity().asFile();

		return war.addAsLibraries(libs);
	}

	/**
	 *
	 * @param testClass
	 * @param nameSuffix
	 * @return
	 */
	public static WebArchive addResourceBeanXMLWithDiscoveryModeAnnotated(WebArchive war, Class<?>[] interceptors,
			Class<?>[] alternatives) {
		return war
				// beans.xml is optional for CDI 2.0
				.addAsWebInfResource(ArchiveUtils.getResourceBeanXMLWithAlternativeAndDiscoveryModeAnnotated(
						interceptors, alternatives), "beans.xml");
	}

	/**
	 *
	 * @param alternatives
	 * @return
	 */
	public static StringAsset getResourceBeanXMLWithAlternativeAndDiscoveryModeAnnotated(Class<?>[] interceptors,
			Class<?>[] alternatives) {
		StringBuffer interceptorTag = new StringBuffer();
		for (Class<?> clazz : interceptors) {
			interceptorTag.append(MessageFormat.format(BEANS_XML_CONTENT_CLASS, clazz.getName()));
		}
		String interceptorsTag = interceptorTag.length() > 0
				? MessageFormat.format(BEANS_XML_CONTENT_INTERCEPTORS, interceptorTag.toString())
				: "";

		StringBuffer alternativeTag = new StringBuffer();
		for (Class<?> clazz : alternatives) {
			alternativeTag.append(MessageFormat.format(BEANS_XML_CONTENT_CLASS, clazz.getName()));
		}
		String alternativesTag = alternativeTag.length() > 0
				? MessageFormat.format(BEANS_XML_CONTENT_ALTERNATIVES, alternativeTag.toString())
				: "";

		return new StringAsset(MessageFormat.format(BEANS_XML_CONTENT_ANNOTATED, interceptorsTag, alternativesTag));
	}

	/**
	 * Adds the facade/mock combination of given EJB class name as
	 * {@link ByteArrayAsset} to the web archive
	 *
	 * @param archive
	 * @param clazzName
	 *            *don't* use YourClass.class.getName(); use the complete FQDN
	 *            instead
	 * @return
	 * @throws Exception
	 */
	public static WebArchive addI18NLibs(WebArchive archive) throws Exception {
		PomEquippedResolveStage resolver = Maven.resolver().loadPomFromFile("pom.xml");

		File[] libsI18N = resolver.resolve("groupId:moduleId-i18n").withTransitivity().asFile();
		return archive.addAsLibraries(libsI18N);
	}

	/**
	 * Adds all web resources which are *not* inside META-INF or WEB-INF
	 *
	 * @param archive
	 * @throws Exception
	 */
	public static void addStaticWebResources(WebArchive archive) throws Exception {
		archive.as(ExplodedImporter.class).importDirectory(WEBAPP_SRC, Filters.exclude(".*\\-INF"));
	}

	/**
	 * Saves the content of given WAR to local directory
	 *
	 * @param war
	 * @param targetDirectory
	 */
	public static void saveWarContent(WebArchive war, File targetDirectory) {
		war.as(ExplodedExporter.class).exportExploded(targetDirectory);
	}
}