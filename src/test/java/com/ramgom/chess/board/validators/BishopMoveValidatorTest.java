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

class BishopMoveValidatorTest {

    private final MoveValidator bishopMoveValidator = new BishopMoveValidator();

    @ParameterizedTest
    @MethodSource("validateParams")
    public void validate(Position[] initialPositions, Piece[] pieces, Position from, Position[] targets, boolean result) {
        CommonMoveValidatorUtils.validate(
                bishopMoveValidator,
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
                        Arrays.array(new Piece(COLOR, PieceType.BISHOP)),
                        new Position(3, 3),
                        Arrays.array(
                                new Position(5,1),
                                new Position(6, 6),
                                new Position(1, 5),
                                new Position(1, 1)
                        ),
                        true),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3), new Position(5, 1)),
                        Arrays.array(new Piece(COLOR, PieceType.BISHOP), new Piece(Color.BLACK, PieceType.PAWN)),
                        new Position(3, 3),
                        Arrays.array(new Position(5,1)),
                        true
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3)),
                        Arrays.array(new Piece(COLOR, PieceType.BISHOP)),
                        new Position(3, 3),
                        Arrays.array(
                                new Position(1,3),
                                new Position(6, 3),
                                new Position(3, 0),
                                new Position(3, 6)
                        ),
                        false),
                Arguments.arguments(
                        new Position[0],
                        new Piece[0],
                        new Position(3, 3),
                        Arrays.array(new Position(5,1)),
                        false
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3)),
                        Arrays.array(new Piece(Color.BLACK, PieceType.BISHOP)),
                        new Position(3, 3),
                        Arrays.array(new Position(5,1)),
                        false
                ),
                Arguments.arguments(
                        Arrays.array(new Position(3, 3), new Position(5, 1)),
                        Arrays.array(new Piece(COLOR, PieceType.BISHOP), new Piece(Color.WHITE, PieceType.PAWN)),
                        new Position(3, 3),
                        Arrays.array(new Position(5,1)),
                        false
                )
        );
    }
}