package com.artemnizhnyk.myworker.config;

import com.artemnizhnyk.store.EnableSMPTStore;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Import({EnableSMPTStore.class})
@Configuration
@EnableScheduling
public class ImportConfig {
}
