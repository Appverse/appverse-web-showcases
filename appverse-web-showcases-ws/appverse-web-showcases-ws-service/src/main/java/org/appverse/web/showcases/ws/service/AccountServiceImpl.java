package org.appverse.web.showcases.ws.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.appverse.web.showcases.ws.service.model.Account;
import org.appverse.web.showcases.ws.service.model.Customer;
 

@WebService(endpointInterface = "org.appverse.web.showcases.ws.service.AccountService")
public class AccountServiceImpl implements AccountService {
	
	private static final int test_size = 10;
	private static final Map<String, String>  accounts = new HashMap<String, String>();
	private static final Account [] accountsArray;
	static {
		for (int i=1; i<=test_size; i++ ) {
		accounts.put(String.valueOf(i), "account_" + i);		
		}
		accountsArray = new Account[test_size];
		for (int i=0; i < test_size; i++ ) {
			 Customer cust = new Customer ();
			 cust.setAge(new Random().nextInt(10));
			 cust.setDesc("TEST NESTED OBJECTS_" + i); 
			 cust.setLocation("HERE_" + i);
			 cust.setName("CUSTOMER_" + i);
			 Account acc = new Account (); 
			 acc.setId(i);
			 acc.setBalance(new BigDecimal (new Random().nextInt(5000)));
			 acc.setCustomer(cust);
			accountsArray [i] = acc;
		}
	}
 	
 
	public String getAccountByClient(String clientId) {
		return accounts.get(clientId);
	}

	public String[] getAccounts() {
		return accounts.values().toArray(new String[accounts.size()]);
	}

	@WebMethod
	public Account getAccount() {
		 Customer cust = new Customer ();
		 cust.setAge(25);
		 cust.setDesc("TEST NESTED OBJECTS"); 
		 cust.setLocation("TEST CUSTOMER LOCATION");
		 cust.setName("TEST CUSTOMER NAME");
		 Account acc = new Account (); 
		 acc.setId(1L);
		 acc.setBalance(new BigDecimal (100000));
		 acc.setCustomer(cust);
		 return acc;
	}

	@WebMethod
	public Account[] getAccountObjects() {
		return accountsArray;
	}
	
	public static void main(String[] args) {
		try {
			Endpoint.publish("http://localhost:8090/appverse-web-showcases-ws-service/AccountService", new  AccountServiceImpl());
	        System.out.println("Published service at http://localhost:8090/appverse-web-showcases-ws-service/AccountService");  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	 

}
