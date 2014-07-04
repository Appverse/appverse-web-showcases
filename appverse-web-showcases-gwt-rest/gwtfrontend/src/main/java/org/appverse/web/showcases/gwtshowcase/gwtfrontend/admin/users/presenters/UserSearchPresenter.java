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
package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.presenters;

import com.google.gwt.user.client.Window;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.framework.frontend.gwt.callback.AppverseCallback;
import org.appverse.web.framework.frontend.gwt.helpers.dispatcher.AppverseDispatcher;
import org.appverse.web.framework.frontend.gwt.helpers.security.PrincipalInformation;
import org.appverse.web.showcases.gwtshowcase.backend.constants.AuthoritiesConstants;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.UserVO;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.UserServiceFacade;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminEventBus;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.commands.UserRestRpcCommand;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.presenters.interfaces.UserSearchView;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.views.impl.gxt.UserSearchViewImpl;
import org.fusesource.restygwt.client.Defaults;
import javax.inject.Inject;

@Presenter(view = UserSearchViewImpl.class)
public class UserSearchPresenter extends
		LazyPresenter<UserSearchView, AdminEventBus> implements
		UserSearchView.IUserSearchPresenter {

    @Deprecated
    @Inject
	private UserRestRpcCommand userRestRpcCommand;

    @Inject // no need to declare this in any other place because GIN will use GWT.create if it can't find it
    private UserServiceFacade.UserRestServiceFacade newUserRestRpcCommand;

    private boolean useDeprecatedCommand = false;

    public UserSearchPresenter() {
        // TODO: move this to the entry point
        // The rest sufix has to match what is defined in the web.xml for the CXFServlet.
        Defaults.setServiceRoot(com.google.gwt.core.client.GWT.getHostPageBaseURL() + "admin/rest/");
        Defaults.setDispatcher(AppverseDispatcher.INSTANCE);

        String deprecated = Window.Location.getParameter("deprecated");
        if (deprecated != null && deprecated.equals("true")) {
            useDeprecatedCommand = true;
        }
    }

	@Override
	public void addUser() {
		eventBus.userEdit(new UserVO());

	}

	private void applySecurity() {
		if (!PrincipalInformation
				.hasPrincialAuthority(AuthoritiesConstants.ROLE_USER_CREATE)) {
			view.disableAddFeature();
		}

		if (!PrincipalInformation
				.hasPrincialAuthority(AuthoritiesConstants.ROLE_USER_EDIT)) {
			view.disableEditFeature();
		}
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
		//injector = AdminInjector.INSTANCE;
//		userRpcCommand = injector.getUserRpcCommand();
		//userRestRpcCommand = injector.getUserRestRpcCommand();
	}

	@Override
	public void editUser(final UserVO user) {
		eventBus.userEdit(user);
	}

	@Override
	public void onPlaceUserSearch() {
		onUsersSearch(false);
	}
	
	@Override
	public void onUsersSearch(final boolean refresh) {
		if (refresh) {
			searchUsers();
		}
		eventBus.adminLayoutChangeBody(view.asWidget());
	}

	@Override
	public void searchUsers() {
		this.loadUsers(view.getDataFilter(), view.getCallbackRestListUsers());
	}
	
	public void onInit() {
		eventBus.adminLayoutChangeBody(view.asWidget());
	}

	@Override
	public void loadUsers(
			GWTPresentationPaginatedDataFilter dataFilter,
			AppverseCallback<GWTPresentationPaginatedResult<UserVO>> callbackRestListUsers) {
        if (useDeprecatedCommand) {
            userRestRpcCommand.loadUsers(dataFilter, callbackRestListUsers);
        } else {
            newUserRestRpcCommand.loadUsers(dataFilter, callbackRestListUsers);
        }
	}

}