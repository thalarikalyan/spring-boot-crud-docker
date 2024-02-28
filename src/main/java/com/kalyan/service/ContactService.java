package com.kalyan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalyan.entity.Contact;
import com.kalyan.errorcodes.ErrorCode;
import com.kalyan.exception.NoContactFoundException;
import com.kalyan.exception.UnableToUpdateRecord;
import com.kalyan.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	// insert record into table

	public boolean insertContactetails(Contact contact) {
		Contact contacDetails = contactRepository.save(contact);
		return contacDetails.getCid() != null ? true : false;

	}

	// get Contact Details
	public List<Contact> getContactDetails() throws NoContactFoundException {
		List<Contact> getContactDetails = contactRepository.findAll();
		if (getContactDetails.isEmpty())
			throw new NoContactFoundException("No record is found::");
		return getContactDetails;

	}

	// get contact details by id
	public Contact getContactDetailsById(Integer contactid) {

		Optional<Contact> getContact = contactRepository.findById(contactid);
		if (getContact.isPresent())
			return getContact.get();
		else
			throw new NoContactFoundException("No record is found::");

	}

	// Delete Contact Details by Id
	public String deleteContactDetailsById(Integer contactid) {
		Optional<Contact> findById = contactRepository.findById(contactid);
		if (!findById.isEmpty()) {
			contactRepository.delete(findById.get());
			return "Contact Details Deleted";
		} else
			throw new NoContactFoundException("No record is found::");

	}

	// Update Contact Details
	public String updateContactDetailsById(Contact contact) {
		String updatestatus = null;
		Contact contactDetailsById = getContactDetailsById(contact.getCid());
		if (contactDetailsById != null) {
			boolean insertContact = insertContactetails(contact);
			if (insertContact) {
				updatestatus = "Given Contact Details is Updated Sucessfully:" + contact.getCid();
			} else
				throw new UnableToUpdateRecord(ErrorCode.UNABLE_TO_UPDATE_RECORD);
		}
		return updatestatus;

	}

	// soft deletion
	public String softDeleteEntityById(Integer contactid) {
		Optional<Contact> findById = contactRepository.findById(contactid);
		if (findById.isPresent()) {
			Contact contact = findById.get();
			contact.setActivesw("Failure");
			Contact save = contactRepository.save(contact);
			if (save.getCid() != null)
				return "Soft deletion is done";
		}
		return null;

	}

}
