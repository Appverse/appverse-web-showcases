/*
 * GFT
 *
 * GWT project implemented using MVP4g showing the next features corresponding 
 * to the GWT front end architecture:
 * 
 * - Reverse MVP pattern (MVP4g implementation) 
 * - Dependency Injection (GIN implementation) 
 * - UiBinder (Plain GWT) 
 * - Lazy presenters and views (MVP4g implementation): presenters and views are not instantiated until they need to attend the first event 
 * - Command pattern: to show how to structure the application better, encapsulate reusable services, avoid large presenters, have a place 
 *   to implement caching, etc.. 
 * - History support (MVP4g implementation): back and forward browser buttons management, ability to "bookmark" a particular status of the 
 *   application, etc. 
 * - Cancellable Navigation (MVP4g implementation): ability to mark some transitions to ask for user confirmation (for instance in order 
 *   to prevent user to loss data because he is moving from one place ("screen") to another. 
 * - Editor framework (Plain GWT): Provides binding from POJO to GUI and the other way around 
 * - Code splitting (MVP4g implementation - using the concept of module, not splitters): This allows us not to load in the client browser 
 *   all the resources from the beginning. Instead, we specify the application to fetch them to the front end when they are requiered. 
 * - Layout templating (Plain GWT): simple example showing three areas: top area, bottom area and main (data area). Typically, the main area 
 *   is the one that is changing all the time to show the different "screens" 
 * - Other built-in patterns provided by MVP4G like singleton for presenters and views, etc. 
 * 
 * @author Miguel Fernandez Garrido
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