package org.appverse.web.showcases.gwtshowcase.backend.services;

import org.appverse.web.framework.backend.api.helpers.security.XSSSecurityFilter;
import org.appverse.web.framework.backend.api.services.presentation.AuthenticationRestServiceFacade;
import org.appverse.web.framework.backend.frontfacade.json.controllers.JSONController;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.UserServiceFacade;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.impl.live.UserRestServiceFacadeImpl;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.ServletContext;

/**
 * TODO: move this to the correct package
 */
public class JerseyInitJsonApplication extends ResourceConfig {

    @Inject
    public JerseyInitJsonApplication(ServletContext servletContext) {
        // TODO: Why can't we use FileUploadController here? Check how FileUploadController works.
        // TODO: not sure why it was necessary to register the JacksonFeature because according to the documentation
        // it should be automatically added.
        super(/*JSONController.class, */XSSSecurityFilter.class, JacksonFeature.class);

        if (true) {
            WebApplicationContext springFactory = WebApplicationContextUtils.getWebApplicationContext(servletContext);

            // Manually register a bean created by Spring
            // TODO: scan entire Spring factory for beans annotated with @Path and register them, so we don't need to do this manually.
            // Letting Jersey register the beans does not work because in this case Spring will not intercept the calls.
            // See comments bellow.
            register(springFactory.getBean(UserServiceFacade.class));
            //TODO This should be done in the Framework version of JerseyINitJsonApplication
            register(springFactory.getBean("authenticationServiceFacade"));
        } else {
            // This does not work because of this issue: https://java.net/jira/browse/JERSEY-2301
            // After the bug is fixed this can be reenabled, but in this case remember to disable the Spring scan in
            // this package otherwise the component may be created twice.
            // find all facades in the same package
            packages(UserRestServiceFacadeImpl.class.getPackage().getName());
        }
    }
}
