package com.connectify.connectify10.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

//all protected url here

import com.connectify.connectify10.entity.User;
import com.connectify.connectify10.helpers.Helper;
import com.connectify.connectify10.Service.ContactService;
import com.connectify.connectify10.Service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {

        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info(username);

        // database se data ko fetch karana
        User user = userService.getUserByEmail(username);

        System.out.println(user.getName());
        System.out.println(user.getEmail());

        model.addAttribute("loggedInUser", user);

    }

    // User dashboard page
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        long totalContacts = contactService.getTotalContactCount();
        model.addAttribute("totalContacts", totalContacts);

        return "user/dashboard";
    }



    // User profile page
    @RequestMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        return "user/profile";
    }

    // Add contact

    // Other methods can be defined similarly
}
