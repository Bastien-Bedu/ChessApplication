package com.example.chessgame.chess;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();

    public ChessBoard() {
        ArrayList<String> chessBoard = new ArrayList<>();
        chessBoard.add("rnbqkbnr");
        chessBoard.add("pppppppp");
        chessBoard.add("        ");
        chessBoard.add("        ");
        chessBoard.add("        ");
        chessBoard.add("        ");
        chessBoard.add("PPPPPPPP");
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
}
