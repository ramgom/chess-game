package com.ramgom.chess.game;

import com.ramgom.chess.board.Board;
import com.ramgom.chess.board.BoardInitializer;
import com.ramgom.chess.board.CheckValidator;
import com.ramgom.chess.board.Position;
import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.Piece;
import com.ramgom.chess.board.validators.MoveValidator;
import com.ramgom.chess.board.validators.MoveValidatorProvider;
import com.ramgom.chess.game.output.GameOutput;
import com.ramgom.chess.players.Player;
import com.whitehatgaming.UserInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class ChessGame {
    private static final String NEW_PLAY = "********* New Play *********";
    private static final String INITIAL_STATE = "********* Initial State *********";

    private final Board board = new Board();
    private final GameOutput<String> gameOutput;
    private final BoardInitializer boardInitializer;
    private final UserInput userInput;
    private final MoveValidatorProvider moveValidatorProvider;
    private final Player playerOne = new Player("Player One", Color.WHITE);
    private final Player playerTwo = new Player("Player Two", Color.BLACK);
    private final CheckValidator checkValidator;

    @PostConstruct
    public void init() {
        boardInitializer.init(board);
        gameOutput.addConverter(playerOne, p -> p.toShortStringValue().toUpperCase());
        gameOutput.addConverter(playerTwo, p -> p.toShortStringValue().toLowerCase());
    }

    public void start() {
        gameOutput.showMessage(INITIAL_STATE);
        gameOutput.showBoard(board);

        try {
            play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void play() throws IOException {
        Player currentPlayer = playerOne;
        int[] moves = userInput.nextMove();
        while (moves != null) {

            Position from = new Position(moves[1], moves[0]);
            Position to = new Position(moves[3], moves[2]);

            if (checkValidator.inCheckValidator(board, currentPlayer.color())) {
                gameOutput.showChecked();
            }

            switch (move(currentPlayer, from, to)) {
                case VALID -> {
                    currentPlayer = switchCurrentPlayer(currentPlayer);
                    showBoard();
                }
                case INVALID -> showErrorMessage(String.format("Invalid move from %s to %s", from, to), currentPlayer);
                case IN_CHECK -> showErrorMessage("King in check", currentPlayer);

            }

            moves = userInput.nextMove();
        }
    }

    protected void showBoard() {
        gameOutput.showMessage("");
        gameOutput.showMessage(NEW_PLAY);
        gameOutput.showBoard(board);
    }

    protected void showErrorMessage(String message, Player currentPlayer) {
        gameOutput.showMessage(message);
        gameOutput.showMessage(String.format("Player %s will continue playing", currentPlayer));
    }

    protected Player switchCurrentPlayer(Player player) {
        return player == playerOne ? playerTwo:playerOne;
    }

    protected MoveState move(Player player, Position from, Position to) {
        Optional<Piece> pieceInPosition = board.getPiece(from.row(), from.column());

        if (pieceInPosition.isEmpty()) {
            return MoveState.INVALID;
        }

        Piece piece = pieceInPosition.get();

        MoveValidator moveValidator = moveValidatorProvider.moveValidator(piece);

        if (!moveValidator.validate(board, player.color(), from, to)) {
            return MoveState.INVALID;
        }

        Optional<Piece> deletedPiece = board.removePiece(from.row(), from.column());
        board.setPiece(piece, to.row(), to.column());

        if (checkValidator.inCheckValidator(board, player.color())) {
            deletedPiece.ifPresent(value -> board.setPiece(value, to.row(), to.column()));
            board.setPiece(piece, from.row(), from.column());

            return MoveState.IN_CHECK;
        }

        return MoveState.VALID;
    }

    enum MoveState {
        VALID, INVALID, IN_CHECK
    }
}
