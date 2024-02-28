package com.kalyan.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kalyan.entity.Contact;
import com.kalyan.exception.NoContactFoundException;
import com.kalyan.service.ContactService;

@SpringBootTest
public class ContactControllerTest {

	@InjectMocks
	private ContactController contactController;

	@Mock
	private ContactService contactService;

	@Test
	public void saveRecordTest() {
		Contact contact = new Contact(12345, "Thalari Kalyan", "thalarikalyaninfo@gmail.com", 9985285282L, "success");
		when(contactService.insertContactetails(contact)).thenReturn(true);
		ResponseEntity<String> saveRecord = contactController.saveRecord(contact);
		assertEquals(HttpStatus.CREATED, saveRecord.getStatusCode());
	}

	@Test
	public void saveRecordFailureTest() {
		Contact contact = new Contact();
		when(contactService.insertContactetails(contact)).thenReturn(false);
		ResponseEntity<String> saveRecord = contactController.saveRecord(contact);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, saveRecord.getStatusCode());
	}

	@Test
	public void getRecordsSuccessTest() {
		Contact contact = new Contact(12345, "Thalari Kalyan", "thalarikalyaninfo@gmail.com", 9985285282L, "success");
		List<Contact> listOfContacts = Arrays.asList(contact);
		when(contactService.getContactDetails()).thenReturn(listOfContacts);
		ResponseEntity<List<Contact>> allDetails = contactController.getAllDetails();
		assertEquals(HttpStatus.OK, allDetails.getStatusCode());
	}

	@Test
	public void getRecordsFailureTest() {
		when(contactService.getContactDetails()).thenThrow(new NoContactFoundException());
		assertThrows(NoContactFoundException.class, () -> contactController.getAllDetails());
	}

	@Test
	public void getContactDetailsByIdTest() {
		Contact contact = new Contact(12345, "Thalari Kalyan", "thalarikalyaninfo@gmail.com", 9985285282L, "success");
		when(contactService.getContactDetailsById(contact.getCid())).thenReturn(contact);
		ResponseEntity<Contact> getcontactDetailsById = contactController.getContactDetailsById(contact.getCid());
		assertEquals(HttpStatus.OK, getcontactDetailsById.getStatusCode());
	}

	@Test
	public void deleteContactDetailsByIdTest() {
		Contact contact = new Contact(12345, "Thalari Kalyan", "thalarikalyaninfo@gmail.com", 9985285282L, "success");
		when(contactService.deleteContactDetailsById(contact.getCid())).thenReturn(Mockito.anyString());
		ResponseEntity<String> deleteContactDetailsById = contactController.deleteContactDetailsById(contact.getCid());
		assertEquals(HttpStatus.OK, deleteContactDetailsById.getStatusCode());
	}

	@Test
	public void updateContactDetailsTest() {
		Contact contact = new Contact(12345, "Thalari Kalyan", "thalarikalyaninfo@gmail.com", 9985285282L, "success");
		when(contactService.updateContactDetailsById(contact)).thenReturn(Mockito.anyString());
		ResponseEntity<String> updateContactDetails = contactController.updateContactDetails(contact);
		assertEquals(HttpStatus.OK, updateContactDetails.getStatusCode());
	}

}
