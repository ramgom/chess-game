package com.ramgom.chess;

import com.ramgom.chess.game.ChessGame;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ChessGameApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChessGameApplication.class, args);
	}

	private final ChessGame game;

	@Override
	public void run(String... args) {
		game.start();
	}
}
