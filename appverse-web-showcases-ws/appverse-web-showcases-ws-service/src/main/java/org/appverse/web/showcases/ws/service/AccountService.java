package org.appverse.web.showcases.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.appverse.web.showcases.ws.service.model.Account;
 
/**
 * Account Service Endpoint Interface
 * @author MOCR
 *
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface AccountService {
	/**
	 * Get Account by Client ID
	 * @param clientId
	 * @return account as string
	 */
	@WebMethod public String getAccountByClient(String clientId);
	/**
	 * Get array of accounts as strings
	 * @return String[] 
	 */
	@WebMethod public String [] getAccounts();
	/**
	 * Get Account 
	 * @return Account
	 */	
	@WebMethod public Account getAccount();
	/**
	 * Get a list of Accounts
	 * @return Account[]
	 */
	@WebMethod public Account [] getAccountObjects();
}
