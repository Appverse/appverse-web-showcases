package org.appverse.web.showcases.gwtshowcase.backend.services;

import com.google.gwt.user.client.rpc.RemoteService;
import org.appverse.web.framework.backend.api.helpers.log.AutowiredLogger;
import org.appverse.web.framework.backend.frontfacade.rest.application.JerseyInitRestApplication;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import java.util.Map;

public class ShowcaseRestInitApplication extends JerseyInitRestApplication {

    private static Logger logger = LoggerFactory.getLogger(ShowcaseRestInitApplication.class);

    @Inject
    public ShowcaseRestInitApplication(ServletContext servletContext) {
    	super(servletContext);

        // This is required by the FileUploadController
        register(MultiPartFeature.class);

        WebApplicationContext springFactory = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        // Scan entire Spring factory for beans annotated with @Path and register them, so we don't need to do this manually.
        // In theory this should not be necessary and Jersey could find the classes itself and register them in Spring,
        // but this is not working very well so far: https://java.net/jira/browse/JERSEY-2301
        Map<String, Object> map = springFactory.getBeansWithAnnotation(Path.class);
        Map<String, RemoteService> gwtRpcMap = springFactory.getBeansOfType(RemoteService.class);
        Map.Entry<String, Object> lastEntry = null;
        try {
            int n = 0;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                lastEntry = entry;
                if (gwtRpcMap.containsKey(entry.getKey())) {
                    // The only reason we have this is the fact that GWTAuthenticationServiceFacadeImpl extends
                    // AuthenticationServiceFacadeImpl, so it inherits the @Path annotation, so it can be considered
                    // a JAX-RS end-point and it will conflict with AuthenticationServiceFacadeImpl.
                    // TODO: find a better way to deal with it. In the first place if we are not using GWT-RPC I don't
                    // see why we need GWTAuthenticationServiceFacadeImpl.
                    logger.warn("ignoring bean " + entry.getKey() + " because it is already a GWT-RPC end-point");
                    continue;
                }
                logger.debug("Registering bean " + entry.getKey() + " in Jersey. Class: " + entry.getValue().getClass());
                register(entry.getValue());
                n++;
            }
            logger.debug("beans registered: " + n);
        } catch (RuntimeException ex) {
            logger.error("Error registering bean " + lastEntry.getKey() + " in Jersey. Class: " + lastEntry.getValue().getClass(), ex);
            throw ex;
        }
    }
}
