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

class RookMoveValidatorTest {

    private final MoveValidator rookMoveValidator = new RookMoveValidator();

    @ParameterizedTest
    @MethodSource("validateParams")
    public void validate(Position[] initialPositions, Piece[] pieces, Position from, Position[] targets, boolean result) {
        CommonMoveValidatorUtils.validate(
                rookMoveValidator,
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
                        Arrays.array(new Piece(COLOR, PieceType.ROOK)),
                        new Position(3, 3),
                        Arrays.array(
                                new Position(0, 3),
                                new Position(6, 3),
                                new Position(3, 2),
                                new Position(3, 7)
                        ),
                        true
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3), new Position(0, 3)),
                        Arrays.array(new Piece(COLOR, PieceType.ROOK), new Piece(Color.BLACK, PieceType.BISHOP)),
                        new Position(3, 3),
                        Arrays.array(new Position(0, 3)),
                        true
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3)),
                        Arrays.array(new Piece(COLOR, PieceType.ROOK)),
                        new Position(3, 3),
                        Arrays.array(
                                new Position(1, 5),
                                new Position(1, 1),
                                new Position(5, 1),
                                new Position(5, 5)
                        ),
                        false
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3), new Position(0, 3), new Position(6, 3)),
                        Arrays.array(new Piece(COLOR, PieceType.ROOK), new Piece(COLOR, PieceType.BISHOP), new Piece(Color.BLACK, PieceType.PAWN)),
                        new Position(3, 3),
                        Arrays.array(
                                new Position(0, 3),
                                new Position(7, 3)
                        ),
                        false
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3)),
                        Arrays.array(new Piece(Color.BLACK, PieceType.ROOK)),
                        new Position(3, 3),
                        Arrays.array(new Position(0, 3)),
                        false
                ),
                Arguments.arguments(
                        new Position[0],
                        new Piece[0],
                        new Position(3, 3),
                        Arrays.array(new Position(0, 3)),
                        false
                )
        );
    }
}