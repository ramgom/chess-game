package com.ramgom.chess.board.validators;

import com.ramgom.chess.board.Board;
import com.ramgom.chess.board.Position;
import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.Piece;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonMoveValidatorUtils {

    static final Color COLOR = Color.WHITE;

    static void validate(MoveValidator validator,
                         Position[] initialPositions,
                         Piece[] pieces,
                         Position from,
                         Position[] targets,
                         boolean result) {
        Board board = new Board();

        for (int i = 0; i < initialPositions.length; i++) {
            board.setPiece(pieces[i], initialPositions[i]);
        }

        for (Position to: targets) {
            boolean isValid = validator.validate(board, COLOR, from, to);

            assertThat(isValid).isEqualTo(result);
        }
    }
}
