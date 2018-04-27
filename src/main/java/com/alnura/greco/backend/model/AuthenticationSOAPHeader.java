package com.alnura.greco.backend.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
   <soapenv:Header>
      <auth:authentication xmlns:auth="http://alnura.es/security">
         <auth:username>username</auth:username>
         <auth:password>password</auth:password>
      </auth:authentication>
   </soapenv:Header>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="authentication", namespace = AuthenticationSOAPHeader.AUTH_NS)
public class AuthenticationSOAPHeader {
	
	public static final String AUTH_NS = "http://alnura.es/security";
	@XmlElement(namespace = AUTH_NS)
    private String username;
    @XmlElement(namespace = AUTH_NS)
    private String password;
 
}
