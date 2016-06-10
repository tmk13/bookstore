package com.apress.bookstore.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.apress.springmvc.configuration.DBManager;

public class BookstoreContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		DBManager.getManager().createEntityManagerFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		DBManager.getManager().closeEntityManagerFactory();
	}

}
