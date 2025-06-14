package com.resumemaker.resumemaker.Service;

import com.resumemaker.resumemaker.Service.ResumeService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;
@Service


public class ResumeServiceImpl implements ResumeService {

    private final OllamaChatModel chatClient;

    public ResumeServiceImpl(OllamaChatModel chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String generateResumeResponse(String userResumeDescription) throws IOException {
        String promptString = this.loadPromptFromFile("resume_prompt.txt");
        String promptContent = this.putValuesToTemplates(promptString, Map.of(
                "userDescription", userResumeDescription
        ));

        Prompt prompt = new Prompt(promptContent);

        try {
            // Call the AI model
            ChatResponse responseObj = chatClient.call(prompt);

            // Debugging - Print full response
            System.out.println("Raw Response: " + responseObj);

            if (responseObj != null && !responseObj.getResults().isEmpty()) {
                // Extract assistant's message from response
                AssistantMessage assistantMessage = (AssistantMessage) responseObj.getResults().get(0).getOutput();
                return assistantMessage.getText(); // ✅ FIXED: Use getText() instead of getContent()
            } else {
                return "Error: No response received.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Unable to process resume request.";
        }
    }

    private String loadPromptFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new ClassPathResource(filename).getInputStream()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    private String putValuesToTemplates(String template, Map<String, String> values) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            template = template.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return template;
    }
}
