package org.appverse.web.showcases.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.appverse.web.showcases.ws.service.model.Account;
 
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface AccountService {
	@WebMethod public String getAccountByClient(String clientId);
	@WebMethod public String [] getAccounts(); 
	@WebMethod public Account getAccount();
	@WebMethod public Account [] getAccountObjects();
}
