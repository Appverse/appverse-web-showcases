package org.appverse.web.showcases.ws.client.test;

import static org.junit.Assert.*;

import org.appverse.web.showcases.ws.client.stub.mock.AccountWSClient;
import org.junit.Test;

public class ClientTest {
	
	@Test
	public void testServiceClient () {
		AccountWSClient mockclient = new AccountWSClient();		
		for (String a: mockclient.getAccounts()) {
			System.out.println ("MOCK ACCOUNT: " + a);
		}
		assertEquals ( mockclient.getAccounts().size(), 5);
	}

}
