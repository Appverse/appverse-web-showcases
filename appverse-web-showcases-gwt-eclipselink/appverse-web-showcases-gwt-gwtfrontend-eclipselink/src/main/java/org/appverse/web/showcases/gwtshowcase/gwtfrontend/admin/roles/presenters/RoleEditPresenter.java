/*
 Copyright (c) 2012 GFT Appverse, S.L., Sociedad Unipersonal.

 This Source Code Form is subject to the terms of the Mozilla Public
 License, v. 2.0. If a copy of the MPL was not distributed with this
 file, You can obtain one at http://mozilla.org/MPL/2.0/.

 Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the conditions of the Mozilla Public License v2.0
 are met.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. EXCEPT IN CASE OF WILLFUL MISCONDUCT OR GROSS NEGLIGENCE, IN NO EVENT
 SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE)
 ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 POSSIBILITY OF SUCH DAMAGE.
 */
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