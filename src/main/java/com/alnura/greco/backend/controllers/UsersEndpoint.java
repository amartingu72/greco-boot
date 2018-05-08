package com.alnura.greco.backend.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

import com.alnura.greco.backend.model.AuthenticationSOAPHeader;
import com.alnura.greco.backend.model.Community;
import com.alnura.greco.backend.model.CommunityDTO;
import com.alnura.greco.backend.model.GetUserRequest;
import com.alnura.greco.backend.model.GetUserResponse;
import com.alnura.greco.backend.model.UserDTO;
import com.alnura.greco.backend.service.UsersService;

@Endpoint
public class UsersEndpoint {
	
	private static final Logger logger = LoggerFactory.getLogger(UsersEndpoint.class);
	
	@Value("${security.enabled}")
	private boolean securityEnabled;
	
	@PostConstruct
	public void onload() {
		logger.debug("Me he iniciado");
	}
	
	private static final String NAMESPACE_URI = "http://alnura.es";
	
	@Autowired
	private UsersService usersService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUserRequest(@RequestPayload GetUserRequest request,
			@SoapHeader("{" + AuthenticationSOAPHeader.AUTH_NS + "}authentication") SoapHeaderElement auth) {
		
		if ( securityEnabled ) {
			AuthenticationSOAPHeader authentication = getAuthentication(auth);
			logger.info("Usuario {}, password {}", authentication.getUsername(), authentication.getPassword());
		}
		GetUserResponse response = new GetUserResponse();
		
		UserDTO userDTO=usersService.findById(request.getId());
		response.setAdds(userDTO.isAdds());
		response.setId(userDTO.getId());
		response.setMydata(userDTO.getMydata());
		response.setNickname(userDTO.getNickname());
		response.setMail(userDTO.getEmail());
	
		List<Community> communities= response.getCommunities();
		
		List<CommunityDTO> communitiesDTO =usersService.getCommunities(request.getId());
		if ( communitiesDTO != null ) {
			Community community=null;
			for (CommunityDTO communityDTO:communitiesDTO ) {
				community=new Community();
				community.setId(communityDTO.getId());
				community.setName(communityDTO.getName());
				community.setZipcode(communityDTO.getZipcode());
				communities.add(community);
			}
		}
		return response;
	}
	
	private AuthenticationSOAPHeader getAuthentication(SoapHeaderElement header){
		AuthenticationSOAPHeader authentication = null;
        try {

            JAXBContext context = JAXBContext.newInstance(AuthenticationSOAPHeader.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            authentication = (AuthenticationSOAPHeader) unmarshaller.unmarshal(header.getSource());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return authentication;
    }

}
