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
/* Example
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