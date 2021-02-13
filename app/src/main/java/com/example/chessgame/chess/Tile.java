package com.example.chessgame.chess;

import com.example.chessgame.R;
import com.example.chessgame.chess.pieces.Bishop;
import com.example.chessgame.chess.pieces.King;
import com.example.chessgame.chess.pieces.Knight;
import com.example.chessgame.chess.pieces.Pawn;
import com.example.chessgame.chess.pieces.Piece;
import com.example.chessgame.chess.pieces.Queen;
import com.example.chessgame.chess.pieces.Rook;

import static com.example.chessgame.chess.PlayerColor.BLACK;
import static com.example.chessgame.chess.PlayerColor.WHITE;

public class Tile {
    Piece piece = null;

    public Tile(char c) {
        switch (c) {
            case 'P':
                piece = new Pawn(WHITE);
                break;
            case 'p':
                piece = new Pawn(BLACK);
                break;
            case 'N':
                piece = new Knight(WHITE);
                break;
            case 'n':
                piece = new Knight(BLACK);
                break;
            case 'b':
                piece = new Bishop(BLACK);
                break;
            case 'B':
                piece = new Bishop(WHITE);
                break;
            case 'r':
                piece = new Rook(BLACK);
                break;
            case 'R':
                piece = new Rook(WHITE);
                break;
            case 'q':
                piece = new Queen(BLACK);
                break;
            case 'Q':
                piece = new Queen(WHITE);
                break;
            case 'k':
                piece = new King(BLACK);
                break;
            case 'K':
                piece = new King(WHITE);
                break;
            default:
                piece = null;
        }
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public Piece.PieceType getPieceType() {
        return piece.getType();
    }

    public PlayerColor getPlayerColor() {
        return piece.getPlayerColor();
    }

}
