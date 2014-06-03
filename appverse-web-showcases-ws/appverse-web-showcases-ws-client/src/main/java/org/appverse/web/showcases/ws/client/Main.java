package org.appverse.web.showcases.ws.client;

import org.apache.log4j.Logger;
import org.appverse.web.showcases.ws.client.stub.live.AccountWSClient; 

public class Main { 

	public static void main(String[] args) throws Exception { 
		  Logger logger = Logger.getLogger(Main.class);
		
		AccountWSClient client = new AccountWSClient();		
		for (String a: client.getAccounts()) {
			logger.debug ("REMOTE ACCOUNT: " + a);
		}
		logger.debug ("--------------------------------");
		org.appverse.web.showcases.ws.client.stub.mock.AccountWSClient mockclient 
			= new org.appverse.web.showcases.ws.client.stub.mock.AccountWSClient();		
		for (String a: mockclient.getAccounts()) {
			logger.debug ("MOCK ACCOUNT: " + a);
		}
			
	}

}
