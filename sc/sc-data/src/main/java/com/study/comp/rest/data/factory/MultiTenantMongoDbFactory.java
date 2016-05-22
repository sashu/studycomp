package com.study.comp.rest.data.factory;

import java.net.UnknownHostException;

import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.DB;
import com.mongodb.Mongo;

@SuppressWarnings("deprecation")
public class MultiTenantMongoDbFactory extends SimpleMongoDbFactory {

	private static final ThreadLocal<String> dbName = new ThreadLocal<String>();
	public  static String defaultName = "scadmin";
	public static String currentClient = "studycomp";

	public MultiTenantMongoDbFactory(final Mongo mongo, final String defaultDatabaseName) throws UnknownHostException {
		super(mongo, defaultDatabaseName);
		MultiTenantMongoDbFactory.defaultName = defaultDatabaseName;
	}

	public static void setDatabaseNameForCurrentThread(final String schemaName) {
		if (schemaName != null && schemaName != "") {
			dbName.set(schemaName);
		} else {
			dbName.set(MultiTenantMongoDbFactory.defaultName);
		}
	}

	public static void clearDatabaseNameForCurrentThread() {
		dbName.remove();
	}

	public static String getCurrentClient() {
		return currentClient;
	}

	public static void setCurrentClient(String currentClient) {
		MultiTenantMongoDbFactory.currentClient = currentClient;
	}

	@Override
	public DB getDb() {
		final String tlName = dbName.get();
		final String dbToUse = (tlName != null ? tlName : MultiTenantMongoDbFactory.defaultName);
		return super.getDb(dbToUse);
	}
}
