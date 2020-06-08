package com.ramgom.chess;

import com.whitehatgaming.UserInput;
import com.whitehatgaming.UserInputFile;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties
@Slf4j
public class GameConfiguration {

    @Bean
    public UserInput userInputFile(GameProperties properties) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(properties.inputFile);
        String file = classPathResource.getURL().getPath();
        log.info("Loading input file: {}", file);
        return new UserInputFile(file);
    }

    @Configuration
    @ConfigurationProperties
    @Getter
    @Setter
    static class GameProperties {
        String inputFile;
    }
}
