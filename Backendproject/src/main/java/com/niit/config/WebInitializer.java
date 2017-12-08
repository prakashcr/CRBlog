package com.niit.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	   
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{DBconfig.class};
	}
   
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{WebResolver.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
		
	}
	protected Filter[] getServletFilters() {
		Filter[] singleton =  { new CORSFilter() };
		return singleton;
	}
	
}