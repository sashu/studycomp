package com.study.comp.rest.common.utils;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

public class ApplicationContextUtil implements ApplicationContextAware, ServletContextAware {

	static ApplicationContext applicationContext = null;
	static ServletContext servletContext = null;

	public static ApplicationContext getApplicationContext() {
		return ApplicationContextUtil.applicationContext;
	}

	public static ServletContext getServletContext() {
		return ApplicationContextUtil.servletContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtil.applicationContext = applicationContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		ApplicationContextUtil.servletContext = servletContext;
	}

}
