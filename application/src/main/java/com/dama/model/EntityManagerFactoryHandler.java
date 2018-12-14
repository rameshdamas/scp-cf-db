package com.dama.model;

import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;

public class EntityManagerFactoryHandler {

	private static EntityManagerFactory emf = null;

	/*
	 * Getter for an EntityManagerFactory. If there is already one existing return
	 * this. If not, then create a new one.
	 */
	public static EntityManagerFactory getEntityManagerFactory() throws Exception {
		if (emf == null)
			emf = createNewEntityManagerFactory();
		return emf;
	}

	/*
	 * Return a new EntityManagerFactory for the defined data source and persistence
	 * unit.
	 */
	private static EntityManagerFactory createNewEntityManagerFactory() throws Exception {

		// Name of the data source as defined in /webapp/WEB-INF/web.xml
		InitialContext ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/my-db");

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);

		// As replication will change the underlying database without
		// notifying the hub the JPA cache needs to be disabled.
		// Else the hub might provide outdated data
		properties.put(PersistenceUnitProperties.CACHE_SHARED_DEFAULT, "false");

		// Name of the persistence unit as defined in
		// /resources/META-INF/persistence.xml
		return Persistence.createEntityManagerFactory("dama-jpa", properties);
		
	}

}
