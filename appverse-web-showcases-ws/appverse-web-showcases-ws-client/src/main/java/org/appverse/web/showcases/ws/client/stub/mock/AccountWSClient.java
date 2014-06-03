package org.appverse.web.showcases.ws.client.stub.mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.appverse.web.showcases.ws.client.Account;
import org.appverse.web.showcases.ws.client.AccountService;
import org.appverse.web.showcases.ws.client.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Proxy class for the service client
 * @author MOCR
 *
 */
public class AccountWSClient implements AccountService { 
	
	private static Logger logger = LoggerFactory.getLogger(AccountWSClient.class);
	
	private static Account [] accountsArray;
	
	private static final Map<String, String>  accounts = new HashMap<String, String>();
	
	public AccountWSClient () {
		for (int i=1; i<=5; i++ ) {
			accounts.put(String.valueOf(i), "account_" + i);		
			}
			accountsArray = new Account[5];
			for (int i=0; i < 5; i++ ) {
				 Customer cust = new Customer ();
				 cust.setAge(new Random().nextInt(10));
				 cust.setDesc("MOCK DESC_" + i); 
				 cust.setLocation("MOCK LOCATION_" + i);
				 cust.setName("MOCK CUSTOMER_" + i);
				 Account acc = new Account (); 
				 acc.setId(i);
				 acc.setBalance(new BigDecimal (new Random().nextInt(5000)));
				 acc.setCustomer(cust);
				accountsArray [i] = acc;
			}
		
	}
 
	public Account getAccount() {
		 Customer cust = new Customer ();
		 cust.setAge(38);
		 cust.setDesc("MOCK DESC"); 
		 cust.setLocation("MOCK CUSTOMER LOCATION");
		 cust.setName("MOCK CUSTOMER NAME");
		 Account acc = new Account (); 
		 acc.setId(12L);
		 acc.setBalance(new BigDecimal (112300));
		 acc.setCustomer(cust);
		 return acc; 
	}
	
	public String getAccountByClient(String arg0) {
		return accounts.get(arg0);
	}
	public List<Account> getAccountObjects() {
		return Arrays.asList(accountsArray);

	}

	public List<String> getAccounts() {
		return  Arrays.asList(accounts.values().toArray(new String[accounts.size()]));
	}
}
