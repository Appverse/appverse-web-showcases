/*
 Copyright (c) 2012 GFT Appverse, S.L., Sociedad Unipersonal.

 This Source Code Form is subject to the terms of the GNU Lesser General Public License (LGPL), 
 version 2.1. If a copy of the MPL was not distributed with this 
 file, You can obtain one at https://www.gnu.org/licenses/lgpl-2.1.html. 

 Redistribution and use in source and binary forms, with or without modification, 
 are permitted provided that the conditions of the GNU Lesser General Public License (LGPL) 
 version 2.1 are met.
 
 This project includes the original Liferay Portlet Bridge with PrimeFaces4 example that can
 be found here: https://github.com/liferay/liferay-faces/tree/master/demos/bridge/primefaces4-portlet
 and it is released under GNU Lesser General Public License (LGPL) version 2.1. and 
 extends it with it own source code. For this reason in the original Liferay code the original packages
 have been kept and the original Liferay header as well. Appverse header have been added so both 
 the orginal one (Liferay) and Appverse can be found in some source files.
  

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

/**
 * Copyright (c) 2000-2014 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package com.liferay.faces.demos.bean;

import java.util.Enumeration;
import java.util.Map;

import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.WindowState;
import javax.portlet.faces.preference.Preference;

import com.liferay.faces.demos.util.FacesMessageUtil;


/**
 * @author  Neil Griffin
 */
@ManagedBean(name = "portletPreferencesBackingBean")
@RequestScoped
public class PortletPreferencesBackingBean {

	/**
	 * Resets/restores the values in the portletPreferences.xhtml Facelet composition with portlet preference default
	 * values.
	 */
	public void reset() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		PortletRequest portletRequest = (PortletRequest) externalContext.getRequest();
		PortletPreferences portletPreferences = portletRequest.getPreferences();

		try {
			Enumeration<String> preferenceNames = portletPreferences.getNames();

			while (preferenceNames.hasMoreElements()) {
				String preferenceName = preferenceNames.nextElement();
				portletPreferences.reset(preferenceName);
			}

			portletPreferences.store();

			// Switch the portlet mode back to VIEW.
			ActionResponse actionResponse = (ActionResponse) externalContext.getResponse();
			actionResponse.setPortletMode(PortletMode.VIEW);
			actionResponse.setWindowState(WindowState.NORMAL);

			FacesMessageUtil.addGlobalSuccessInfoMessage(facesContext);
		}
		catch (Exception e) {
			FacesMessageUtil.addGlobalUnexpectedErrorMessage(facesContext);
		}

	}

	/**
	 * Saves the values in the portletPreferences.xhtml Facelet composition as portlet preferences.
	 */
	public void submit() {

		// The JSR 329 specification defines an EL variable named mutablePortletPreferencesValues that is being used in
		// the portletPreferences.xhtml Facelet composition. This object is of type Map<String, Preference> and is
		// designed to be a model managed-bean (in a sense) that contain preference values. However the only way to
		// access this from a Java class is to evaluate an EL expression (effectively self-injecting) the map into
		// this backing bean.
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		String elExpression = "mutablePortletPreferencesValues";
		ELResolver elResolver = facesContext.getApplication().getELResolver();
		@SuppressWarnings("unchecked")
		Map<String, Preference> mutablePreferenceMap = (Map<String, Preference>) elResolver.getValue(
				facesContext.getELContext(), null, elExpression);

		// Get a list of portlet preference names.
		PortletRequest portletRequest = (PortletRequest) externalContext.getRequest();
		PortletPreferences portletPreferences = portletRequest.getPreferences();
		Enumeration<String> preferenceNames = portletPreferences.getNames();

		try {

			// For each portlet preference name:
			while (preferenceNames.hasMoreElements()) {

				// Get the value specified by the user.
				String preferenceName = preferenceNames.nextElement();
				String preferenceValue = mutablePreferenceMap.get(preferenceName).getValue();

				// Prepare to save the value.
				if (!portletPreferences.isReadOnly(preferenceName)) {
					portletPreferences.setValue(preferenceName, preferenceValue);
				}
			}

			// Save the preference values.
			portletPreferences.store();

			// Switch the portlet mode back to VIEW.
			ActionResponse actionResponse = (ActionResponse) externalContext.getResponse();
			actionResponse.setPortletMode(PortletMode.VIEW);
			actionResponse.setWindowState(WindowState.NORMAL);

			// Report a successful message back to the user as feedback.
			FacesMessageUtil.addGlobalSuccessInfoMessage(facesContext);
		}
		catch (Exception e) {
			FacesMessageUtil.addGlobalUnexpectedErrorMessage(facesContext);
		}
	}
}
