package com.artemnizhnyk.worker.service;

import com.artemnizhnyk.store.entity.SendEmailTaskEntity;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class EmailClientApi {

    /**
     * @return true if email delivered to destinationEmailAddress
     */
    public boolean sendEmail(final String destinationEmailAddress, final String mailMessage) {

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }
}
