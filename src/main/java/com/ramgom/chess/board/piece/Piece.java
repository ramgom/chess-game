package com.ramgom.chess.board.piece;

public record Piece(Color color, PieceType type) {
    public String toShortStringValue() {
        return type.getShortStringValue();
    }
}
