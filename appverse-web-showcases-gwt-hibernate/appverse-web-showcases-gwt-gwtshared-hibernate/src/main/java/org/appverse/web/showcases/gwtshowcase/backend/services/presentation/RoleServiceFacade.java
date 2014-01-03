package org.appverse.web.showcases.gwtshowcase.backend.services.presentation;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.appverse.web.framework.backend.api.services.presentation.IPresentationService;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTItemVO;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;

import java.util.List;

@RemoteServiceRelativePath("services/roleServiceFacade.rpc")
public interface RoleServiceFacade extends IPresentationService, RemoteService {

	void deleteRole(long roleId) throws Exception;

	RoleVO loadRole(long roleId) throws Exception;

	List<RoleVO> loadRoles() throws Exception;

	GWTPresentationPaginatedResult<RoleVO> loadRoles(
            GWTPresentationPaginatedDataFilter config) throws Exception;

	List<GWTItemVO> loadRolesMap() throws Exception;

	long saveRole(RoleVO roleVO) throws Exception;

}
