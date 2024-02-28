package com.kalyan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalyan.entity.Contact;
import com.kalyan.exception.NoContactFoundException;
import com.kalyan.service.ContactService;

@RestController
@RequestMapping("/ContactDetailsWar")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@PostMapping("/save")
	public ResponseEntity<String> saveRecord(@RequestBody Contact contact) {

		boolean status = contactService.insertContactetails(contact);
		if (status)
			return new ResponseEntity<String>("Record Inserted", HttpStatus.CREATED);
		else {
			return new ResponseEntity<String>("Failure to Insert", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getAllContactDetails")
	public ResponseEntity<List<Contact>> getAllDetails() {
		try {
			List<Contact> allContactDetails = contactService.getContactDetails();
			if (allContactDetails != null)
				return new ResponseEntity<List<Contact>>(allContactDetails, HttpStatus.OK);
		} catch (Exception e) {
			throw new NoContactFoundException("No Contact Details Found::");
		}
		return null;

	}

	@GetMapping("/getContById/{contactid}")
	public ResponseEntity<Contact> getContactDetailsById(@PathVariable Integer contactid)
			throws NoContactFoundException {

		Contact contactDetails = contactService.getContactDetailsById(contactid);
		if (contactDetails != null)
			return new ResponseEntity<Contact>(contactDetails, HttpStatus.OK);
		return null;

	}

	@GetMapping("/deleteContById/{contactid}")
	public ResponseEntity<String> deleteContactDetailsById(@PathVariable Integer contactid)
			throws NoContactFoundException {

		String contactDetails = contactService.deleteContactDetailsById(contactid);
		if (contactDetails != null)
			return new ResponseEntity<String>("Contact Deleted Successfully!!", HttpStatus.OK);
		return null;

	}

	@PutMapping("/updateContact")
	public ResponseEntity<String> updateContactDetails(@RequestBody Contact contact) throws NoContactFoundException {

		String contactDetails = contactService.updateContactDetailsById(contact);
		if (contactDetails != null)
			return new ResponseEntity<String>("Record Updated Sucessfully for " + contact.getCid(), HttpStatus.OK);
		return null;

	}

}
