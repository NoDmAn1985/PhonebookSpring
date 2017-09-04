package ru.academits.phonebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.academits.converter.ContactDtoToContactConverter;
import ru.academits.converter.ContactToContectDtoConverter;
import ru.academits.dto.ContactDto;
import ru.academits.entity.Contact;
import ru.academits.bean.ContactValidation;
import ru.academits.savetoexcel.SaveToExcel;
import ru.academits.service.ContactService;

import java.util.List;

/**
 * Created by Anna on 17.07.2017.
 */

@RestController
@RequestMapping("/phoneBook/rcp/api/v1")
public class PhoneBookController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactToContectDtoConverter contactToContectDtoConverter;

    @Autowired
    private ContactDtoToContactConverter contactDtoToContactConverter;

    @RequestMapping(value = "getAllContacts", method = RequestMethod.GET)
    public List<ContactDto> getAllContacts() {
        return contactToContectDtoConverter.convert(contactService.getAllContacts());
    }

    @RequestMapping(value = "addContact", method = RequestMethod.POST)
    public ContactValidation addContact(@RequestBody ContactDto contact) {
        Contact contactEntity = contactDtoToContactConverter.convert(contact);
        return contactService.addContact(contactEntity);
    }

    @RequestMapping(value = "removeContact", method = RequestMethod.POST)
    public void removeContact(@RequestParam("index") int id) {
        contactService.removeContact(id);
    }

    @RequestMapping(value = "saveToExcel", method = RequestMethod.POST)
    public void saveToExcel() {
        SaveToExcel.save(contactService.getAllContacts());
    }
}

