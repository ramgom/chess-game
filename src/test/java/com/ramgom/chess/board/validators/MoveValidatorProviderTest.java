package com.ramgom.chess.board.validators;

import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.Piece;
import com.ramgom.chess.board.piece.PieceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MoveValidatorProviderTest {

    private static final Map<PieceType, Class<? extends MoveValidator>> expectedValues =
            Map.of(
                    PieceType.ROOK, RookMoveValidator.class,
                    PieceType.BISHOP, BishopMoveValidator.class,
                    PieceType.KING, KingMoveValidator.class,
                    PieceType.KNIGHT, KnightMoveValidator.class,
                    PieceType.PAWN, PawnMoveValidator.class,
                    PieceType.QUEEN, QueenMoveValidator.class
            );

    @Autowired
    private MoveValidatorProvider provider;

    @Test
    public void moveValidator() {
        expectedValues.forEach(
                (type, validatorClass) -> {
                    MoveValidator moveValidator = provider.moveValidator(new Piece(Color.WHITE, type));

                    assertThat(moveValidator).isInstanceOf(validatorClass);
                });
    }
}