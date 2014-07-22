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
package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.commands.impl.live;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;

import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.framework.frontend.gwt.callback.AppverseCallback;
import org.appverse.web.framework.frontend.gwt.commands.AbstractRestCommand;
import org.appverse.web.framework.frontend.gwt.json.ApplicationJsonAsyncCallback;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.UserVO;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.UserServiceFacade;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminEventBus;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.commands.UserRestRpcCommand;

/**
 * This class is deprecated because the only thing it is doing is to specify the bean and method names and this can
 * be already specified in the UserRestServiceFacade via the @Path annotations. JSONController will also be
 * deprecated because it only dispatches the calls to the corresponding Spring beans which is also already done
 * by the JAX-RS servlet (which in this case is delegating all calls to JSONController).
 * 
 * Appverse Web GWT-API proposes and recommends the use of the Command pattern.
 * In the CommandImpl we can implement a sort of quick cache, or initialize some specific things for the service being accessed.
 * In that case, it is true that most of the responsabilities of the typical command impl in Appverse are now carried on in the RestyGWT, however
 * we still think it makes sense to have this extra layer of separation between the service and the Presenter.
 */

public class UserRestRpcCommandImpl extends AbstractRestCommand<AdminEventBus,UserServiceFacade.UserRestServiceFacade> implements UserRestRpcCommand {


    @Inject
    private UserServiceFacade.UserRestServiceFacade userRestService;

	@Override
	public void deleteUser(UserVO user,
                           ApplicationJsonAsyncCallback<Void> asyncCallback) {
		userRestService.deleteUser(user, asyncCallback);
	}

	@Override
	public void loadUser(long userId, ApplicationJsonAsyncCallback<UserVO> callback) {
		userRestService.loadUser(Long.valueOf(userId), callback);
	}

	@Override
	public void loadUsers(
			GWTPresentationPaginatedDataFilter config,
			AppverseCallback<GWTPresentationPaginatedResult<UserVO>> callback) {
		userRestService.loadUsers(config, callback);
	}

    @Override
    public void saveUser(UserVO user, ApplicationJsonAsyncCallback<Long> applicationRestAsyncCallback) {
    	userRestService.saveUser(user, applicationRestAsyncCallback);
    }

    @Override
    public UserServiceFacade.UserRestServiceFacade createService() {
        //return GWT.create(UserServiceFacade.UserRestServiceFacadeOld.class);
    	return null;
    }
}
