Appverse Web Showcases
======================

This project covers all our template and demonstration applications for healthy development. 

## Web Service client Showcase
This is a example of building a simple Web Service client using *JAX-WS*. 
The project is a sample client of the Account Web Service showcase.

##WSIMPORT
### JAX-WS Maven plug-in
The client uses the jaxws-maven-plugin to generate the client classes. 

    <groupId>org.jvnet.jax-ws-commons</groupId>
    <artifactId>jaxws-maven-plugin</artifactId>
    <version>2.3</version>

#### Configuration
* Bindings: `<bindingDirectory>${basedir}/src/jaxws</bindingDirectory>`
* Target path: `<sourceDestDir>${project.build.directory}/generated-sources/jaxws-wsimport</sourceDestDir>`
* Target package: `<packageName>org.appverse.web.showcases.ws.client</packageName>`
* WSDL: `<wsdlUrl>http://localhost:8090/appverse-web-showcases-ws-service/AccountService?wsdl</wsdlUrl>`

#### Bindings
The bindings.xml file is used to configure the wsimport execution.
In this case, all the generated classes will extend Appverse AbstractIntegrationBean superclass.

    <jaxb:globalBindings>
    <!-- All beans should extend this base class: -->
    <xjc:superClass name="org.appverse.web.framework.backend.api.model.integration.AbstractIntegrationBean" />
    </jaxb:globalBindings>

## Stub
Using the service interface is possible to have different Service stubs. 
### Live
The live class will act as proxy class for the service client.
#### Using the AbstractWSClient
The Appverse WS module provides a class to extend that will perform all the necessary code to connect to the web service. 

    public class AccountWSClient extends AbstractWSClient<AccountServiceImplService, AccountService>

The abstract class will provide a `getService()` method that will connect to the remote service. 
This method performs the getPort call on the first method call, then it will catch the service instance on the client side on the subsequent calls. 
That means the client code can decide to call the `getService()` method on the `@PostConstruct` event or on the first request.  

    /**
    * Proxy method.
    * @return A list of accounts.
    * @throws Exception
    */
    public List<String> getAccounts() throws Exception {
    return getService().getAccounts();
    }

####Mock
It will implement the service interface providing a mock implementation of the service. 
That will allow to break the dependency with the remote service and perform some test of the client business logic. 

### Handlers
The framework provides two SOAP handlers.
* ClientPerformanceMonitorLogicalHandler: Logs the time spent by the request.
* EnvelopeLoggingSOAPHandler: Logs the inbound / outbound SOAP messages.

Register the framework or custom Handler implementations to use, with the registerHandler method. 

    public AccountWSClient () {
    this.setBeanClasses(AccountServiceImplService.class, AccountService.class);
    this.registerHandler(new ClientPerformanceMonitorLogicalHandler());
    this.registerHandler(new EnvelopeLoggingSOAPHandler()); 
    }
    
## More Information
* **SOAP**: <http://www.w3.org/TR/soap/>
* **JAX-WS**: <http://searchsoa.techtarget.com/definition/JAX-WS> 
* **Maven Plug-in**: <https://jax-ws-commons.java.net/jaxws-maven-plugin/>
* **About this project**: <http://appverse.github.com/appverse-web>
* **About licenses**: <http://www.mozilla.org/MPL/>
* **About The Appverse Project**: <http://appverse.org>

## License

    Copyright (c) 2012 GFT Appverse, S.L., Sociedad Unipersonal.

     This Source Code Form is subject to the terms of the Mozilla Public
     License, v. 2.0. If a copy of the MPL was not distributed with this
     file, You can obtain one at http://mozilla.org/MPL/2.0/.

     Redistribution and use in  source and binary forms, with or without modification, 
     are permitted provided that the  conditions  of the  Mozilla Public License v2.0 
     are met.

     THIS SOFTWARE IS PROVIDED BY THE  COPYRIGHT HOLDERS  AND CONTRIBUTORS "AS IS" AND
     ANY EXPRESS  OR IMPLIED WARRANTIES, INCLUDING, BUT  NOT LIMITED TO,   THE IMPLIED
     WARRANTIES   OF  MERCHANTABILITY   AND   FITNESS   FOR A PARTICULAR  PURPOSE  ARE
     DISCLAIMED. EXCEPT IN CASE OF WILLFUL MISCONDUCT OR GROSS NEGLIGENCE, IN NO EVENT
     SHALL THE  COPYRIGHT OWNER  OR  CONTRIBUTORS  BE LIABLE FOR ANY DIRECT, INDIRECT,
     INCIDENTAL,  SPECIAL,   EXEMPLARY,  OR CONSEQUENTIAL DAMAGES  (INCLUDING, BUT NOT
     LIMITED TO,  PROCUREMENT OF SUBSTITUTE  GOODS OR SERVICES;  LOSS OF USE, DATA, OR
     PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
     WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE) 
     ARISING  IN  ANY WAY OUT  OF THE USE  OF THIS  SOFTWARE,  EVEN  IF ADVISED OF THE 
     POSSIBILITY OF SUCH DAMAGE.
