package com.alnura.greco.backend.controller;

import java.io.IOException;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;
import javax.xml.transform.Source;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.RequestCreators.withSoapEnvelope;
import static org.springframework.ws.test.server.ResponseMatchers.*;

import com.alnura.greco.backend.app.Application;

import com.alnura.greco.backend.entities.User;

@RunWith(SpringRunner.class)
@SpringBootTest(
		  webEnvironment = WebEnvironment.RANDOM_PORT,
		  classes = Application.class)
		//@AutoConfigureMockMvc
		@ActiveProfiles("test")
		@AutoConfigureTestEntityManager
public class UsersEndpointTest {
	@Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;
    private Resource xsdSchema = new ClassPathResource("users.xsd");
    
	@Autowired
    private TestEntityManager entityManager;  //Util para los tests

	
    @Before
    public void init(){
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    @Transactional
    public void valid_xsd_request_response_test() throws IOException {
    	// given
    	User user=new User();
    	user.setId(1);
    	user.setEmail("alberto.martin@alnura.es");
    	user.setMydata("Mis dato");
    	user.setAdds((byte)1 );
    	user.setNickname("alberto");
    	entityManager.persist(user);
    	entityManager.flush();	
    	
    	
        Source requestPayload = new StringSource(
        		" <aln:getUserRequest  xmlns:aln=\"http://alnura.es\">"+
                "         <aln:id>1</aln:id>"+
                "      </aln:getUserRequest>");
     
        Source responsePayload = new StringSource(
        		"   <ns2:getUserResponse xmlns:ns2=\"http://alnura.es\">"+
        		"      <ns2:id>1</ns2:id>"+
        		"      <ns2:mail>alberto.martin@alnura.es</ns2:mail>"+
        		"      <ns2:mydata>Mis dato</ns2:adds>"+
        		"      <ns2:adds>true</ns2:adds>"+
        		"      <ns2:communities/>"+
        		"      </ns2:getUserResponse>");
  
        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(noFault())
                .andExpect(payload(responsePayload))
                .andExpect(validPayload(xsdSchema));
    }

    @Test
    public void id_cannot_be_0_test() throws IOException {
        Source requestPayload = new StringSource(        	
                		"      <aln:getUserRequest  xmlns:aln=\"http://alnura.es\">"+
                		"         <aln:id>0</aln:id>"+
                		"      </aln:getUserRequest>");

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(serverOrReceiverFault());
    }

	
}
