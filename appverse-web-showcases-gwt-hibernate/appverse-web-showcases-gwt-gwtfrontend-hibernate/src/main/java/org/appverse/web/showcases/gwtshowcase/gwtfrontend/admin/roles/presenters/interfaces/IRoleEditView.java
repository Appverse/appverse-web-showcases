package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.presenters.interfaces;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.view.LazyView;
import com.mvp4g.client.view.ReverseViewInterface;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTItemVO;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.common.views.ValidatorView;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.common.views.toolbar.ToolbarView;

import java.util.List;

public interface IRoleEditView extends
		ReverseViewInterface<IRoleEditView.IRoleEditPresenter>, LazyView,
		IsWidget, ToolbarView, ValidatorView<RoleVO> {

	interface IRoleEditPresenter {
		public void cancel();

/* We do not support delete currently, just deactivation
        public void delete(RoleVO role);
*/

		// Presenter methods here
		public void onRolesEdit(RoleVO role);

		public void save(RoleVO role);
	}

	public void setRole(RoleVO role);
}