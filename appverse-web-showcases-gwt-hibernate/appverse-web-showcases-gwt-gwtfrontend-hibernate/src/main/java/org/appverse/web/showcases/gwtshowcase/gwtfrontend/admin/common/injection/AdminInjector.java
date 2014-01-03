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
package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.common.injection;

import org.appverse.web.framework.frontend.gwt.commands.AuthenticationRpcCommand;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminConstants;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminImages;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminMessages;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.commands.RolesRpcCommand;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.users.commands.UserRpcCommand;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(AdminGinModule.class)
public interface AdminInjector extends Ginjector {

	AdminInjector INSTANCE = GWT.create(AdminInjector.class);

	AdminConstants getAdminConstants();

	AdminImages getAdminImages();

	AdminMessages getAdminMessages();

	AuthenticationRpcCommand getAuthenticationRpcCommand();
	
	UserRpcCommand getUserRpcCommand();

    RolesRpcCommand getRolesRpcCommand();

}
