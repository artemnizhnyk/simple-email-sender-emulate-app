package com.artemnizhnyk.api.controller;

import com.artemnizhnyk.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class EmailController {

    private final EmailService emailService;
    public static final String SEND_EMAIL = "/email/send";

    @GetMapping("/")
    public String helloWorld() {
        return "Hello world";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(SEND_EMAIL)
    public void sendEmail(@RequestParam("destination_email") final String destinationEmailAddress,
                          @RequestParam("mail_message") final String mailMessage) {

        emailService.saveSendEmailTask(destinationEmailAddress, mailMessage);

        emailService.sendEmail(destinationEmailAddress, mailMessage);
    }
}
