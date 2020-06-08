package com.ramgom.chess.board.validators;

import com.ramgom.chess.board.Board;
import com.ramgom.chess.board.Position;
import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.Piece;
import com.ramgom.chess.board.piece.PieceType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PawnMoveValidator extends CommonMoveValidator {

    @Override
    protected boolean validatePath(Board board, Color color, Position from, Position to){
        int deltaColumn = Math.abs(to.column() - from.column());
        int deltaRow = Math.abs(to.row() - from.row());

        if (!isMovingForward(color, from, to)) {
            return false;
        }

        if (isDiagonal(deltaRow, deltaColumn)) {
            Optional<Piece> toPiece = board.getPiece(to);
            return isValidDelta(deltaRow, 1, 1) && toPiece.isPresent() && toPiece.get().color() != color;
        }

        if (isVerticalValid(from, deltaColumn, deltaRow)) {
            return validateEmptyPath(board, from, to);
        }

        return false;
    }

    protected boolean isMovingForward(Color color, Position from, Position to) {
        return (color == Color.BLACK && to.row() > from.row()) || (color == Color.WHITE && to.row() < from.row());
    }

    protected boolean isVerticalValid(Position from, int deltaColumn, int deltaRow) {
        return isStartingPoint(from) ?
                deltaColumn == 0 && isValidDelta(deltaRow, 1, 2) :
                deltaColumn == 0 && deltaRow == 1;
    }

    protected boolean isStartingPoint(Position from) {
        return from.row() == 1 || from.row() == 6;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
