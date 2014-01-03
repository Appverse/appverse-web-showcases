package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.presenters;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import org.appverse.web.framework.frontend.gwt.rpc.ApplicationAsyncCallback;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminEventBus;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.common.injection.AdminInjector;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.commands.RolesRpcCommand;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.presenters.interfaces.IRoleEditView;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.views.impl.gxt.RoleEditView;

@Presenter(view = RoleEditView.class)
public class RoleEditPresenter extends
		LazyPresenter<IRoleEditView, AdminEventBus> implements
		IRoleEditView.IRoleEditPresenter {

	private AdminInjector injector;
	private RolesRpcCommand rolesRpcCommand;

	@Override
	public void bindView() {
	}

	// Button cancel pressed
	@Override
	public void cancel() {
		eventBus.rolesSearch();
	}

	@Override
	public void createPresenter() {
		injector = AdminInjector.INSTANCE;
		rolesRpcCommand = injector.getRolesRpcCommand();
	}

	private void loadMappings() {

	}

	@Override
	public void onRolesEdit(final RoleVO role) {
		view.setRole(role);
/* TODO

		if (role.getId() == 0) {
			view.setCreationMode(PrincipalInformation
					.hasPrincialAuthority(AuthoritiesConstants.ROLE_ROLE_CREATE));
		} else {
			view.setEditMode(
					PrincipalInformation
							.hasPrincialAuthority(AuthoritiesConstants.ROLE_ROLE_EDIT),
					PrincipalInformation
							.hasPrincialAuthority(AuthoritiesConstants.ROLE_ROLE_DISABLE));
		}
*/

        view.setEditMode(true, true);

        // Load initial data
		loadMappings();
		eventBus.adminLayoutChangeBody(view.asWidget());
	}

	// Button save pressed
	@Override
	public void save(final RoleVO role) {
		boolean valid = view.validate(role);
		if (valid) {
			rolesRpcCommand.saveRole(role,
					new ApplicationAsyncCallback<Long>() {
						@Override
						public void onSuccess(final Long ret) {
							eventBus.rolesSearch();
						}
					});
		}
	}
}