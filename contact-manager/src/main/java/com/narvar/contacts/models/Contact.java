package com.narvar.contacts.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6374739968787113145L;

	@Id
  	@GeneratedValue(strategy = GenerationType.AUTO)
  	private long id;
	
  	@NotNull
  	private String name;

  	@NotNull
  	private String email;

  	@NotNull
  	private String profession;
  	
  	public Contact(long id) {
  		this.id = id;
  	}
  	
	public Contact() {
		super();
	}

	public Contact(String name, String email, String profession) {
		super();
		this.name = name;
		this.email = email;
		this.profession = profession;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getProfession() {
		return profession;
	}
	
	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", profession=" + profession + "]";
	}  
}

