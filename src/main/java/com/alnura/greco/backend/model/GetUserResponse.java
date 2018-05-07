//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.05.07 a las 05:08:50 PM CEST 
//


package com.alnura.greco.backend.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="nickname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="mail" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="mydata" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="adds" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="communities" type="{http://alnura.es}community" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "nickname",
    "mail",
    "mydata",
    "adds",
    "communities"
})
@XmlRootElement(name = "getUserResponse")
public class GetUserResponse {

    protected int id;
    @XmlElement(required = true)
    protected String nickname;
    @XmlElement(required = true)
    protected String mail;
    @XmlElement(required = true)
    protected String mydata;
    protected boolean adds;
    protected List<Community> communities;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad nickname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Define el valor de la propiedad nickname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickname(String value) {
        this.nickname = value;
    }

    /**
     * Obtiene el valor de la propiedad mail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMail() {
        return mail;
    }

    /**
     * Define el valor de la propiedad mail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMail(String value) {
        this.mail = value;
    }

    /**
     * Obtiene el valor de la propiedad mydata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMydata() {
        return mydata;
    }

    /**
     * Define el valor de la propiedad mydata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMydata(String value) {
        this.mydata = value;
    }

    /**
     * Obtiene el valor de la propiedad adds.
     * 
     */
    public boolean isAdds() {
        return adds;
    }

    /**
     * Define el valor de la propiedad adds.
     * 
     */
    public void setAdds(boolean value) {
        this.adds = value;
    }

    /**
     * Gets the value of the communities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the communities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommunities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Community }
     * 
     * 
     */
    public List<Community> getCommunities() {
        if (communities == null) {
            communities = new ArrayList<Community>();
        }
        return this.communities;
    }

}
