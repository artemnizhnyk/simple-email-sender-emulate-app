package com.artemnizhnyk.service;

import com.artemnizhnyk.store.entity.SendEmailTaskEntity;
import com.artemnizhnyk.store.repository.SendEmailTaskRepository;
import com.artemnizhnyk.myworker.service.EmailClientApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final SendEmailTaskRepository sendEmailTaskRepository;
    private final EmailClientApi emailClientApi;

    public void sendEmail(final String destinationEmailAddress, final String mailMessage) {
        emailClientApi.sendEmail(destinationEmailAddress, mailMessage);
    }

    public void saveSendEmailTask(final String destinationEmailAddress, final String mailMessage) {
        sendEmailTaskRepository.save(SendEmailTaskEntity.builder()
                .message(mailMessage)
                .destinationEmailAddress(destinationEmailAddress)
                .build());
    }
}
