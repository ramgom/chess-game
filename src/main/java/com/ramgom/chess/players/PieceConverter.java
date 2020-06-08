package com.ramgom.chess.players;

import com.ramgom.chess.board.piece.Piece;

@FunctionalInterface
public interface PieceConverter<T> {

    T convert(Piece piece);
}
