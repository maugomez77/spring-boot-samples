package com.narvar.contacts.services;

import java.util.List;

import com.narvar.contacts.models.Contact;

public interface ContactService {
	public Contact save(Contact contact);
	public Contact findOne(long id);
	public String  delete(long id);
	public Contact update(long id, Contact updated);
	public List<Contact> findAll(int page, int size, boolean ascName);
	public List<Contact> findByProfessionIgnoreCase(int page, int size, boolean ascName, String profession);
}
