package com.resumemaker.resumemaker;

import com.resumemaker.resumemaker.Service.ResumeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ResumeMakerApplicationTests {
    @Autowired
    private ResumeService resumeService;

    @Test
    void contextLoads() throws IOException {
        resumeService.generateResumeResponse("i am a ramkrushna zende with 2 year of java experience");
    }
}