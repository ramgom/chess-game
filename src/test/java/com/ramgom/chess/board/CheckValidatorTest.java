package com.ramgom.chess.board;

import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.Piece;
import com.ramgom.chess.board.piece.PieceType;
import com.ramgom.chess.board.validators.MoveValidator;
import com.ramgom.chess.board.validators.MoveValidatorProvider;
import mockit.Expectations;
import mockit.FullVerifications;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CheckValidatorTest {

    private static final Color COLOR = Color.BLACK;

    @Tested
    private CheckValidator checkValidator;

    @Injectable
    private MoveValidatorProvider moveValidatorProvider;

    @Test
    public void validateMove(@Mocked MoveValidator validator) {

        Board board = new Board();
        Piece piece = new Piece(COLOR, PieceType.BISHOP);
        Position position = new Position(0, 0);
        Position kingPosition = new Position(1,1);

        new Expectations() {{
            piece.color(); result = COLOR;
            moveValidatorProvider.moveValidator(piece); result = validator;
            validator.validate(board, COLOR, position, kingPosition); result = true;
        }};

        boolean isValid = checkValidator.validateMove(board, new CheckValidator.Tile(piece, position), kingPosition);

        assertThat(isValid).isTrue();

        new FullVerifications() {{}};
    }

    @Test
    public void inCheck() {
        Board board = new Board();
        CheckValidator.Tile tile1 = new CheckValidator.Tile(new Piece(Color.BLACK, PieceType.BISHOP), new Position(0, 0));
        CheckValidator.Tile tile2 = new CheckValidator.Tile(new Piece(Color.BLACK, PieceType.PAWN), new Position(3, 3));
        Position kingPosition = new Position(1, 1);

        List<CheckValidator.Tile> tiles = Arrays.asList(tile1, tile2);

        new Expectations(checkValidator) {{
            checkValidator.validateMove(board, tile1, kingPosition); returns(false, false);
            checkValidator.validateMove(board, tile2, kingPosition); returns(false, true);
        }};

        boolean noInCheck = checkValidator.inCheck(board, tiles, kingPosition);
        boolean inCheck = checkValidator.inCheck(board, tiles, kingPosition);

        assertThat(noInCheck).isFalse();
        assertThat(inCheck).isTrue();

        new FullVerifications() {{}};
    }

    @Test
    public void inCheckValidator() {
        Board board = new Board();

        Piece whiteKing = new Piece(Color.WHITE, PieceType.KING);
        Position whiteKingPosition = new Position(0, 0);
        Piece blackPawn = new Piece(Color.BLACK, PieceType.PAWN);
        Position blackPawnPosition = new Position(1, 0);
        Piece blackKing = new Piece(Color.BLACK, PieceType.KING);
        Position blackKingPosition = new Position(2, 0);
        Piece whiteQueen = new Piece(Color.WHITE, PieceType.QUEEN);
        Position whiteQueenPosition = new Position(3, 0);

        board.setPiece(whiteKing, whiteKingPosition);
        board.setPiece(blackPawn, blackPawnPosition);
        board.setPiece(blackKing, blackKingPosition);
        board.setPiece(whiteQueen, whiteQueenPosition);

        new Expectations(checkValidator) {{
            checkValidator.inCheck(board, withAny(new ArrayList<>()), whiteKingPosition); result = false;
        }};

        boolean inCheck = checkValidator.inCheckValidator(board, Color.WHITE);

        assertThat(inCheck).isFalse();

        new FullVerifications() {{
            List<CheckValidator.Tile> tiles;

            checkValidator.inCheck(board, tiles = withCapture(), whiteKingPosition);

            assertThat(tiles).hasSize(2);
            assertThat(tiles).containsOnly(
                    new CheckValidator.Tile(blackPawn, blackPawnPosition),
                    new CheckValidator.Tile(blackKing, blackKingPosition)
            );
        }};
    }
}