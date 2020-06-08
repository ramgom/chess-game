package com.ramgom.chess.board.validators;

import com.ramgom.chess.board.Board;
import com.ramgom.chess.board.Position;
import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.PieceType;

public interface MoveValidator {
    boolean validate(Board board, Color color, Position from, Position to);
    PieceType getType();
}
