package com.kalyan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kalyan.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
