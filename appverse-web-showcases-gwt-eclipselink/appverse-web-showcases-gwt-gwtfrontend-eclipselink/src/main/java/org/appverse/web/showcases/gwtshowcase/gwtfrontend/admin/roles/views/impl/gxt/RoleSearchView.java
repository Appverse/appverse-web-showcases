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
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;
import com.sencha.gxt.cell.core.client.form.CheckBoxCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import org.appverse.web.framework.backend.api.model.presentation.PresentationDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.framework.frontend.gwt.helpers.filters.GxtPaginationConverter;
import org.appverse.web.framework.frontend.gwt.rmvp.ReverseComposite;
import org.appverse.web.framework.frontend.gwt.rpc.ApplicationAsyncCallback;
import org.appverse.web.framework.frontend.gwt.theme.client.search.AppverseSuggestAppearance.RiaSuggestStyle;
import org.appverse.web.framework.frontend.gwt.theme.client.search.SuggestTemplate;
import org.appverse.web.framework.frontend.gwt.widgets.search.suggest.events.LoadSuggestEvent;
import org.appverse.web.framework.frontend.gwt.widgets.search.suggest.events.SelectSuggestEvent;
import org.appverse.web.framework.frontend.gwt.widgets.search.suggest.handlers.LoadSuggestEventHandler;
import org.appverse.web.framework.frontend.gwt.widgets.search.suggest.handlers.SearchSuggestEventHandler;
import org.appverse.web.framework.frontend.gwt.widgets.search.suggest.handlers.SelectSuggestEventHandler;
import org.appverse.web.framework.frontend.gwt.widgets.search.suggest.impl.gxt.SuggestWidgetImpl;
import org.appverse.web.framework.frontend.gwt.widgets.search.suggest.model.SuggestModel;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.AdminMessages;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.common.injection.AdminInjector;
import org.appverse.web.showcases.gwtshowcase.gwtfrontend.admin.roles.presenters.interfaces.IRoleSearchView;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class RoleSearchView extends
		ReverseComposite<IRoleSearchView.IRoleSearchPresenter> implements
		IRoleSearchView {

	// Template to display VO in suggest cell
	interface MySuggestTemplate extends SuggestTemplate<RoleVO> {
		@Override
		@XTemplate("<div class='{style.searchItem}'><h3><span>{role.created:date(\"d/M/yyyy\")}<br /> {role.createdBy}</span>{role.name}</h3>{role.description:nullsafe}</div>")
		SafeHtml render(RoleVO role, RiaSuggestStyle style);
	}

	interface RoleSearchViewUiBinder extends UiBinder<Widget, RoleSearchView> {
	}

	// Marker properties for roles grid
	public interface RoleVOProperties extends PropertyAccess<RoleVO> {

		ValueProvider<RoleVO, Boolean> active();

		ModelKeyProvider<RoleVO> id();

		ValueProvider<RoleVO, String> listEnvironments();

		ValueProvider<RoleVO, String> listPermissions();

		ValueProvider<RoleVO, String> name();
	}

	// Marker properties for SuggestField
	public interface RoleVOSugProperties extends SuggestModel<RoleVO> {
		@Override
		@Path("description")
		ValueProvider<RoleVO, String> description();

		@Override
		@Path("id")
		ModelKeyProvider<RoleVO> id();

		@Override
		@Path("name")
		LabelProvider<RoleVO> label();

		@Override
		@Path("name")
		ValueProvider<RoleVO, String> name();
	}

	private static RoleSearchViewUiBinder uiBinder = GWT
			.create(RoleSearchViewUiBinder.class);

	@UiField(provided = true)
	ListStore<RoleVO> store;

	@UiField(provided = true)
	ColumnModel<RoleVO> cm;

	@UiField
	GridView<RoleVO> view;

	@UiField
	Grid<RoleVO> roleListTable;

	@UiField
	PagingToolBar toolBar;

	@UiField
	TextButton addRoleButton;

	@UiField
	SuggestWidgetImpl<RoleVO> suggestSearch;

	ApplicationAsyncCallback<GWTPresentationPaginatedResult<RoleVO>> callbackListRoles;
	GWTPresentationPaginatedDataFilter dataFilter;

	boolean disableEditFeature = false;

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void createView() {
		initProvidedWidgets();
		initWidget(uiBinder.createAndBindUi(this));

		initLoaders();
		// Selection list option clicked
		suggestSearch.addSuggestEventHandler(new SelectSuggestEventHandler() {
			@Override
			public void onSelect(final SelectSuggestEvent event) {
				presenter.searchRoles();
			}
		});
		suggestSearch
				.addSuggestEventHandler(new LoadSuggestEventHandler<RoleVO>() {
                    // Load list
                    @Override
                    public void onLoad(final LoadSuggestEvent<RoleVO> event) {
                        presenter.loadRoles(event.getConfig(),
                                event.getCallback());
                    }
                });
        suggestSearch.addSuggestEventHandler(new SearchSuggestEventHandler() {
            @Override
            public void onSearch() {
                presenter.searchRoles();
            }
        });

	}

	@Override
	public void disableAddFeature() {
		addRoleButton.disable();
	}

	@Override
	public void disableEditFeature() {
		disableEditFeature = true;
	}

	@Override
	public ApplicationAsyncCallback<GWTPresentationPaginatedResult<RoleVO>> getCallbackListRoles() {
		return callbackListRoles;
	}

	@Override
	public GWTPresentationPaginatedDataFilter getDataFilter() {
		// TODO search automatic filter update system
		// dataFilter.getColumns().clear();
		// dataFilter.getValues().clear();
		dataFilter.resetConditions();

		if ((suggestSearch.getText().trim() != null)
				&& (suggestSearch.getText().trim().length() > 0)) {
			StringBuilder sb = new StringBuilder();
			sb.append(PresentationDataFilter.WILDCARD_ALL)
					.append(suggestSearch.getText())
					.append(PresentationDataFilter.WILDCARD_ALL);
			// dataFilter.getValues().add(sb.toString());
			// dataFilter.getColumns().add("name");
			// dataFilter.getLikes().add(true);
			dataFilter.addLikeCondition("name", sb.toString());
		}
		return dataFilter;
	}

	public void initLoaders() {
		GWT.log("initLoaders");
		final RpcProxy<PagingLoadConfig, PagingLoadResult<RoleVO>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<RoleVO>>() {
			@Override
			public void load(final PagingLoadConfig loadConfig,
					final AsyncCallback<PagingLoadResult<RoleVO>> callback) {
				GWT.log("Proxy load method called");
				callbackListRoles = new ApplicationAsyncCallback<GWTPresentationPaginatedResult<RoleVO>>() {
					@Override
					@SuppressWarnings("unchecked")
					public void onSuccess(
							final GWTPresentationPaginatedResult<RoleVO> result) {
						callback.onSuccess((PagingLoadResult<RoleVO>) GxtPaginationConverter
								.convert(result));

					}
				};

				dataFilter = GxtPaginationConverter.convert(loadConfig);
				// CAUTION
				// You must use "getDataFilter()" to avoid loosing any kind of
				// filter when using toolbar
				presenter.loadRoles(getDataFilter(), callbackListRoles);
			}
		};

		final PagingLoader<PagingLoadConfig, PagingLoadResult<RoleVO>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<RoleVO>>(
				proxy);
		loader.setRemoteSort(true);

		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, RoleVO, PagingLoadResult<RoleVO>>(
				store));

		roleListTable.setLoader(loader);
		toolBar.bind(loader);
		loader.load();

		MySuggestTemplate template = GWT.create(MySuggestTemplate.class);
		// It's REQUIRED to set a template
		suggestSearch.setTemplate(template);
	}

	/**
	 * This method will only create the widgets that are marked as provided. It
	 * does not add the widget to the panel: this is still done by the UI
	 * binder.
	 */
	private void initProvidedWidgets() {
		GWT.log("initProvidedWidgets");
		store = new ListStore<RoleVO>(new ModelKeyProvider<RoleVO>() {
			@Override
			public String getKey(final RoleVO item) {
				return "" + item.getId();
			}
		});

		final RoleVOProperties props = GWT.create(RoleVOProperties.class);
		final AdminMessages am = AdminInjector.INSTANCE.getAdminMessages();
		final ColumnConfig<RoleVO, String> nameColumn = new ColumnConfig<RoleVO, String>(
				props.name(), 150, am.rolesTableName());
		final ColumnConfig<RoleVO, String> permColumn = new ColumnConfig<RoleVO, String>(
				props.listPermissions(), 150, am.rolesTablePerms());
        permColumn.setSortable(false);
		final ColumnConfig<RoleVO, String> environmentColumn = new ColumnConfig<RoleVO, String>(
				props.listEnvironments(), 150, am.rolesTableEnvironment());
        environmentColumn.setSortable(false);
		final ColumnConfig<RoleVO, Boolean> activeColumn = new ColumnConfig<RoleVO, Boolean>(
				props.active(), 75, am.rolesTableActive());
		final CheckBoxCell checkboxCell = new CheckBoxCell();
		checkboxCell.setReadOnly(true);
		activeColumn.setCell(checkboxCell);

		final List<ColumnConfig<RoleVO, ?>> l = new ArrayList<ColumnConfig<RoleVO, ?>>();
		l.add(nameColumn);
		l.add(permColumn);
		l.add(environmentColumn);
		l.add(activeColumn);

		cm = new ColumnModel<RoleVO>(l);

	}

	@UiHandler("addRoleButton")
	public void onAddRoleButtonClick(final SelectEvent event) {
		presenter.addRole();
	}

	@UiHandler("roleListTable")
	public void onGridDoubleClick(final RowDoubleClickEvent event) {
		if (!disableEditFeature) {
			final int row = event.getRowIndex();
			final RoleVO role = store.get(row);
			presenter.editRole(role);
		}
	}

	public void setDataFilter(
			final GWTPresentationPaginatedDataFilter dataFilter) {
		this.dataFilter = dataFilter;
	}
}