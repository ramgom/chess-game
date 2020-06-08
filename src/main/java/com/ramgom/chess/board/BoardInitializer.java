package com.ramgom.chess.board;

import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.board.piece.Piece;
import com.ramgom.chess.board.piece.PieceType;
import org.springframework.stereotype.Component;

@Component
public class BoardInitializer {

    public void init(Board board) {
        addKings(board);
        addQueens(board);
        addBishops(board);
        addKnights(board);
        addRooks(board);
        addPawns(board);
    }

    private void addRooks(Board board) {
        board.setPiece( new Piece(Color.BLACK, PieceType.ROOK), 0, 0);
        board.setPiece( new Piece(Color.BLACK, PieceType.ROOK),0, 7);

        board.setPiece( new Piece(Color.WHITE, PieceType.ROOK),7, 0);
        board.setPiece( new Piece(Color.WHITE, PieceType.ROOK),7, 7);
    }

    private void addKnights(Board board) {

        board.setPiece( new Piece(Color.BLACK, PieceType.KNIGHT),0, 1);
        board.setPiece( new Piece(Color.BLACK, PieceType.KNIGHT),0, 6);

        board.setPiece( new Piece(Color.WHITE, PieceType.KNIGHT), 7, 1);
        board.setPiece( new Piece(Color.WHITE, PieceType.KNIGHT),7, 6);
    }

    private void addBishops(Board board) {
        board.setPiece( new Piece(Color.BLACK, PieceType.BISHOP),0, 2);
        board.setPiece( new Piece(Color.BLACK, PieceType.BISHOP),0, 5);

        board.setPiece( new Piece(Color.WHITE, PieceType.BISHOP),7, 2);
        board.setPiece( new Piece(Color.WHITE, PieceType.BISHOP), 7, 5);
    }

    private void addQueens(Board board) {
        board.setPiece( new Piece(Color.BLACK, PieceType.QUEEN),0, 3);

        board.setPiece( new Piece(Color.WHITE, PieceType.QUEEN),7, 3);
    }

    private void addKings(Board board) {
        board.setPiece( new Piece(Color.BLACK, PieceType.KING),0, 4);

        board.setPiece( new Piece(Color.WHITE, PieceType.KING),7, 4);
    }

    private void addPawns(Board board) {

        for (int i = 0; i < 8; i++) {
            board.setPiece( new Piece(Color.BLACK, PieceType.PAWN),1, i);
            board.setPiece( new Piece(Color.WHITE, PieceType.PAWN), 6, i);
        }
    }
}
