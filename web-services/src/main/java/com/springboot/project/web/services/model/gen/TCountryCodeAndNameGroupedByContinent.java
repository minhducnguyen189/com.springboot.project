//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.03.28 at 07:39:44 PM ICT 
//


package com.springboot.project.web.services.model.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tCountryCodeAndNameGroupedByContinent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tCountryCodeAndNameGroupedByContinent"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Continent" type="{http://www.oorsprong.org/websamples.countryinfo}tContinent"/&gt;
 *         &lt;element name="CountryCodeAndNames" type="{http://www.oorsprong.org/websamples.countryinfo}ArrayOftCountryCodeAndName"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tCountryCodeAndNameGroupedByContinent", propOrder = {
    "continent",
    "countryCodeAndNames"
})
public class TCountryCodeAndNameGroupedByContinent {

    @XmlElement(name = "Continent", required = true)
    protected TContinent continent;
    @XmlElement(name = "CountryCodeAndNames", required = true)
    protected ArrayOftCountryCodeAndName countryCodeAndNames;

    /**
     * Gets the value of the continent property.
     * 
     * @return
     *     possible object is
     *     {@link TContinent }
     *     
     */
    public TContinent getContinent() {
        return continent;
    }

    /**
     * Sets the value of the continent property.
     * 
     * @param value
     *     allowed object is
     *     {@link TContinent }
     *     
     */
    public void setContinent(TContinent value) {
        this.continent = value;
    }

    /**
     * Gets the value of the countryCodeAndNames property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOftCountryCodeAndName }
     *     
     */
    public ArrayOftCountryCodeAndName getCountryCodeAndNames() {
        return countryCodeAndNames;
    }

    /**
     * Sets the value of the countryCodeAndNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOftCountryCodeAndName }
     *     
     */
    public void setCountryCodeAndNames(ArrayOftCountryCodeAndName value) {
        this.countryCodeAndNames = value;
    }

}
