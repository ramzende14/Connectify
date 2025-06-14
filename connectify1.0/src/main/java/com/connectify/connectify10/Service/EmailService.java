package com.connectify.connectify10.Service;

public interface EmailService {


    //
    void sendEmail(String to,String subject ,String body);

    //
    void sendEmilWithHtml();

    //
    void sendEmilWithAttachment();
        
    

    
}
