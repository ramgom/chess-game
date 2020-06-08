package com.ramgom.chess.board.validators;

import com.ramgom.chess.board.Board;
import com.ramgom.chess.board.Position;
import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.Piece;
import com.ramgom.chess.board.piece.PieceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.ramgom.chess.board.piece.Color.BLACK;
import static com.ramgom.chess.board.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class PawnMoveValidatorTest {

    private final MoveValidator pawnMoveValidator = new PawnMoveValidator();

    @ParameterizedTest
    @MethodSource("validateParams")
    public void validate(Color color, Position from, Position to, Boolean result) {
        Board board = new Board();

        board.setPiece(new Piece(color, PieceType.PAWN), from);

        boolean isValid = pawnMoveValidator.validate(board, color, from, to);

        assertThat(isValid).isEqualTo(result);
    }

    static Stream<Arguments> validateParams() {
        return Stream.of(
                Arguments.arguments(BLACK, new Position(1, 3), new Position(2, 3), true),
                Arguments.arguments(BLACK, new Position(1, 3), new Position(3, 3), true),
                Arguments.arguments(BLACK, new Position(2, 3), new Position(3, 3), true),
                Arguments.arguments(BLACK, new Position(2, 3), new Position(4, 3), false),
                Arguments.arguments(BLACK, new Position(1, 3), new Position(2, 2), false),
                Arguments.arguments(BLACK, new Position(1, 3), new Position(2, 4), false),
                Arguments.arguments(BLACK, new Position(2, 3), new Position(1, 3), false),
                Arguments.arguments(WHITE, new Position(6, 3), new Position(5, 3), true),
                Arguments.arguments(WHITE, new Position(6, 3), new Position(4, 3), true),
                Arguments.arguments(WHITE, new Position(5, 3), new Position(4, 3), true),
                Arguments.arguments(WHITE, new Position(5, 3), new Position(3, 3), false),
                Arguments.arguments(WHITE, new Position(6, 3), new Position(5, 2), false),
                Arguments.arguments(WHITE, new Position(6, 3), new Position(5, 4), false),
                Arguments.arguments(WHITE, new Position(5, 3), new Position(6, 3), false)
        );
    }

    @Test
    public void validateDiagonal() {
        Board board = new Board();

        Position from = new Position(1, 3);
        Position to = new Position(2, 2);
        board.setPiece(new Piece(BLACK, PieceType.PAWN), from);
        board.setPiece(new Piece(WHITE, PieceType.BISHOP), to);

        boolean isValid = pawnMoveValidator.validate(board, BLACK, from, to);

        assertThat(isValid).isTrue();
    }
}