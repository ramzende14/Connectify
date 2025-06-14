package com.resumemaker.resumemaker.Controller;

import com.resumemaker.resumemaker.Service.ResumeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@RestController

@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateResume(@RequestBody String userResumeDescription) {
        try {
            String resumeResponse = resumeService.generateResumeResponse(userResumeDescription);
            return ResponseEntity.ok(resumeResponse);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error generating resume: " + e.getMessage());
        }
    }
}
