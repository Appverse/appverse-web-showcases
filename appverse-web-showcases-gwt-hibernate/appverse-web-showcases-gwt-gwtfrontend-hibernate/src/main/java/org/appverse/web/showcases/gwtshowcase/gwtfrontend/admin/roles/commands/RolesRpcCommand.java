package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.commands;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTItemVO;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.framework.frontend.gwt.rpc.ApplicationAsyncCallback;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;

import java.util.List;

public interface RolesRpcCommand {
	// TODO: Add IRpcCommand to new version of RIAFramework
	/* Textends IRpcCommand<UserServiceFacadeAsync> { */

	void deleteRole(final RoleVO role,
                    final ApplicationAsyncCallback<Void> callback);

	void loadRoles(ApplicationAsyncCallback<List<RoleVO>> callback);

	void loadRoles(GWTPresentationPaginatedDataFilter config,
                   AsyncCallback<GWTPresentationPaginatedResult<RoleVO>> callback);

	void loadRolesMap(ApplicationAsyncCallback<List<GWTItemVO>> callback);

	void saveRole(final RoleVO role,
                  final ApplicationAsyncCallback<Long> callback);
}
