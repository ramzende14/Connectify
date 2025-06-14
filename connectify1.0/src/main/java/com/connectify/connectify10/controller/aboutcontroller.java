package com.connectify.connectify10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.connectify.connectify10.Service.UserService;
import com.connectify.connectify10.entity.User;
import com.connectify.connectify10.forms.UserForm;
import com.connectify.connectify10.helpers.Message;
import com.connectify.connectify10.helpers.MessageType;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class aboutcontroller {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String index(){
        return "redirect:/home";

    }

    @GetMapping("/home")
    public String home() {
       
        return "home";
    }

    @GetMapping("/about")
    public String about() {
      
        return "about";
    }

    @GetMapping("/service")
    public String service() {
       
        return "service";
    }
    @GetMapping("/contact")
    public String contact(){
        return new String("contact");
    }
    // this is registration controller -veiw page login
    @GetMapping("/login")
    public String login(){
        return new String("login");

        



    }
    //forgot password 
    @GetMapping("/forgot")
    public String showForgotPasswordPage() {
        return "forgot"; // This should match your HTML file name in the 'templates' folder
    }
    //forgot username 











    //process registration reg
    @GetMapping("/signup")
    public String signup(Model model){
        UserForm userForm = new UserForm();
        // we can include siome default data here
        
        model.addAttribute("userForm", userForm);
        return "register";

    }
    //processing register
    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult, HttpSession session) {
    System.out.println("Processing Registration");

    if(rBindingResult.hasErrors()){
        return "register";
    }

    // Create and save the user
    User user = new User();
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setAbout(userForm.getAbout());
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setProfilePic("null");
    user.setEnabled(false);

    User savedUser = userService.saveUser(user);
    System.out.println(savedUser);

    // Set the session message
    Message message = Message.builder()
                             .content("Registration Successful")
                             .type(MessageType.blue)
                             .build();
    session.setAttribute("message", message);

    // Redirect to the signup page
    return "redirect:/signup";
}

   
        
    }

