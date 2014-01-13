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
package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.editors.impl.gxt;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.DualListField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.EmptyValidator;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTItemVO;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;

import java.util.List;

public class RoleVOEditor extends Composite implements Editor<RoleVO> {

	interface _UiBinder extends UiBinder<Widget, RoleVOEditor> {
	}

	@UiField
	@Path("name")
	TextField addName;
	@UiField
	CheckBox active;
	@UiField
	@Path("description")
	TextField addDescription;

	private static _UiBinder uiBinder = GWT.create(_UiBinder.class);

	public RoleVOEditor() {
		initWidget();
	}

	private void initWidget() {
		initProvidedWidgets();
		initWidget(uiBinder.createAndBindUi(this));
	}

	interface ItemProperties extends PropertyAccess<GWTItemVO> {
		ModelKeyProvider<GWTItemVO> id();

		LabelProvider<GWTItemVO> name();

		@Path("name")
		ValueProvider<GWTItemVO, String> nameProp();
	}

	/**
	 * This method will only create the widgets that are marked as provided. It
	 * does not add the widget to the panel: this is still done by the UI
	 * binder.
	 */
	private void initProvidedWidgets() {
		final ItemProperties props = GWT.create(ItemProperties.class);
	}

    public void setEditMode(final boolean canSave, final boolean canDelete){
        if (!canDelete){
            // For roles 'delete' permission mean the user is able to unpublish (or publish) a role
            active.disable();
        }
    }
}