//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.02.09 at 11:58:46 AM IST 
//


package com.skill.profile.api.jaxb.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="satusText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element ref="{http://skill-tracker.com/skill-profile-api-common}messages" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseType", namespace = "http://skill-tracker.com/skill-profile-api-common", propOrder = {
    "status",
    "satusText",
    "messages"
})
@XmlRootElement(name = "response", namespace = "http://skill-tracker.com/skill-profile-api-common")
public class Response {

    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected String satusText;
    protected List<Messages> messages;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the satusText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSatusText() {
        return satusText;
    }

    /**
     * Sets the value of the satusText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSatusText(String value) {
        this.satusText = value;
    }

    /**
     * Gets the value of the messages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Messages }
     * 
     * 
     */
    public List<Messages> getMessages() {
        if (messages == null) {
            messages = new ArrayList<Messages>();
        }
        return this.messages;
    }

}
