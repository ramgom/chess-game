package com.ramgom.chess.board;

import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.Piece;
import com.ramgom.chess.board.piece.PieceType;
import com.ramgom.chess.board.validators.MoveValidatorProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckValidator {

    private final MoveValidatorProvider moveValidatorProvider;

    public boolean inCheckValidator(Board board, Color color) {

        List<Tile> tiles = new ArrayList<>();
        Position opponentKingPosition = null;

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Position position = new Position(row, column);
                Optional<Piece> boardPiece = board.getPiece(row, column);
                if (boardPiece.isEmpty()) {
                    continue;
                }

                Piece piece = boardPiece.get();

                if (piece.color() != color) {
                    tiles.add(new Tile(piece, position));
                } else if (piece.type() == PieceType.KING) {
                    opponentKingPosition = position;
                }
            }
        }

        return inCheck(board, tiles, opponentKingPosition);
    }

    protected boolean inCheck(Board board, List<Tile> pieces, Position opponentKingPosition) {
        return pieces.stream()
                .anyMatch(tile -> validateMove(board, tile, opponentKingPosition));
    }

    protected boolean validateMove(Board board, Tile tile, Position kingPosition) {
        return moveValidatorProvider.moveValidator(tile.piece)
                .validate(board, tile.piece.color(), tile.position, kingPosition);
    }

    static record Tile(Piece piece, Position position) {}
}
