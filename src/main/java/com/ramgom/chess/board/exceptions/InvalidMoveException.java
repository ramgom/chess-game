package com.ramgom.chess.board.exceptions;

import com.ramgom.chess.board.piece.PieceType;
import com.ramgom.chess.board.Position;

public class InvalidMoveException extends Exception {

    private static final String MESSAGE = "Invalid move for %s from %s to %s";

    public InvalidMoveException(String message) {
        super(message);
    }

    public InvalidMoveException(PieceType type, Position from, Position to) {
        super(String.format(MESSAGE, type, from, to));
    }
}
