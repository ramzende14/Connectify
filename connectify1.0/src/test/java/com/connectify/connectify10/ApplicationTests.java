package com.connectify.connectify10;

import com.connectify.connectify10.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class ApplicationTests {

    @Autowired
    private EmailService emailService;

    @Test
    void sendEmailTest() {
        assertDoesNotThrow(() -> {
            emailService.sendEmail("zenderamkrushna@gmail.com", "Test Email", "This is a test email body.");
        });
    }
}
