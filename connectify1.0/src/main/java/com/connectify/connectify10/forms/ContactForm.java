package com.connectify.connectify10.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ContactForm {
    @NotBlank(message = "Name is Required")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Address ")
    private String email;

    @NotBlank( message = "Phone No is Required")
    
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    
    private String description;


    private boolean favorite;


    private String websiteLink;


    private String linkdinLink;
    //annotation  create karege jo file ko validate karega
    //size
    //resloution
    //type
    


    private MultipartFile contactImage;



    private String picture;
       
    
}
