package com.ramgom.chess.board;

import com.ramgom.chess.board.piece.Piece;

import java.util.Optional;

public class Board {

    private final Piece[][] pieces = new Piece[8][8];

    public void removePiece(Position position) {
        removePiece(position.row(), position.column());
    }

    public Optional<Piece> removePiece(int row, int column) {
        Piece piece = pieces[row][column];
        pieces[row][column] = null;

        return Optional.ofNullable(piece);
    }

    public Optional<Piece> getPiece(Position position) {
        return getPiece(position.row(), position.column());
    }
    public Optional<Piece> getPiece(int row, int column) {
        return Optional.ofNullable(pieces[row][column]);
    }

    public void setPiece(Piece piece, Position position) {
        setPiece(piece, position.row(), position.column());
    }

    public void setPiece(Piece piece, int row, int column) {
        pieces[row][column] = piece;
    }
}
