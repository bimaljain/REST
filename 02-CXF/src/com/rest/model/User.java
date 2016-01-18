package com.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.stereotype.Component;

@Component
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
@JsonRootName(value="user")
public class User {	
	@XmlElement(name="nAmE")
	@JsonProperty("NaMe")
	String name;
	@XmlElement(name="zip")
	int zip; 
	@XmlElement(name="uri")
	String uri;	
	
	public String getName() {return name;} 
	public void setName(String name) {this.name = name;}	
	public int getZip() {return zip;}
	public void setZip(int zip) {this.zip = zip;}
	public String getUri() {return uri;}
	public void setUri(String uri) {this.uri = uri;}	
}
