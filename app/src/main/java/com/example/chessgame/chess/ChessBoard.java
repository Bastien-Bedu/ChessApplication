package com.example.chessgame.chess;

import android.util.Log;

import java.util.ArrayList;

public class ChessBoard {
    private ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();

    public ChessBoard() {
        ArrayList<String> chessBoard = new ArrayList<>();
        chessBoard.add("rnbqkbnr");
        chessBoard.add("        ");
        chessBoard.add("        ");
        chessBoard.add("        ");
        chessBoard.add("        ");
        chessBoard.add("        ");
        chessBoard.add("        ");
        chessBoard.add("RNBQKBNR");

        for (int i = 0; i < chessBoard.size(); i++) {
            tiles.add(new ArrayList<>());
            for (char elem : chessBoard.get(i).toCharArray()) {
                tiles.get(i).add(new Tile(elem));
            }
        }
    }

    public ArrayList<ArrayList<Tile>> getTiles() {
        return tiles;
    }

    public Tile getTileAt(Position pos) {
        return getTiles().get(pos.getRow()).get(pos.getCol());
    }

    public boolean canMove(Position from, Position to) {
        Tile toTile = getTileAt(to);
        Tile fromTile = getTileAt(from);

        if (fromTile.isEmpty()) {
            Log.e("ChessBoard::canMove", "fromTile is empty, it cannot move");
            return (false);
        } else {
            Conditions conditions = fromTile.piece.canMoveTo(from, to);
            if (conditions.isDoable()) {
                while (!conditions.isCompleted()) {
                    Position np = conditions.popNextPosition();
                    if (!conditions.isConditionValid(getTileAt(np).piece))
                        return (false);
                }
                // TODO check if any enemy piece can take own king (no check movement).
                toTile.piece = fromTile.piece;
                fromTile.piece = null;
                return (true);
            }
            return (false);
        }
    }
}
