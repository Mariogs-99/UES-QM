/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Conventions;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.util.Assert;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


/**
 *
 * @author Oscar Vides
 */
@Configuration
public class WebInitializer implements WebApplicationInitializer{
    
     public static final String DEFAULT_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        
        registerHiddenHttpMethodFilter(servletContext);   
        
    }
    
    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocations(new String[]{"sv.global.colas.config"});
        return context;
    }
    
    private void registerHiddenHttpMethodFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic registration = servletContext.addFilter("openEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class);
        registration.setInitParameter("singleSession", "true");
        registration.setInitParameter("flushMode", "AUTO");        
        registration.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC),
                false, getServletName());
    }
    
        protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
		String filterName = Conventions.getVariableName(filter);		
		FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
		if (registration == null) {
			int counter = -1;
			while (counter == -1 || registration == null) {
				counter++;
				registration = servletContext.addFilter(filterName + "#" + counter, filter);
				Assert.isTrue(counter < 100,
						"Failed to register filter '" + filter + "'." +
						"Could the same Filter instance have been registered already?");
			}
		}
		registration.setAsyncSupported(isAsyncSupported());
		registration.addMappingForServletNames(getDispatcherTypes(), false, getServletName());
		return registration;
	}
        
        protected boolean isAsyncSupported() {
		return true;
	}
    
    private EnumSet<DispatcherType> getDispatcherTypes() {
		return isAsyncSupported() ?
			EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC) :
			EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);
	}
    
    protected String getServletName() {
		return DEFAULT_SERVLET_NAME;
	}
    
}
