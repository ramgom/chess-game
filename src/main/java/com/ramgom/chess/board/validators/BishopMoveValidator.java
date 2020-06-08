package com.ramgom.chess.board.validators;

import com.ramgom.chess.board.Board;
import com.ramgom.chess.board.Position;
import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.PieceType;
import org.springframework.stereotype.Component;

@Component
class BishopMoveValidator extends CommonMoveValidator {

    @Override
    protected boolean validatePath(Board board, Color color, Position from, Position to) {
        int deltaRow = calculateDelta(to.row(), from.row());
        int deltaColumn = calculateDelta(to.column(), from.column());

        if (isDiagonal(deltaRow, deltaColumn)) {
            return validateEmptyPath(board, from, to);
        }

        return false;
    }



    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
