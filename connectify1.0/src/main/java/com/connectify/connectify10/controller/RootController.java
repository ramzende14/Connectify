package com.connectify.connectify10.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.connectify.connectify10.Service.UserService;
import com.connectify.connectify10.entity.User;
import com.connectify.connectify10.helpers.Helper;

@ControllerAdvice
public class RootController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;


     @ModelAttribute
    public void addLoggedInUserInformation(Model model , Authentication authentication ){
        if(authentication == null){
            return;
        }

        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info(username);

        //database se data ko fetch karana
        User user = userService.getUserByEmail(username);
        if(user == null){
            model.addAttribute("loggedInUser", null);



        }else{
            System.out.println(user.getName());
            System.out.println(user.getEmail());
    
            model.addAttribute("loggedInUser", user);
    

        }

     
    }
    
}
