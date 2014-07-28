package org.appverse.web.showcases.gwtshowcase.backend.services;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.appverse.web.framework.backend.api.helpers.security.XSSSecurityFilter;
import org.appverse.web.framework.backend.frontfacade.rest.application.JerseyInitRestApplication;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.UserServiceFacade;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.impl.live.UserRestServiceFacadeImpl;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ShowcaseRestInitApplication extends JerseyInitRestApplication {

	
    @Inject
    public ShowcaseRestInitApplication(ServletContext servletContext) {
    	super(servletContext);
        WebApplicationContext springFactory = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        // Manually register a bean created by Spring
        // TODO: scan entire Spring factory for beans annotated with @Path and register them, so we don't need to do this manually.
        // Letting Jersey register the beans does not work because in this case Spring will not intercept the calls.
        // See comments bellow.
        register(springFactory.getBean(UserServiceFacade.class));
        //TODO This should be done in the Framework version of JerseyINitJsonApplication
        register(springFactory.getBean("authenticationServiceFacade"));
    }
}
