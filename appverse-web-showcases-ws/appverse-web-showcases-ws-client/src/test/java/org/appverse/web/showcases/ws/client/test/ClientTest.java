package org.appverse.web.showcases.ws.client.test;

import static org.junit.Assert.*;

import org.appverse.web.showcases.ws.client.stub.mock.AccountWSClient;
import org.junit.Test;
/**
 * Simple test case using the mock implementation
 * @author MOCR
 *
 */
public class ClientTest {
	/**
	 * Test get accounts as strings 
	 */
	@Test
	public void testServiceClient () {
		AccountWSClient mockclient = new AccountWSClient();		
		for (String a: mockclient.getAccounts()) {
			System.out.println ("MOCK ACCOUNT: " + a);
		}
		assertEquals ( mockclient.getAccounts().size(), 5);
	}

}
