package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.presenters;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminEventBus;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.common.injection.AdminInjector;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.commands.RolesRpcCommand;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.presenters.interfaces.IRoleSearchView;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.views.impl.gxt.RoleSearchView;

@Presenter(view = RoleSearchView.class)
public class RoleSearchPresenter extends
		LazyPresenter<IRoleSearchView, AdminEventBus> implements
		IRoleSearchView.IRoleSearchPresenter {

	private AdminInjector injector;
	private RolesRpcCommand rolesRpcCommand;

	@Override
	public void addRole() {
		eventBus.rolesEdit(new RoleVO());
	}

	private void applySecurity() {
/* TODO
		if (!PrincipalInformation
				.hasPrincialAuthority(AuthoritiesConstants.ROLE_ROLE_CREATE)) {
			view.disableAddFeature();
		}

		if (!PrincipalInformation
				.hasPrincialAuthority(AuthoritiesConstants.ROLE_ROLE_EDIT)) {
			view.disableEditFeature();
		}
*/
	}

	@Override
	public void bindView() {
		// We do nothing here as we use remote pagination and the view uses
		// and RPCProxy and loader

		// We only apply security to the view
		applySecurity();
	}

	@Override
	public void createPresenter() {
		injector = AdminInjector.INSTANCE;
		rolesRpcCommand = injector.getRolesRpcCommand();
	}

	@Override
	public void editRole(final RoleVO role) {
		eventBus.rolesEdit(role);
	}

	@Override
	public void loadRoles(final GWTPresentationPaginatedDataFilter config,
			final AsyncCallback<GWTPresentationPaginatedResult<RoleVO>> callback) {
		rolesRpcCommand.loadRoles(config, callback);
	}

	@Override
	public void onRolesSearch() {
	    searchRoles();
		eventBus.adminLayoutChangeBody(view.asWidget());
	}

	@Override
	public void searchRoles() {
		this.loadRoles(view.getDataFilter(), view.getCallbackListRoles());
	}

}