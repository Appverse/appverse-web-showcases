Appverse Web Showcases
======================

This project covers all our template and demonstration applications for healthy development. 

## Web Service Showcase
This is a example of building a simple Web Service using JAX-WS. 

### JAX-WS
*Java API for XML Web Services (JAX-WS)* is a technology for building web services and clients that communicate using *XML*.
JAX-WS allows developers to write message-oriented as well as *Remote Procedure Call-oriented (RPC-oriented)* web services.
In *JAX-WS*, a web service operation invocation is represented by an *XML-based protocol*, such as *SOAP*. 
The *SOAP* specification defines the envelope structure, encoding rules, and conventions for representing web service invocations and responses. 
These calls and responses are transmitted as *SOAP* messages (*XML* files) over *HTTP*. Although *SOAP* messages are complex, the *JAX-WS API* hides this complexity 
from the application developer. On the server side, the developer specifies the web service operations by defining methods in an interface written in the 
Java programming language. The developer also codes one or more classes that implement those methods. 
Client programs are also easy to code. A client creates a proxy (a local object representing the service) and then simply invokes methods on the proxy. 
With *JAX-WS*, the developer does not generate or parse SOAP messages. It is the JAX-WS runtime system that converts the API calls and responses to and from SOAP messages.
With *JAX-WS*, clients and web services have a big advantage: the platform independence of the Java programming language. 
In addition, *JAX-WS* is not restrictive: A *JAX-WS* client can access a web service that is not running on the Java platform, and vice versa. 
This flexibility is possible because *JAX-WS* uses technologies defined by the *W3C*: *HTTP*, *SOAP*, and *WSDL*. 
*WSDL* specifies an *XML* format for describing a service as a set of endpoints operating on messages.
 
### Service Description
The sample web service is an Account service. See the **model** package for more details. 
It has some methods to return mock Account objects like: 
`@WebMethod public Account getAccount()` 

### JAX-WS Deploy
With *JAX-WS 2.2.2 RI*, JAX-WS Runtime utilizes the *Servlet 3.0* capabilities to dynamically register a servlet for each web service endpoint specified in *sun-jaxws.xml*. 
This should work in any *Servlet 3.0* container like *GlassFish V3*, *Tomcat 7*, etc. This simplified deployment feature is introduced in *GlassFish 3.1 MS3* and you can find 
a working sample from *Metro 2.1* latest promoted build here. The dynamically registered *JAX-WS* servlet uses default settings in most cases and if you need to do advanced 
configuration like security, you can still specify in **web.xml** as you used to earlier.
Of course, with the use of *JAX-WS* annotations, even configuration *sun-jaxws.xml* can be made optional making it completely descriptor free, 
but that requires specifying a default url-pattern like in *JSR-109* or custom pattern like in *Jersey REST* services, in the *JAX-WS* specification. 
As it is, such descriptor free deployment is already supported by *JSR-109* implementation from *Glassfish V2*. 
This definitely is a step towards that goal and one less configuration file to worry about.  

The project uses the maven jetty plugin. 

`mvn clean jetty:run-war`

This will start Jetty running on port 8080 and serving the generated WAR file.

Port selection, specify another port for jetty:

`mvn clean jetty:run-war -Djetty.port=8090`

Service URL: <http://localhost:8090/appverse-web-showcases-ws-service/AccountService>
Generated WSDL: <http://localhost:8090/appverse-web-showcases-ws-service/AccountService?wsdl>


## More Information
* **SOAP**: <http://www.w3.org/TR/soap/>
* **JAX-WS**: <http://searchsoa.techtarget.com/definition/JAX-WS> 
* **Maven-Jetty**: <http://docs.codehaus.org/display/JETTY/Maven+Jetty+Plugin>
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
