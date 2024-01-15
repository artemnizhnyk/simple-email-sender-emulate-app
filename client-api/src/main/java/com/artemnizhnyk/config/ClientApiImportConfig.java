package com.artemnizhnyk.config;

import com.artemnizhnyk.store.EnableSMPTStore;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({EnableSMPTStore.class})
@Configuration
public class ImportConfig {
}
