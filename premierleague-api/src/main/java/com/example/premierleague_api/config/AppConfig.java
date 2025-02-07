package com.example.premierleague_api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Value("${app.name}")
    private String appName;

    @Value("${app.owner}")
    private String appOwner;

    @Value("${app.version}")
    private String appVersion;

    public void printAppInfo() {
        logger.info("Application Name: {}", appName);
        logger.info("Application Version: {}", appVersion);
        logger.info("Application Owner: {}", appOwner);
    }
}
