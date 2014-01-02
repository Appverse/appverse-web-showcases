package org.appverse.web.showcases.gwtshowcase.backend.services.business;

import org.appverse.web.framework.backend.api.model.business.BusinessPaginatedDataFilter;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.Role;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface RoleService {

	@PreAuthorize("hasRole('ROLE_APP_ADMIN_CONSOLE_ACCESS') OR hasRole('ROLE_APP_ACCESS')")
	int countRoles(BusinessPaginatedDataFilter filter) throws Exception;

	@PreAuthorize("hasRole('ROLE_APP_ADMIN_CONSOLE_ACCESS') AND hasRole('ROLE_ROLE_DISABLE')")
	void deleteRole(long roleId) throws Exception;

	@PreAuthorize("hasRole('ROLE_APP_ADMIN_CONSOLE_ACCESS') OR hasRole('ROLE_APP_ACCESS')")
    Role loadRole(long pk) throws Exception;

	@PreAuthorize("hasRole('ROLE_APP_ADMIN_CONSOLE_ACCESS') OR hasRole('ROLE_APP_ACCESS')")
	List<Role> loadRoles() throws Exception;

	@PreAuthorize("hasRole('ROLE_APP_ADMIN_CONSOLE_ACCESS') OR hasRole('ROLE_APP_ACCESS')")
	List<Role> loadRoles(BusinessPaginatedDataFilter config) throws Exception;

	@PreAuthorize("hasRole('ROLE_APP_ADMIN_CONSOLE_ACCESS') AND ( (#role.id == 0 AND hasRole('ROLE_ROLE_CREATE')) OR (#role.id != 0 AND hasRole('ROLE_ROLE_EDIT')) )")
	long saveRole(Role role) throws Exception;
}