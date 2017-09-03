package ru.academits.dao;

import ru.academits.entity.Contact;

import java.util.List;

/**
 * Created by Link on 01.09.2017.
 */
public interface ContactDao {
    List<Contact> getAllContacts();

    void add(Contact contact);

    void remove(int index);
}
