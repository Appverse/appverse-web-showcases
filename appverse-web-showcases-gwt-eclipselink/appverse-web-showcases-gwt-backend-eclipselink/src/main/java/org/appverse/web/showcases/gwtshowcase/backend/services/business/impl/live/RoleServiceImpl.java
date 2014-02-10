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
package org.appverse.web.showcases.gwtshowcase.backend.services.business.impl.live;

import org.appverse.web.framework.backend.api.converters.ConversionType;
import org.appverse.web.framework.backend.api.converters.b2i.PaginatedDataFilterB2IBeanConverter;
import org.appverse.web.framework.backend.api.model.business.BusinessPaginatedDataFilter;
import org.appverse.web.framework.backend.api.model.integration.IntegrationPaginatedDataFilter;
import org.appverse.web.framework.backend.api.services.business.AbstractBusinessService;
import org.appverse.web.showcases.gwtshowcase.backend.converters.b2i.RoleB2IBeanConverter;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.Role;
import org.appverse.web.showcases.gwtshowcase.backend.model.integration.RoleDTO;
import org.appverse.web.showcases.gwtshowcase.backend.services.business.RoleService;
import org.appverse.web.showcases.gwtshowcase.backend.services.integration.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl extends AbstractBusinessService implements
        RoleService {

	@Autowired
	private RoleRepository roleRepository;
    @Autowired
	private RoleB2IBeanConverter roleB2IBeanConverter;
	@Autowired
	private PaginatedDataFilterB2IBeanConverter paginatedDataFilterB2IBeanConverter;

	@Override
	public int countRoles(final BusinessPaginatedDataFilter filter)
			throws Exception {
		final IntegrationPaginatedDataFilter integrationDataFilter = paginatedDataFilterB2IBeanConverter
				.convert(filter);
		return roleRepository.count(integrationDataFilter);
	}

	@Override
	public Role loadRole(final long pk) throws Exception {
		// Note that default conversion type (not specified) is
		// ConversionType.Complete. This will convert all collections
		// using the proper corresponding mapping
		final RoleDTO roleDTO = roleRepository.retrieve(pk);
		final Role role = roleB2IBeanConverter.convert(roleDTO);
		return role;
	}

	@Override
	public void deleteRole(final long roleId) throws Exception {
		final RoleDTO roleDTO = roleRepository.retrieve(roleId);
		roleRepository.delete(roleDTO);
	}

	@Override
	public List<Role> loadRoles() throws Exception {
		// Note that ConversitonType.WithoutDependencies will not convert
		// collections using the corresponding mapping
		final List<RoleDTO> roleList = roleRepository.retrieveList();
		return roleB2IBeanConverter.convertIntegrationList(roleList);
	}

	@Override
	public List<Role> loadRoles(final BusinessPaginatedDataFilter filter)
			throws Exception {
		// Note that ConversitonType.WithoutDependencies will not convert
		// collections using the corresponding mapping
		final IntegrationPaginatedDataFilter integrationDataFilter = paginatedDataFilterB2IBeanConverter
				.convert(filter);
		final List<RoleDTO> roleList = roleRepository
				.retrieveList(integrationDataFilter);
		return roleB2IBeanConverter.convertIntegrationList(roleList);
	}

	@Override
	public long saveRole(final Role role) throws Exception {

        // This call is just to demostrate the use of the native Hibernate API. Does not add any functionality it is here just as a example
        // showing a transaction that mixes JPA queries and native queries.
        // We recommend to use JPA as much as possible, avoiding your JPA provider (ORM) native API. Following the JPA specification will
        // make your application much more portable in case you want to change your JPA provider.
        final List<RoleDTO> roleList =  roleRepository.retrieveRoleListUsingNativeOrmApiExample();

		RoleDTO roleDTO;
		if (role.getId() != 0L) {
			// As it is an existing user we retrieve the entity manager managed
			// object
			roleDTO = roleRepository.retrieve(role.getId());
			roleB2IBeanConverter.convert(role, roleDTO,
					ConversionType.WithoutDependencies);
		} else {
			// We are creating a new DTO (not managed by the entity manager yet)
			roleDTO = roleB2IBeanConverter.convert(role);
		}
		return roleRepository.persist(roleDTO);
	}
}