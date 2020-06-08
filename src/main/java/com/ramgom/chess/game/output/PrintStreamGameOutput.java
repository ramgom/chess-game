package com.ramgom.chess.game.output;

import com.ramgom.chess.board.Board;
import com.ramgom.chess.board.piece.Color;
import com.ramgom.chess.players.PieceConverter;
import com.ramgom.chess.players.Player;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
class PrintStreamGameOutput implements GameOutput<String> {
    private static final String CHECKED = "In check!";
    private static final String TILE_SEPARATOR = "|";
    private static final String EMPTY_TILE = "   ";
    private static final String FULL_TILE = " %s ";

    private final PrintStream printStream;
    private final Map<Color, PieceConverter<String>> converters = new HashMap<>();

    @Override
    public void showChecked() {
        printStream.println(CHECKED);
    }

    public void addConverter(Player player, PieceConverter<String> converter) {
        converters.put(player.color(), converter);
    }

    @Override
    public void showBoard(Board board) {
        for (int row = 0; row < 8; row++) {
            printStream.print(8 - row);
            printStream.print(TILE_SEPARATOR);
            for (int column = 0; column  < 8; column++) {
                String tile = board.getPiece(row, column)
                        .map(piece -> converters.get(piece.color()).convert(piece))
                        .map(pieceStringValue -> String.format(FULL_TILE, pieceStringValue))
                        .orElse(EMPTY_TILE);

                printStream.print(tile);
                printStream.print(TILE_SEPARATOR);
            }
            printStream.println();
        }

        printStream.println("   a   b   c   d   e   f   g   h");
    }

    @Override
    public void showMessage(String message) {
        printStream.println(message);
    }
}
