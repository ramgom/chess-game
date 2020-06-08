package com.ramgom.chess.game.output;

import com.ramgom.chess.board.Board;
import com.ramgom.chess.players.PieceConverter;
import com.ramgom.chess.players.Player;

public interface GameOutput<T> {
    void showChecked();
    void showBoard(Board board);
    void showMessage(String message);
    void addConverter(Player player, PieceConverter<T> converter);
}
