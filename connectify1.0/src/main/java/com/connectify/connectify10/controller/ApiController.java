package com.connectify.connectify10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connectify.connectify10.Service.ContactService;
import com.connectify.connectify10.entity.Contact;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ContactService contactService ;

    //get contact
    @GetMapping("/contact/{contactId}")
    public Contact  geContact(@PathVariable String contactId){
        return contactService.getById(contactId);

    }

    
}
