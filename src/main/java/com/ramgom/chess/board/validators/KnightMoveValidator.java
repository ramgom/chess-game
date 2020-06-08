package com.ramgom.chess.board.validators;

import com.ramgom.chess.board.Board;
import com.ramgom.chess.board.Position;
import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.PieceType;
import org.springframework.stereotype.Component;

@Component
class KnightMoveValidator extends CommonMoveValidator {

    @Override
    protected boolean validatePath(Board board, Color color, Position from, Position to) {
        int deltaRow = calculateDelta(to.row(), from.row());
        int deltaColumn = calculateDelta(to.column(), from.column());

        return (deltaRow == 1 && deltaColumn == 2) || (deltaRow == 2 && deltaColumn == 1);
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
