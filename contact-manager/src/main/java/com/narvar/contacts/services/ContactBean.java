package com.narvar.contacts.services;

public class ContactBean {

  	private long id;
	
  	private String name;

  	private String email;

  	private String profession;

  	public ContactBean() { 
  		super();
  	}
  	
	public ContactBean(long id, String name, String email, String profession) {
		super();
		this.id = id;
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
}
