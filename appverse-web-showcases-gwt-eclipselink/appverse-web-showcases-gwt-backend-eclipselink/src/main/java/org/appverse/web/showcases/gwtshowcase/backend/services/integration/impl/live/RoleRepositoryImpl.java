package org.appverse.web.showcases.gwtshowcase.backend.services.integration.impl.live;

import org.appverse.web.framework.backend.api.helpers.log.AutowiredLogger;
import org.appverse.web.framework.backend.persistence.services.integration.impl.live.JPAPersistenceService;
import org.appverse.web.showcases.gwtshowcase.backend.model.integration.RoleDTO;
import org.appverse.web.showcases.gwtshowcase.backend.services.integration.RoleRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleRepository")
public class RoleRepositoryImpl extends JPAPersistenceService<RoleDTO>
		implements RoleRepository {

	@AutowiredLogger
	private static Logger logger;

    @Override
    public List<RoleDTO> retrieveRoleListUsingNativeOrmApiExample() throws Exception{
/* TODO: with eclipselink
        Session session = unwrap(org.hibernate.Session.class);

        Criteria criteria = session.createCriteria(RoleDTO.class);
        return criteria.list();
*/
        return null;
    }
}
