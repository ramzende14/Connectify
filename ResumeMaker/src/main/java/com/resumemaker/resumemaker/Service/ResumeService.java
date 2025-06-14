package com.resumemaker.resumemaker.Service;

import org.springframework.stereotype.Service;

import java.io.IOException;


public interface ResumeService {

    String generateResumeResponse(String userResumeDescription) throws IOException;
}
