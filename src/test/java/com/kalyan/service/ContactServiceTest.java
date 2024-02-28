package com.kalyan.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.kalyan.entity.Contact;
import com.kalyan.exception.NoContactFoundException;
import com.kalyan.exception.UnableToUpdateRecord;
import com.kalyan.repository.ContactRepository;

@SpringBootTest
public class ContactServiceTest {

	@InjectMocks
	private ContactService contactService;

	@Mock
	private ContactRepository contactRepository;

	@Test
	public void insertContactDetailsTest() {
		Contact contact = new Contact(12345, "Thalari Kalyan", "thalarikalyaninfo@gmail.com", 9985285282L, "success");
		when(contactRepository.save(contact)).thenReturn(contact);
		boolean insertContactetails = contactService.insertContactetails(contact);
		assertEquals(true, insertContactetails);
	}

	@Test
	public void insertContactDetailsFailureTest() {
		Contact contact = new Contact();
		when(contactRepository.save(contact)).thenReturn(contact);
		// when(contactService.insertContactetails(contact)).thenReturn(Mockito.anyBoolean());
		// (No need of mocking this)
		boolean insertContactetails = contactService.insertContactetails(contact);
		assertEquals(false, insertContactetails);
	}

	@Test
	public void getAllContactDetailsTest() {
		Contact contact = new Contact(12345, "Thalari Kalyan", "thalarikalyaninfo@gmail.com", 9985285282L, "success");
		Contact contact1 = new Contact(12346, "Thalari Venkatesh", "venki.170292@gmail.com", 9492653432L, "success");
		Contact contact2 = new Contact(123457, "Murali", "murali123@gmail.com", 987654321L, "success");
		List<Contact> listOfContacts = List.of(contact, contact1, contact2);

		when(contactRepository.findAll()).thenReturn(listOfContacts);
		List<Contact> contactDetails = contactService.getContactDetails();
		assertEquals(12345, contactDetails.get(0).getCid());
	}

	@Test
	public void getAllContactDetailsFailureTest() {
		when(contactRepository.findAll()).thenReturn(new ArrayList<>());
		assertThrows(NoContactFoundException.class, () -> contactService.getContactDetails());
	}

	@Test
	public void getPerticularContactDetailsTest() {
		Contact contact = new Contact(12345, "Thalari Kalyan", "thalarikalyaninfo@gmail.com", 9985285282L, "success");
		Optional<Contact> optionalContact = Optional.of(contact);
		when(contactRepository.findById(contact.getCid())).thenReturn(optionalContact);
		Contact contactDetailsById = contactService.getContactDetailsById(contact.getCid());
		assertEquals(contact.getCid(), contactDetailsById.getCid());
	}

	@Test
	public void getPerticularContactDetailFailureTest() {

		Contact contact = new Contact();
		Optional<Contact> emptyContactOptional = Optional.empty();
		when(contactRepository.findById(contact.getCid())).thenReturn(emptyContactOptional);
		assertThrows(NoContactFoundException.class, () -> contactService.getContactDetailsById(contact.getCid()));
	}

	@Test
	public void deleteContactDetailsById() {
		Contact contact = new Contact(12345, "Thalari Kalyan", "thalarikalyaninfo@gmail.com", 9985285282L, "success");
		Optional<Contact> optionalContact = Optional.of(contact);
		when(contactRepository.findById(contact.getCid())).thenReturn(optionalContact);
		String deleteContactDetailsById = contactService.deleteContactDetailsById(contact.getCid());
		assertEquals("Contact Details Deleted", deleteContactDetailsById);

	}

	@Test
	public void deleteContactDetailsByIdFailureTest() {
		Contact contact = new Contact();
		Optional<Contact> emptyContactOptional = Optional.empty();
		when(contactRepository.findById(contact.getCid())).thenReturn(emptyContactOptional);
		assertThrows(NoContactFoundException.class, () -> contactService.deleteContactDetailsById(contact.getCid()));

	}

	@Test
	public void updateContactDetailsById() {
		Contact contact = new Contact(12345, "Thalari Kalyan", "thalarikalyaninfo@gmail.com", 9985285282L, "success");
		Optional<Contact> optionalContact = Optional.of(contact);
		when(contactRepository.findById(contact.getCid())).thenReturn(optionalContact);
		when(contactRepository.save(contact)).thenReturn(contact);
		String updateContactDetailsById = contactService.updateContactDetailsById(contact);
		assertEquals("Given Contact Details is Updated Sucessfully:" + contact.getCid(), updateContactDetailsById);

	}

	@Test
	public void updateContactDetailsById_FailureTest() {
		Contact contact = new Contact();
		Optional<Contact> optionalContact = Optional.of(contact);
		when(contactRepository.findById(contact.getCid())).thenReturn(optionalContact);
		when(contactRepository.save(contact)).thenReturn(contact);
		assertThrows(UnableToUpdateRecord.class, () -> contactService.updateContactDetailsById(contact));

	}

}
