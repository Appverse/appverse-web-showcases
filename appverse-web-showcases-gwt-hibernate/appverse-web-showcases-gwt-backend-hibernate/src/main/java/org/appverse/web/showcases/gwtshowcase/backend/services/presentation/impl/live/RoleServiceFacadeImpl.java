package org.appverse.web.showcases.gwtshowcase.backend.services.presentation.impl.live;

import org.appverse.web.framework.backend.api.converters.p2b.PaginatedDataFilterP2BBeanConverter;
import org.appverse.web.framework.backend.api.model.business.BusinessPaginatedDataFilter;
import org.appverse.web.framework.backend.api.services.presentation.AbstractPresentationService;
import org.appverse.web.framework.backend.frontfacade.gxt.converters.p2b.GWTPaginatedDataFilterP2BBeanConverter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTItemVO;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
import org.appverse.web.showcases.gwtshowcase.backend.converters.helpers.utils.ConcatListToStringConverter;
import org.appverse.web.showcases.gwtshowcase.backend.converters.p2b.GWTItemRoleP2BBeanConverter;
import org.appverse.web.showcases.gwtshowcase.backend.converters.p2b.RoleP2BBeanConverter;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.Role;
import org.appverse.web.showcases.gwtshowcase.backend.model.presentation.RoleVO;
import org.appverse.web.showcases.gwtshowcase.backend.services.business.RoleService;
import org.appverse.web.showcases.gwtshowcase.backend.services.presentation.RoleServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleServiceFacade")
public class RoleServiceFacadeImpl extends AbstractPresentationService
		implements RoleServiceFacade {

	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleP2BBeanConverter roleP2BBeanConverter;
	@Autowired
	private GWTPaginatedDataFilterP2BBeanConverter gwtPaginatedDataFilterP2BBeanConverter;

	@Autowired
	private PaginatedDataFilterP2BBeanConverter paginatedDataFilterP2BBeanConverter;
	@Autowired
	private GWTItemRoleP2BBeanConverter itemRoleP2BBeanConverter;

	public static final int NUM_ENVS_LIST = 3;
	public static final int NUM_PERMS_LIST = 3;

	public RoleServiceFacadeImpl() {
	}

	@Override
	public void deleteRole(final long roleId) throws Exception {
		roleService.deleteRole(roleId);
	}

	@Override
	public RoleVO loadRole(final long roleId) throws Exception {
		final Role role = roleService.loadRole(roleId);
		return roleP2BBeanConverter.convert(role);
	}

	@Override
	public List<RoleVO> loadRoles() throws Exception {
		final List<Role> roles = roleService.loadRoles();

		final List<RoleVO> rolesVO = roleP2BBeanConverter
				.convertBusinessList(roles);

		// Map environment list and permissions list to string fields
/*
		for (final RoleVO role : rolesVO) {
			final String envsString = ConcatListToStringConverter
					.concatFieldFromList(role.getEnvironments(), "name");
			final String permsString = ConcatListToStringConverter
					.concatFieldFromList(role.getPermissions(), "name");
			role.setListEnvironments(envsString);
			role.setListPermissions(permsString);
		}
*/
		return rolesVO;
	}

	@Override
	public GWTPresentationPaginatedResult<RoleVO> loadRoles(
			final GWTPresentationPaginatedDataFilter config) throws Exception {

		final BusinessPaginatedDataFilter businessDatafilter = paginatedDataFilterP2BBeanConverter
				.convert(config);

		// Get the total number of rows first
		final int total = roleService.countRoles(businessDatafilter);

		// Get the rows
		final List<Role> roles = roleService.loadRoles(businessDatafilter);

		final List<RoleVO> rolesVO = roleP2BBeanConverter
				.convertBusinessList(roles);

		// Map environment list and permissions list to string fields
/*
		for (final RoleVO role : rolesVO) {
			final String envsString = ConcatListToStringConverter
					.concatFieldFromList(role.getEnvironments(), "name");
			final String permsString = ConcatListToStringConverter
					.concatFieldFromList(role.getPermissions(), "name");
			role.setListEnvironments(envsString);
			role.setListPermissions(permsString);
		}
*/

		return new GWTPresentationPaginatedResult<RoleVO>(rolesVO, total,
				config.getOffset());
	}

	@Override
	public List<GWTItemVO> loadRolesMap() throws Exception {
		final List<Role> roles = roleService.loadRoles();
		return itemRoleP2BBeanConverter.convertBusinessList(roles);
	}

	@Override
	public long saveRole(final RoleVO roleVO) throws Exception {
		final Role role = roleP2BBeanConverter.convert(roleVO);
		return roleService.saveRole(role);
	}

}
