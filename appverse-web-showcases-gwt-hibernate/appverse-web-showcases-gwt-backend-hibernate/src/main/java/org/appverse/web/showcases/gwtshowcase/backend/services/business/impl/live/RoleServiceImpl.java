package org.appverse.web.showcases.gwtshowcase.backend.services.business.impl.live;

import org.appverse.web.framework.backend.api.converters.ConversionType;
import org.appverse.web.framework.backend.api.converters.b2i.PaginatedDataFilterB2IBeanConverter;
import org.appverse.web.framework.backend.api.model.business.BusinessPaginatedDataFilter;
import org.appverse.web.framework.backend.api.model.integration.IntegrationPaginatedDataFilter;
import org.appverse.web.framework.backend.api.services.business.AbstractBusinessService;
import org.appverse.web.showcases.gwtshowcase.backend.converters.b2i.RoleB2IBeanConverter;
import org.appverse.web.showcases.gwtshowcase.backend.model.integration.RoleDTO;
import org.appverse.web.showcases.gwtshowcase.backend.services.business.RoleService;
import org.appverse.web.showcases.gwtshowcase.backend.services.integration.RoleRepository;
import org.appverse.web.showcases.gwtshowcase.backend.model.business.Role;
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
		/*
		 * final RoleDTO roleDTO = roleB2IBeanConverter.convert(role,
		 * ConversionType.WithoutDependencies);
		 */
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
		// We need to ensure the DTO object is complete before saving it as
		// presentation
		// object (and so business object) might be lacking of relationships or
		// just keep the ids

		// Retrieve and assign permission full objects
/*
		roleDTO.getPermissions().clear();
		for (final Permission permission : role.getPermissions()) {
			roleDTO.getPermissions().add(
					permissionRepository.retrieve(permission.getId()));
		}
*/

		// Retrieve and assign environments full objects
/*
		roleDTO.getEnvironments().clear();
		for (final Environment environment : role.getEnvironments()) {
			roleDTO.getEnvironments().add(
					environmentRepository.retrieve(environment.getId()));
		}
*/
		return roleRepository.persist(roleDTO);
	}
}