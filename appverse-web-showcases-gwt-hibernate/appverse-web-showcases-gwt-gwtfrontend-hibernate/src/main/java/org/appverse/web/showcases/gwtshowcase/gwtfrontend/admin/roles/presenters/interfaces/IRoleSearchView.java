package org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.presenters.interfaces;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.view.LazyView;
import com.mvp4g.client.view.ReverseViewInterface;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.framework.frontend.gwt.rpc.ApplicationAsyncCallback;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;

public interface IRoleSearchView extends
		ReverseViewInterface<IRoleSearchView.IRoleSearchPresenter>, LazyView,
		IsWidget {

	interface IRoleSearchPresenter {
		// Presenter methods here
		void addRole();

		void editRole(RoleVO role);

		void loadRoles(GWTPresentationPaginatedDataFilter config,
                       AsyncCallback<GWTPresentationPaginatedResult<RoleVO>> callback);

		void onRolesSearch();

		void searchRoles();
	}

	// View methods here
	public ApplicationAsyncCallback<GWTPresentationPaginatedResult<RoleVO>> getCallbackListRoles();

	public GWTPresentationPaginatedDataFilter getDataFilter();

	public void disableAddFeature();

	public void disableEditFeature();

}