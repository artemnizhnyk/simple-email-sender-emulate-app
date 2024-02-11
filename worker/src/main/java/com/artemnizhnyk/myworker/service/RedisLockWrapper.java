package com.artemnizhnyk.myworker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class RedisLockWrapper {

    private final RedisLock redisLock;

    public void lockAndExecuteTask(final String key, final Duration duration, final Runnable runnable) {

        try {
            if (!redisLock.acquireLock(key, duration)) {
                return;
            }
            runnable.run();
        } finally {
            redisLock.releaseLock(key);
        }
    }
}
