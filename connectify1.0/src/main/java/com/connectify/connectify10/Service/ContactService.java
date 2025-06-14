package com.connectify.connectify10.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;



import com.connectify.connectify10.entity.Contact;
import com.connectify.connectify10.entity.User;


public interface ContactService {

    

    // save contact
    Contact save(Contact contact);

//    update contact
    Contact update(Contact contact);


    //get contact
    List<Contact>getAll();

    //get Contact by id

    Contact getById(String id);


    Optional<Contact> findById(String id);


    //delete Contact
    void delete(String id);


    //search contact

    Page<Contact> searchByName(String nameKeyword,int size,int page,String sortBy,String order,User user);
    Page<Contact> searchByEmail(String emailKeyword,int size,int page,String sortBy,String order,User user);

    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword,int size,int page,String sortBy,String order,User user);


    //get contact by userid

    List<Contact> getUserById(String userId);

    //
    Page<Contact> getByUser(User user,int page,int size,String sortFeild,String sortDirection);

    long getTotalContactCount();

    long getContactCountByUser(String userId);

   



}
