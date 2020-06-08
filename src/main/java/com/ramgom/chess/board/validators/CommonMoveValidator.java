package com.ramgom.chess.board.validators;

import com.ramgom.chess.board.Board;
import com.ramgom.chess.board.Position;
import com.ramgom.chess.board.piece.Color;

abstract class CommonMoveValidator implements MoveValidator {

    protected boolean isToMoveValid(Board board, Color color, Position to) {
        return board.getPiece(to)
                .filter(toPiece -> toPiece.color() == color)
                .isEmpty();
    }

    protected boolean isFromValidColor(Board board, Color color, Position from) {
        return board.getPiece(from)
                .filter(fromPiece -> fromPiece.color() == color)
                .isPresent();
    }

    protected abstract boolean validatePath(Board board, Color color, Position from, Position to);

    @Override
    public boolean validate(Board board, Color color, Position from, Position to) {
        if (!isFromValidColor(board, color, from)) {
            return false;
        }

        if (!isToMoveValid(board, color, to)) {
            return false;
        }

        return validatePath(board, color, from, to);
    }

    protected boolean isValidDelta(int delta, int min, int max) {
        return delta >= min && delta <= max;
    }

    protected boolean isDiagonal(int deltaRow, int deltaColumn) {
        return deltaRow == deltaColumn;
    }

    protected boolean isVerticalOrHorizontal(int deltaRow, int deltaColumn) {
        return (deltaRow == 0 && deltaColumn > 0) || (deltaRow > 0 && deltaColumn == 0);
    }

    protected boolean validateEmptyPath(Board board, Position from, Position to) {
        int rowStep = Integer.signum(to.row() - from.row());
        int columnStep = Integer.signum(to.column() - from.column());

        int row = from.row() + rowStep;
        int column = from.column() + columnStep;

        while (row != to.row() || column != to.column()) {
            if (board.getPiece(row, column).isPresent()) {
                return false;
            }

            row += rowStep;
            column += columnStep;
        }

        return true;
    }

    protected int calculateDelta(int left, int right) {
        return Math.abs(left - right);
    }
}
