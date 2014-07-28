package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin;


import org.appverse.web.framework.frontend.gwt.entrypoint.AppverseRestAbstractEntryPoint;

/**
 * In case of GWT with REST we need to extend the Appverse Abstract rest entry point and provide the App path to it.
 * App path is just the same path the app have configured in the Jersey servlet url-pattern parameter.
 */
public class AdminEntryPoint extends AppverseRestAbstractEntryPoint {
	
	//App path is just the same path the app have configured in the Jersey servlet url-pattern parameter.
	private String path="admin/rest";
	
    public String provideAppPath() {
    	return path;
    }
    
}
