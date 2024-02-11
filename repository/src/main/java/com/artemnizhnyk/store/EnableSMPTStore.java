package com.artemnizhnyk.store;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.artemnizhnyk.store.repository")
@EntityScan("com.artemnizhnyk.store.entity")
public class EnableSMPTStore {
}
