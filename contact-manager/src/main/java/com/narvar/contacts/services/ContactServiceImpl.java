package com.narvar.contacts.services;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.narvar.contacts.models.Contact;
import com.narvar.contacts.models.ContactDao;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactDao contactRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Contact save(Contact contact) {
	    try {
	      contactRepository.save(contact);
	      logger.debug("Saved contact " + contact.getId());
	    } catch (Exception ex) {
	      logger.error("An error occurred " + ex.toString());
	      return null;
	    }
	    return contact;
	}

	@Override
	public Contact findOne(long id) {
		Contact contact = null;
		try {
	      contact = contactRepository.findOne(id);
	      logger.debug("Find one contact " + contact.getId());
	    } catch (Exception ex) {
	      logger.error("An error occurred " + ex.toString());	
	    }
	    return contact;
	}

	@Override
	public String delete(long id) {
		StringBuffer sb = new StringBuffer();
		try {
	      Contact contact = new Contact(id);
	      contactRepository.delete(contact);
	      logger.debug("Deleted contact: " + contact.getId());
	    } catch (Exception ex) {
	      logger.error("Error deleting the contact: " + ex.toString());
	      sb.append("Error deleting the contact: " + ex.toString());
	    }
	    sb.append("Contact successfully deleted!");
	    return sb.toString();
	}

	@Override
	public Contact update(long id, Contact updated) {
		Contact contactDB = null;
		try {
		  contactDB = contactRepository.findOne(id);
		  logger.debug("Contact before updated: " + contactDB);
		  //checking if the updated value needs to be updated or just
	      //update with the value that the database has stored previously
	      if (updated.getName() != null && !updated.getName().isEmpty())
	    	  contactDB.setName(updated.getName());
	      if (updated.getEmail() != null && !updated.getEmail().isEmpty())
	    	  contactDB.setEmail(updated.getEmail());
	      if (updated.getProfession() != null && !updated.getProfession().isEmpty()) 
	    	  contactDB.setProfession(updated.getProfession());
	      logger.debug("Contact after updated: " + contactDB);
	      contactRepository.save(contactDB);
	      logger.debug("Updated contact: " + contactDB.getId());
	    } catch (Exception ex) {
	      logger.error("Error updating the contact" + ex.toString());	
	    }
	    return contactDB;
	}

	@Override
	public List<Contact> findAll(int page, int size, boolean ascName) {
		PageRequest pageRequest = null;
		if (ascName) { 
			pageRequest = new PageRequest(page, size, new Sort(new Order(Direction.ASC,  "name")));
		} else { 
			pageRequest = new PageRequest(page, size, new Sort(new Order(Direction.DESC, "name")));			
		}
		Page<Contact> contacts = contactRepository.findAll(pageRequest);
		return contacts.getContent();
	}

	@Override
	public List<Contact> findByProfessionIgnoreCase(int page, int size, boolean ascName, String profession) {
		PageRequest pageRequest = null;
		if (ascName) { 
			pageRequest = new PageRequest(page, size, new Sort(new Order(Direction.ASC,  "name")));
		} else { 
			pageRequest = new PageRequest(page, size, new Sort(new Order(Direction.DESC, "name")));			
		}
		Page<Contact> contacts = contactRepository.findByProfessionIgnoreCase(pageRequest, profession);
		return contacts.getContent();
	}
	
}