package com.artemnizhnyk.myworker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Random;

@Log4j2
@RequiredArgsConstructor
@Component
public class EmailClientApi {

    private final Random random = new Random();

    /**
     * @return true if email delivered to destinationEmailAddress
     */
    public boolean sendEmail(final String destinationEmailAddress, final String mailMessage) {

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            return false;
        }

        return random.nextBoolean();
    }
}
