package com.ramgom.chess.game.output;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GameOutputConfiguration {

    @Bean
    GameOutput<String> printStreamGameOutput() {
        return new PrintStreamGameOutput(System.out);
    }
}
