package com.narvar.contacts.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.narvar.contacts.models.Contact;

import com.narvar.contacts.services.ContactBean;
import com.narvar.contacts.services.ContactService;

@RestController
@RequestMapping(value = "/contacts")
public class ContactRestController {

	@Autowired
  	private ContactService contactService;
	
  	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
  	@RequestMapping(value = "/", method=RequestMethod.POST, headers="Accept=application/json")
  	public Contact addContact(@RequestBody ContactBean contact) {
  		logger.debug("Adding a contact");
  		Contact dto = new Contact(contact.getName(), contact.getEmail(), contact.getProfession());
		return contactService.save(dto);
	}

  	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
  	public String deleteContact(@PathVariable long id) {
  		logger.debug("Deleting a contact");
  		return contactService.delete(id);
  	}

  	@RequestMapping(value="{id}", method=RequestMethod.PUT, headers="Accept=application/json")
  	public Contact update(@PathVariable long id, @RequestBody ContactBean contact) {
  		logger.debug("Updating a contact");
  		Contact dto = new Contact(contact.getName(), contact.getEmail(), contact.getProfession());
  		return contactService.update(id, dto); 
  	}
  	
  	@RequestMapping(value = "/list", method=RequestMethod.GET)
  	public List<Contact> listContacts(
  			@RequestParam(value="page", defaultValue = "0") int page, 
  			@RequestParam(value="size", defaultValue = "1000") int size, 
  			@RequestParam(value="ascName", defaultValue = "1") boolean asc) {
  		logger.debug("Listing a contact");
  		return contactService.findAll(page, size, asc);
	}

  	@RequestMapping(value = "/list-unemployee", method=RequestMethod.GET)
  	public List<Contact> searchEmployeeUnemployee(
  			@RequestParam(value="page", defaultValue = "0") int page, 
  			@RequestParam(value="size", defaultValue = "1000") int size, 
  			@RequestParam(value="ascName", defaultValue = "1") boolean asc, 
  			@RequestParam(value="profession", defaultValue = "unemployee") String profession) {
  		logger.debug("Listing a contact with unemployee");
  		return contactService.findByProfessionIgnoreCase(page, size, asc, profession);
	}

}