package com.artemnizhnyk.worker.sheduled;

import com.artemnizhnyk.store.entity.SendEmailTaskEntity;
import com.artemnizhnyk.store.repository.SendEmailTaskRepository;
import com.artemnizhnyk.worker.service.EmailClientApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Component
public class SendEmailTaskScheduler {

    private final SendEmailTaskRepository sendEmailTaskRepository;
    private final EmailClientApi emailClientApi;

    @Scheduled(cron = "*/5 * * * * *")
    public void sendEmail() {

        sendEmailTaskRepository.findAllByProcessedAtIsNullOrderByCreatedAt()
                .forEach(sendEmailTask -> {

                    String destinationEmailAddress = sendEmailTask.getDestinationEmailAddress();
                    String message = sendEmailTask.getMessage();

                    boolean isDelivered = emailClientApi.sendEmail(destinationEmailAddress, message);

                    log.info(String.format("Email to %s is successfully sent", destinationEmailAddress));

                    if (isDelivered) {
                        log.info(String.format("Email to %s is successfully sent", destinationEmailAddress));
                        sendEmailTaskRepository.
                    }
                });
    }
}
