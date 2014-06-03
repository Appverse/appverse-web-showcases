package org.appverse.web.showcases.ws.client.stub.live;

import java.util.List;

import org.appverse.web.framework.backend.ws.client.AbstractWSClient;
import org.appverse.web.framework.backend.ws.handlers.ClientPerformanceMonitorLogicalHandler;
import org.appverse.web.framework.backend.ws.handlers.EnvelopeLoggingSOAPHandler;
import org.appverse.web.showcases.ws.client.AccountService;
import org.appverse.web.showcases.ws.client.AccountServiceImplService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Proxy class for the service client
 * @author MOCR
 *
 */
public class AccountWSClient extends AbstractWSClient<AccountServiceImplService, AccountService>{ 
	
	private static Logger logger = LoggerFactory.getLogger(AccountWSClient.class);
	
	public AccountWSClient () {
		this.setBeanClasses(AccountServiceImplService.class, AccountService.class);
		this.registerHandler(new ClientPerformanceMonitorLogicalHandler());
		this.registerHandler(new EnvelopeLoggingSOAPHandler()); 
	}
	/**
	 * Proxy method. 
	 * @return A list of accounts.
	 * @throws Exception
	 */
	public List<String> getAccounts() throws Exception {
		return getService().getAccounts();
	}
 
	@Override
	public String getRemoteWSDLURL() { 
		return "http://localhost:8090/appverse-web-showcases-ws-service/AccountService?wsdl";
	}
}
