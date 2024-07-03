package com.elife.sprotscape.coucheService.servicesContact;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elife.sprotscape.DAO.ContactRepository.ContactRepository;
import com.elife.sprotscape.DTO.ContactDTO.ContactRequestDTO;
import com.elife.sprotscape.Entities.Contact;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class servicesContactImpl implements servicesContact {
    private ContactRepository contactRepository;

    @Override
    public void addContact(ContactRequestDTO contactRequestDTO) {
        Contact contact = new Contact();
        contact.setNom(contactRequestDTO.getNom());
        contact.setPrenom(contactRequestDTO.getPrenom());
        contact.setTelephone(contactRequestDTO.getTelephone());
        contact.setSujet(contactRequestDTO.getSujet());
        contact.setMail(contactRequestDTO.getMail());
        contact.setDescription(contactRequestDTO.getDescription());
        contactRepository.save(contact);
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).get();
    }

    @Override
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    @Override
    public void updateContact(ContactRequestDTO contactRequestDTO, Long id) {
        Contact contact = contactRepository.findById(id).get();

        if (contactRequestDTO.getNom() != "") {
            contact.setNom(contactRequestDTO.getNom());
        }
        if (contactRequestDTO.getPrenom() != "") {
            contact.setPrenom(contactRequestDTO.getPrenom());
        }
        if (contactRequestDTO.getTelephone() != -1) {
            contact.setTelephone(contactRequestDTO.getTelephone());
        }
        if (contactRequestDTO.getSujet() != "") {
            contact.setSujet(contactRequestDTO.getSujet());
        }
        if (contactRequestDTO.getMail() != "") {
            contact.setMail(contactRequestDTO.getMail());
        }
        if (contactRequestDTO.getDescription() != "") {
            contact.setDescription(contactRequestDTO.getDescription());
        }

        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        Contact contact = contactRepository.findById(id).get();
        contactRepository.delete(contact);
    }

}
