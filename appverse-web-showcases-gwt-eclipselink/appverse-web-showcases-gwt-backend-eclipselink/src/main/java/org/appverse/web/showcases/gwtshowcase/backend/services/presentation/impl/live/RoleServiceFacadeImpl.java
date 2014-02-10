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
package org.appverse.web.showcases.gwtshowcase.backend.services.presentation.impl.live;

import org.appverse.web.framework.backend.api.converters.p2b.PaginatedDataFilterP2BBeanConverter;
import org.appverse.web.framework.backend.api.model.business.BusinessPaginatedDataFilter;
import org.appverse.web.framework.backend.api.services.presentation.AbstractPresentationService;
import org.appverse.web.framework.backend.frontfacade.gxt.converters.p2b.GWTPaginatedDataFilterP2BBeanConverter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTItemVO;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedDataFilter;
import org.appverse.web.framework.backend.frontfacade.gxt.model.presentation.GWTPresentationPaginatedResult;
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
