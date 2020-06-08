package com.ramgom.chess.board.validators;

import com.ramgom.chess.board.Board;
import com.ramgom.chess.board.Position;
import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.PieceType;
import org.springframework.stereotype.Component;

@Component
class KingMoveValidator extends CommonMoveValidator {
    @Override
    protected boolean validatePath(Board board, Color color, Position from, Position to) {
        int deltaRow = calculateDelta(to.row(), from.row());
        int deltaColumn = calculateDelta(to.column(), from.column());

        return isValidDelta(deltaRow, 0, 1) && isValidDelta(deltaColumn, 0, 1);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
