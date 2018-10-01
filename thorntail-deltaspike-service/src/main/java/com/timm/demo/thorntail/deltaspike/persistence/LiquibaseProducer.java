package com.timm.demo.thorntail.deltaspike.persistence;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import liquibase.integration.cdi.CDILiquibaseConfig;
import liquibase.integration.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

@Dependent
public class LiquibaseProducer implements ILiquibaseProducer {

	@Resource(lookup = "java:jboss/datasources/thorntail-deltaspike-service-admin-ds")
	private DataSource dataSource;

	@Override
	@Produces
	@LiquibaseType
	public CDILiquibaseConfig createConfig() {
		final CDILiquibaseConfig config = new CDILiquibaseConfig();
		config.setChangeLog("db.changelog.xml");
		return config;
	}

	@Produces
	@LiquibaseType
	private DataSource createDS() {
		return dataSource;
	}

	@Produces
	@LiquibaseType
	private ResourceAccessor createResourceAccessor() {
		return new ClassLoaderResourceAccessor(getClass().getClassLoader());
	}

}
