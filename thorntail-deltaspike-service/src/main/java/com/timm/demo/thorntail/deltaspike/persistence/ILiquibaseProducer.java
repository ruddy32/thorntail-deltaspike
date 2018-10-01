package com.timm.demo.thorntail.deltaspike.persistence;

import liquibase.integration.cdi.CDILiquibaseConfig;

public interface ILiquibaseProducer {

	CDILiquibaseConfig createConfig();
}
