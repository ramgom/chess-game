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

class KnightMoveValidatorTest {

    private final MoveValidator knightMoveValidator = new KnightMoveValidator();

    @ParameterizedTest
    @MethodSource("validateParams")
    public void validate(Position[] initialPositions, Piece[] pieces, Position from, Position[] targets, boolean result) {
        CommonMoveValidatorUtils.validate(
                knightMoveValidator,
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
                        Arrays.array(new Piece(COLOR, PieceType.KNIGHT)),
                        new Position(3, 3),
                        Arrays.array(
                                new Position( 2, 1),
                                new Position( 2, 5),
                                new Position( 1, 2),
                                new Position( 1, 4),
                                new Position( 4, 1),
                                new Position( 4, 5),
                                new Position( 5, 2),
                                new Position( 5, 4)
                        ),
                        true
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3), new Position(2, 1)),
                        Arrays.array(new Piece(COLOR, PieceType.KNIGHT), new Piece(Color.BLACK, PieceType.PAWN)),
                        new Position(3, 3),
                        Arrays.array(new Position( 2, 1)),
                        true
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3), new Position(2, 1)),
                        Arrays.array(new Piece(COLOR, PieceType.KNIGHT), new Piece(Color.WHITE, PieceType.PAWN)),
                        new Position(3, 3),
                        Arrays.array(new Position( 2, 1)),
                        false
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3)),
                        Arrays.array(new Piece(Color.BLACK, PieceType.KNIGHT)),
                        new Position(3, 3),
                        Arrays.array(new Position( 2, 1)),
                        false
                ),
                Arguments.arguments(
                        new Position[0],
                        new Piece[0],
                        new Position(3, 3),
                        Arrays.array(new Position( 2, 1)),
                        false
                )
        );
    }
}