package org.appverse.web.showcases.gwtshowcase.backend.services.integration;

import org.appverse.web.framework.backend.persistence.services.integration.IJPAPersistenceService;
import org.appverse.web.showcases.gwtshowcase.backend.model.integration.RoleDTO;

import java.util.List;

public interface RoleRepository extends IJPAPersistenceService<RoleDTO> {

    List<RoleDTO> retrieveRoleListUsingNativeOrmApiExample() throws Exception;

}
