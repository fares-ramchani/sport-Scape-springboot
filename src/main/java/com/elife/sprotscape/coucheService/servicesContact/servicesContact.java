package com.elife.sprotscape.coucheService.servicesContact;

import java.util.List;

import com.elife.sprotscape.DTO.ContactDTO.ContactRequestDTO;
import com.elife.sprotscape.Entities.Contact;

public interface servicesContact {
    public void addContact(ContactRequestDTO ContactRequestDTO);

    public Contact getContactById(Long id);

    public List<Contact> getAllContact();

    public void updateContact(ContactRequestDTO ContactRequestDTO, Long id);

    public void deleteContact(Long id);
}
