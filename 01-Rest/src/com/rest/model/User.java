package com.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

// by default is property
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName(value="uSeR")
@XmlRootElement(name = "user")
public class User {	
	@JsonProperty("Name")
	@XmlElement(name="NaMe")
	String name;
	@XmlElement(name="zip")
	@JsonProperty("ZiP")
	int zip; 
	@XmlElement(name="uri")
	@JsonProperty("uRI")
	String uri;	
	
	public String getName() {return name;} 
	public void setName(String name) {this.name = name;}	
	public int getZip() {return zip;}
	public void setZip(int zip) {this.zip = zip;}
	public String getUri() {return uri;}
	public void setUri(String uri) {this.uri = uri;}	
}
