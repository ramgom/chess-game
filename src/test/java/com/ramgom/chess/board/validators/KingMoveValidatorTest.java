package com.ramgom.chess.board.validators;

import com.ramgom.chess.board.Position;
import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.Piece;
import com.ramgom.chess.board.piece.PieceType;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.ramgom.chess.board.validators.CommonMoveValidatorUtils.COLOR;

class KingMoveValidatorTest {

    private final MoveValidator kingMoveValidator = new KingMoveValidator();

    @ParameterizedTest
    @MethodSource("validateParams")
    public void validate(Position[] initialPositions, Piece[] pieces, Position from, Position[] targets, boolean result) {
        CommonMoveValidatorUtils.validate(
                kingMoveValidator,
                initialPositions,
                pieces,
                from,
                targets,
                result
        );
    }

    static Stream<Arguments> validateParams() {
        return Stream.of(
                Arguments.arguments(
                        Arrays.array(new Position(3, 3)),
                        Arrays.array(new Piece(COLOR, PieceType.KING)),
                        new Position(3, 3),
                        Arrays.array(
                                new Position(2, 2),
                                new Position(2, 3),
                                new Position(2, 4),
                                new Position(3, 2),
                                new Position(4, 2),
                                new Position(4, 3),
                                new Position(2, 4),
                                new Position(3, 4)
                        ),
                        true
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3), new Position(4, 3)),
                        Arrays.array(new Piece(COLOR, PieceType.KING), new Piece(Color.BLACK, PieceType.PAWN)),
                        new Position(3, 3),
                        Arrays.array(new Position(4, 3)),
                        true
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3)),
                        Arrays.array(new Piece(COLOR, PieceType.KING)),
                        new Position(3, 3),
                        Arrays.array(
                                new Position(1, 3),
                                new Position(3, 5),
                                new Position(5, 5),
                                new Position(5, 1),
                                new Position(1, 5),
                                new Position(1, 1),
                                new Position(5, 3),
                                new Position(3, 1)
                        ),
                        false
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3), new Position(4, 2)),
                        Arrays.array(new Piece(COLOR, PieceType.KING), new Piece(Color.WHITE, PieceType.PAWN)),
                        new Position(3, 3),
                        Arrays.array(new Position(4, 2)),
                        false
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3)),
                        Arrays.array(new Piece(Color.BLACK, PieceType.KING)),
                        new Position(3, 3),
                        Arrays.array(new Position(4, 2)),
                        false
                ),
                Arguments.arguments(
                        new Position[0],
                        new Piece[0],
                        new Position(3, 3),
                        Arrays.array(new Position(4, 2)),
                        false
                )
        );
    }
}