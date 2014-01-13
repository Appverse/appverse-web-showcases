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
package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.views.impl.gxt;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import org.appverse.web.framework.frontend.gwt.views.AbstractEditorValidationView;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.editors.impl.gxt.RoleVOEditor;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.presenters.interfaces.IRoleEditView;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.common.widgets.toolbar.events.CancelEvent;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.common.widgets.toolbar.events.DeleteEvent;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.common.widgets.toolbar.events.SaveEvent;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.common.widgets.toolbar.impl.gxt.ToolbarWidgetImpl;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;

@Singleton
public class RoleEditView
		extends
		AbstractEditorValidationView<IRoleEditView.IRoleEditPresenter, RoleVO, RoleVOEditor>
		implements IRoleEditView {

	interface Driver extends SimpleBeanEditorDriver<RoleVO, RoleVOEditor> {
	}

	interface RoleEditViewUiBinder extends UiBinder<Widget, RoleEditView> {
	}

	private static RoleEditViewUiBinder uiBinder = GWT
			.create(RoleEditViewUiBinder.class);
	@UiField
	RoleVOEditor roleEditor;

	@UiField
    ToolbarWidgetImpl roleToolbar;

	@Override
	public void createDriver() {
		driver = GWT.create(Driver.class);
	}

	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
		driver.initialize(roleEditor);
		roleToolbar.addToolbarEventHandler(this);

	}

	/*
	 * Handler for delete event launched by ToolbarWidget
	 * 
	 * @see com.gft.storefront.gwtfrontend.common.widgets.toolbar.handlers.
	 * SaveEventHandler
	 * #onSave(com.gft.storefront.gwtfrontend.common.widgets.toolbar
	 * .events.SaveEvent)
	 */
	@Override
	public void onCancel(final CancelEvent event) {
		presenter.cancel();
	}

	/*
	 * Handler for delete event launched by ToolbarWidget
	 * 
	 * @see com.gft.storefront.gwtfrontend.common.widgets.toolbar.handlers.
	 * SaveEventHandler
	 * #onSave(com.gft.storefront.gwtfrontend.common.widgets.toolbar
	 * .events.SaveEvent)
	 */
	@Override
	public void onDelete(final DeleteEvent event) {
/* We do not support delete currently, just deactivation
		// Secured
		if (PrincipalInformation
				.hasPrincialAuthority(AuthoritiesConstants.ROLE_ROLE_DISABLE)) {
			final RoleVO role = driver.flush();
			presenter.delete(role);
		}
*/
	}

	/*
	 * Handler for save event launched by ToolbarWidget
	 * 
	 * @see com.gft.storefront.gwtfrontend.common.widgets.toolbar.handlers.
	 * SaveEventHandler
	 * #onSave(com.gft.storefront.gwtfrontend.common.widgets.toolbar
	 * .events.SaveEvent)
	 */
	@Override
	public void onSave(final SaveEvent event) {
		final RoleVO role = driver.flush();
		presenter.save(role);
	}

	@Override
	public void setCreationMode(final boolean canSave) {
		roleToolbar.setCreationMode(canSave);
	}

	@Override
	public void setEditMode(final boolean canSave, final boolean canDelete) {
        // Roles can not be physically removed

/* TODO
        roleToolbar.hideDeleteButton();
*/
        roleToolbar.setEditMode(canSave, false);

        // Delete roles  means be able to publish / unpublish roles
        roleEditor.setEditMode(canSave, canDelete);
	}

	@Override
	public void setRole(final RoleVO role) {
		driver.edit(role);
		driver.setConstraintViolations(new ArrayList<ConstraintViolation<?>>());
	}

}