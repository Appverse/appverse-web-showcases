package org.appverse.web.showcases.gwtshowcase.backend.services.business;

import org.appverse.web.framework.backend.api.model.business.BusinessPaginatedDataFilter;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.Role;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface RoleService {

    @PreAuthorize("hasRole('ROLE_ROLE')")
	int countRoles(BusinessPaginatedDataFilter filter) throws Exception;

    @PreAuthorize("hasRole('ROLE_ROLE')")
	void deleteRole(long roleId) throws Exception;

    @PreAuthorize("hasRole('ROLE_ROLE')")
    Role loadRole(long pk) throws Exception;

    @PreAuthorize("hasRole('ROLE_ROLE')")
	List<Role> loadRoles() throws Exception;

    @PreAuthorize("hasRole('ROLE_ROLE')")
	List<Role> loadRoles(BusinessPaginatedDataFilter config) throws Exception;

    @PreAuthorize("hasRole('ROLE_ROLE')")
	long saveRole(Role role) throws Exception;
}