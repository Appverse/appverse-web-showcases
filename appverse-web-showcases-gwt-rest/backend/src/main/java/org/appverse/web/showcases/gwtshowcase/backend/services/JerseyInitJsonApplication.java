package org.appverse.web.showcases.gwtshowcase.backend.services;

import org.appverse.web.framework.backend.api.helpers.security.XSSSecurityFilter;
import org.appverse.web.framework.backend.frontfacade.json.controllers.JSONController;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.impl.live.UserRestServiceFacadeImpl;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * TODO: move this to the correct package
 */
public class JerseyInitJsonApplication extends ResourceConfig {

    public JerseyInitJsonApplication() {
        // TODO: Why can't we use FileUploadController here? Check how FileUploadController works.
        // TODO: not sure why it was necessary to register the JacksonFeature because according to the documentation
        // it should be automatically added.
        super(JSONController.class, XSSSecurityFilter.class, JacksonFeature.class);

        // find all facades in the same package
        packages(UserRestServiceFacadeImpl.class.getPackage().getName());
    }
}
