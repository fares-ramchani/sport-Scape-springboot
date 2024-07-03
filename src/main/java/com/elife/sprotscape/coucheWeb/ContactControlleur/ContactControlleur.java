package com.elife.sprotscape.coucheWeb.ContactControlleur;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elife.sprotscape.DTO.ContactDTO.ContactRequestDTO;
import com.elife.sprotscape.Entities.Contact;
import com.elife.sprotscape.coucheService.servicesContact.servicesContact;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/contact")
public class ContactControlleur {
    private servicesContact servicescontact;

    @PostMapping(path = "/add")
    public void addContact(@RequestBody ContactRequestDTO contactRequestDTO) {
        servicescontact.addContact(contactRequestDTO);
    }

    @PutMapping(path = "/update/{id}")
    public void updateContact(@RequestBody ContactRequestDTO contactRequestDTO, Long id) {
        servicescontact.updateContact(contactRequestDTO, id);
    }

    @GetMapping(path = "/all")
    public List<Contact> getAllContacts() {
        return servicescontact.getAllContact();
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteContact(@RequestParam Long id) {
        servicescontact.deleteContact(id);
    }

    @GetMapping(path = "/ById/{id}")
    public Contact getContactById(@RequestParam Long id) {
        return servicescontact.getContactById(id);
    }
}
