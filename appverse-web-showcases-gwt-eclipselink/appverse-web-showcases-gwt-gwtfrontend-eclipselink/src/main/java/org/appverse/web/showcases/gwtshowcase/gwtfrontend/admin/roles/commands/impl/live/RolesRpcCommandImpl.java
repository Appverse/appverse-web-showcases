package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.commands.impl.live;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTItemVO;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.framework.frontend.gwt.commands.AbstractRpcCommand;
import org.appverse.web.framework.frontend.gwt.rpc.ApplicationAsyncCallback;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.RoleServiceFacade;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.RoleServiceFacadeAsync;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminEventBus;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.commands.RolesRpcCommand;

import java.util.List;

public class RolesRpcCommandImpl extends AbstractRpcCommand<AdminEventBus>
		implements RolesRpcCommand {

	private final RoleServiceFacadeAsync serviceRoles = (RoleServiceFacadeAsync) GWT
			.create(RoleServiceFacade.class);

	@Override
	public void deleteRole(final RoleVO role,
			final ApplicationAsyncCallback<Void> callback) {
		serviceRoles.deleteRole(role.getId(), callback);
	}

	protected RoleServiceFacadeAsync getServiceRoles() {
		return super.getService(serviceRoles);
	}

	@Override
	public void loadRoles(final ApplicationAsyncCallback<List<RoleVO>> callback) {
		getServiceRoles().loadRoles(callback);
	}

	@Override
	public void loadRoles(final GWTPresentationPaginatedDataFilter config,
			final AsyncCallback<GWTPresentationPaginatedResult<RoleVO>> callback) {
		getServiceRoles().loadRoles(config, callback);
	}

	@Override
	public void loadRolesMap(
			final ApplicationAsyncCallback<List<GWTItemVO>> callback) {
		getServiceRoles().loadRolesMap(callback);
	}

	@Override
	public void saveRole(final RoleVO role,
			final ApplicationAsyncCallback<Long> callback) {
		getServiceRoles().saveRole(role, callback);
	}

}
