package com.connectify.connectify10.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.connectify.connectify10.Service.ContactService;
import com.connectify.connectify10.Service.UserService;
import com.connectify.connectify10.Service.imageService;
import com.connectify.connectify10.entity.Contact;
import com.connectify.connectify10.entity.User;
import com.connectify.connectify10.forms.ContactForm;
import com.connectify.connectify10.forms.ContactServiceForm;
import com.connectify.connectify10.helpers.AppConstants;
import com.connectify.connectify10.helpers.Helper;
import com.connectify.connectify10.helpers.Message;
import com.connectify.connectify10.helpers.MessageType;
import com.connectify.connectify10.helpers.ResourceNotFoundException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contact")
public class ContactController {
    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;
    @Autowired
    private imageService imageService;
    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        contactForm.setFavorite(true);
        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm,
            BindingResult result, Authentication authentication, HttpSession session) {
        if (result.hasErrors()) {
            session.setAttribute("message",
                    Message.builder().content("Please correct the following errors").type(MessageType.red).build());
            return "user/add_contact";
        }
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        String filename = UUID.randomUUID().toString();
        String fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkdinLink(contactForm.getLinkdinLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);
        contact.setCloudninaryImagePublicId(filename);

        contactService.save(contact);

        session.setAttribute("message",
                Message.builder().content("You have successfully added the contact").type(MessageType.green).build());
        return "redirect:/user/contact/add";
    }

    @RequestMapping
    public String viewContacts(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model, Authentication authentication) {
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        Page<Contact> pageContacts = contactService.getByUser(user, page, size, sortBy, direction);

        model.addAttribute("pageContacts", pageContacts);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        model.addAttribute("contactServiceForm", new ContactServiceForm());
        return "user/contacts";
    }

    @RequestMapping("/search")
    public String searchHandler(@ModelAttribute ContactServiceForm contactServiceForm,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model, Authentication authentication) {
        logger.info("field: {} keyword: {}", contactServiceForm.getField(), contactServiceForm.getValue());
        User user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));

        Page<Contact> pageContact = switch (contactServiceForm.getField().toLowerCase()) {
            case "name" ->
                contactService.searchByName(contactServiceForm.getValue(), size, page, sortBy, direction, user);
            case "email" ->
                contactService.searchByEmail(contactServiceForm.getValue(), size, page, sortBy, direction, user);
            case "phone" ->
                contactService.searchByPhoneNumber(contactServiceForm.getValue(), size, page, sortBy, direction, user);
            default -> null;
        };

        model.addAttribute("pageContacts", pageContact);
        model.addAttribute("contactServiceForm", contactServiceForm);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        return "user/search";
    }

    @GetMapping("/update/{contactId}")
    public String updateContactFormView(@PathVariable("contactId") String contactId, Model model) {
        Contact contact = contactService.getById(contactId);
        ContactForm contactForm = new ContactForm();
        contactForm.setName(contact.getName());
        contactForm.setEmail(contact.getEmail());
        contactForm.setPhoneNumber(contact.getPhoneNumber());
        contactForm.setAddress(contact.getAddress());
        contactForm.setDescription(contact.getDescription());
        contactForm.setFavorite(contact.isFavorite());
        contactForm.setWebsiteLink(contact.getWebsiteLink());
        contactForm.setLinkdinLink(contact.getLinkdinLink());

        model.addAttribute("contactForm", contactForm);
        model.addAttribute("contactId", contactId);
        return "user/update_contact_view";
    }

    @GetMapping("/view/{id}")
    public String viewContact(@PathVariable("id") String id, Model model) {
        Contact contact = contactService.findById(id).orElse(null);
        model.addAttribute("contact", contact);
        return "user/contact_view";
    }

    // upd means = update
    @RequestMapping(value = "/upd/{contactId}", method = RequestMethod.POST)
    public String updateContact(@PathVariable("contactId") String contactId, @ModelAttribute ContactForm contactForm,
            Model model) {
        // update contact

        var cont = new Contact();
        cont.setId(contactId);
        cont.setName(contactForm.getName());
        cont.setEmail(contactForm.getEmail());
        cont.setPhoneNumber(contactForm.getPhoneNumber());
        cont.setAddress(contactForm.getAddress());
        cont.setDescription(contactForm.getDescription());
        cont.setFavorite(contactForm.isFavorite());
        cont.setWebsiteLink(contactForm.getWebsiteLink());
        cont.setLinkdinLink(contactForm.getLinkdinLink());

        // process image
        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            logger.info("file is not empty");
            String fileName = UUID.randomUUID().toString();
            String imageUrl = imageService.uploadImage(contactForm.getContactImage(), fileName);
            cont.setCloudninaryImagePublicId(fileName);
            cont.setPicture(imageUrl);
            contactForm.setPicture(imageUrl);

        } else {
            logger.info("file is empty");
        }

        var updateCont = contactService.update(cont);
        logger.info("updated contact {}", updateCont);
        model.addAttribute("message", Message.builder().content("Contact Updated !!").type(MessageType.green).build());

        return "redirect:/user/contact/view/" + contactId;

    }

    @RequestMapping(value = "/delete/{contactId}", method = RequestMethod.POST)
    public String deleteContact(@PathVariable("contactId") String contactId, HttpSession session) {
        try {
            contactService.delete(contactId);
            session.setAttribute("message",
                    Message.builder().content("Contact deleted successfully").type(MessageType.green).build());
        } catch (ResourceNotFoundException e) {
            session.setAttribute("message",
                    Message.builder().content(e.getMessage()).type(MessageType.red).build());
        } catch (Exception e) {
            session.setAttribute("message",
                    Message.builder().content("Error deleting contact").type(MessageType.red).build());
        }
        return "redirect:/user/contacts";
    }

}
