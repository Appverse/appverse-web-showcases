package org.appverse.web.showcases.gwtshowcase.backend.services.integration.impl.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.appverse.web.framework.backend.api.helpers.test.AbstractTransactionalTest;
import org.appverse.web.framework.backend.api.helpers.test.JPATest;
import org.appverse.web.framework.backend.api.model.integration.AbstractIntegrationAuditedBean;
import org.appverse.web.framework.backend.api.model.integration.IntegrationDataFilter;
import org.appverse.web.showcases.gwtshowcase.backend.model.integration.RoleDTO;
import org.appverse.web.showcases.gwtshowcase.backend.model.integration.UserDTO;
import org.appverse.web.showcases.gwtshowcase.backend.services.integration.RoleRepository;
import org.appverse.web.showcases.gwtshowcase.backend.services.integration.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryImplTest extends AbstractTransactionalTest implements
		JPATest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	private final UserDTO userDTO = new UserDTO();

	private void checkUser(final UserDTO user) {
		Assert.assertNotNull("Entity can't be null", user);

		// Check collections here

        // Roles collection
		Assert.assertNotNull("Roles collection can't be null", user.getRoles());
		Assert.assertTrue("Roles collection size must be 4", user.getRoles().size() == 4);
	}

	private void checkUserListIsNotEmpty(final List<UserDTO> list) {
		Assert.assertNotNull("Entity list has not been able to be retrieved",
				list);
		Assert.assertTrue("Entity list is empty", list.size() > 0);

        // Checking that list objects are correct
        for (int i=0; i < list.size(); i++){
            checkUser(list.get(0));
        }
	}

    private void checkUserListIsEmpty(final List<UserDTO> list) {
        Assert.assertNotNull("Entity list has not been able to be retrieved",
                list);
        Assert.assertTrue("Entity list is empty", list.size() == 0);
    }


	@Override
	@Test
	public void delete() throws Exception {
        // We check the user we want to remove exists
        UserDTO userToRemove = userRepository.retrieve(1L);
        checkUser(userToRemove);

        // We remove the user
		userRepository.delete(userToRemove);

        // We check it has been effectively removed
		final UserDTO result = userRepository.retrieve(userDTO.getId());
		Assert.assertNull("Deleted " + userDTO.toString()
				+ " has been retrieved. This is not correct. It should have been removed.", result);
	}

	@Override
	@Before
	public void initialize() throws Exception {
		// Init first level fields
		userDTO.setEmail("user.test@email.com");
		userDTO.setCreatedBy("Test");
		userDTO.setCreated(new Date());
		userDTO.setStatus(AbstractIntegrationAuditedBean.STATUS_ACTIVE);
		userDTO.setLastName("lastname");
		userDTO.setName("Name");
		userDTO.setPassword("");

		// Init dependencies (already in test in memory database)

        // We add the 4 regular roles (as we have 5 we remove the extra that will be used later to test
        // roles additions)
        //List<RoleDTO> roleCollection = roleRepository.retrieveList();
		userDTO.setRoles(roleRepository.retrieveList().subList(0, 4));
	}

	@Override
	@Test
	public void persist() throws Exception {
		// Test new insert
		long resultId = userRepository.persist(userDTO);
		UserDTO result = userRepository.retrieve(resultId);
		checkUser(result);
	}

    // @Override
    @Test
    public void update() throws Exception {
        // Test update
        UserDTO result = result = userRepository.retrieve(1L);
        result.setName("newName");

        userRepository.persist(result);

        // With Hibernate, after calling persist method we need to retrieve the object again as the persist method
        // detaches the object so the optimistic locking version is updated properly. This causes you need to
        // retrieve a reference to the object that is attached to the session manager. Otherwise, you would have
        // problems with collections (for instance)
        result = userRepository.retrieve(result.getId());

        checkUser(result);

        Assert.assertEquals("The returned " + result.toString()
                + " name has to be the same", "newName", result.getName());
    }


	@Override
	@Test
	public void retrieveAll() throws Exception {
		final List<UserDTO> result = userRepository.retrieveList();
		checkUserListIsNotEmpty(result);
	}

	@Override
	@Test
	public void retrieveByBean() throws Exception {
		persist();
		final UserDTO result = userRepository.retrieve(userDTO);
		checkUser(result);
	}

	@Override
	@Test
	public void retrieveByFilter() throws Exception {
		// Test that filter is providing results
		IntegrationDataFilter filter = new IntegrationDataFilter();
		filter.addStrictCondition("version", 1L);
		List<UserDTO> result = userRepository.retrieveList(filter);
		checkUserListIsNotEmpty(result);

		// Test that filter is not providing result when no rows fulfill the condition
		filter = new IntegrationDataFilter();
		filter.addStrictCondition("version", -1L);
		result = userRepository.retrieveList(filter);
		Assert.assertNotNull(userDTO.toString()
				+ " filtered list hasn't been able to be retieved", result);
		Assert.assertTrue(userDTO.toString() + " filtered list is not empty",
				result.size() == 0);
	}

	@Override
	@Test
	public void retrieveByPk() throws Exception {
		final UserDTO result = userRepository.retrieve(1L);
		checkUser(result);
	}

    @Override
    @Test
    public void deleteAll() throws Exception {
        // We check initial retrieved objects list
        checkUserListIsNotEmpty(userRepository.retrieveList());

        userRepository.deleteAll();

        // We check that all the objects have been removed
        checkUserListIsEmpty(userRepository.retrieveList());
    }

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public void finalize() throws Exception{

    }
}