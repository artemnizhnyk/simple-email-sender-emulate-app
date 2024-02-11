package com.artemnizhnyk.myworker.sheduled;

import com.artemnizhnyk.myworker.service.RedisLock;
import com.artemnizhnyk.myworker.service.RedisLockWrapper;
import com.artemnizhnyk.store.entity.SendEmailTaskEntity;
import com.artemnizhnyk.store.repository.SendEmailTaskRepository;
import com.artemnizhnyk.myworker.service.EmailClientApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Component
public class SendEmailTaskScheduler {

    private final SendEmailTaskRepository sendEmailTaskRepository;
    private final RedisLockWrapper redisLockWrapper;
    private final EmailClientApi emailClientApi;
    private static final Duration TASK_EXECUTE_DURATION = Duration.ofSeconds(10);
    public static final String SEND_EMAIL_TASK_KEY_FORMAT = "artemnizhnyk:send:email:task:%s";

    @Scheduled(cron = "*/5 * * * * *")
    @Transactional
    public void sendEmail() {

        log.debug("Worker start execution.");

        Instant  latestTryAtGte = Instant.now().minus(TASK_EXECUTE_DURATION);

        sendEmailTaskRepository.findAllNotProcessedIds(latestTryAtGte)
                .forEach(sendEmailTaskId -> {

                    String sendEmailTaskKey = getSendEmailTaskKey(sendEmailTaskId);

                    redisLockWrapper.lockAndExecuteTask(
                            sendEmailTaskKey,
                            Duration.ofSeconds(5),
                            () -> sendEmail(sendEmailTaskId)
                    );
                });
    }


    private void sendEmail(final Long sendEmailTaskId) {

        Instant  latestTryAtGte = Instant.now().minus(TASK_EXECUTE_DURATION);

        Optional<SendEmailTaskEntity> optionalSendEmailTask = sendEmailTaskRepository
                .findNotProcessedById(sendEmailTaskId, latestTryAtGte);

        if (optionalSendEmailTask.isEmpty()) {
            log.info("Task with id %d has already been sent".formatted(sendEmailTaskId));
            return;
        }
        SendEmailTaskEntity sendEmailTask = optionalSendEmailTask.get();

        String destinationEmailAddress = sendEmailTask.getDestinationEmailAddress();
        String message = sendEmailTask.getMessage();

        boolean isDelivered = emailClientApi.sendEmail(destinationEmailAddress, message);

        if (isDelivered) {
            log.debug(String.format("Email to %s with id %d is successfully sent"
                    , destinationEmailAddress, sendEmailTask.getId()));

            sendEmailTaskRepository.markAsProcessed(sendEmailTask.getId());
        }

        log.warn(String.format("Email to %s with id %d wasn't sent and backing to process"
                , destinationEmailAddress, sendEmailTask.getId()));

        sendEmailTaskRepository.updateLatestTryAt(sendEmailTask.getId());
    }

    private static String getSendEmailTaskKey(Long taskId) {
        return SEND_EMAIL_TASK_KEY_FORMAT.formatted(taskId);
    }
}
