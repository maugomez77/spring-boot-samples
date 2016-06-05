package com.narvar.contacts.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactDao extends PagingAndSortingRepository<Contact, Long> {

	Page<Contact> findAll(Pageable pageable);

	Page<Contact> findByProfessionIgnoreCase(Pageable pageable, String profession);
}
