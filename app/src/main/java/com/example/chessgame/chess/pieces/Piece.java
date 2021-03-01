package com.example.chessgame.chess.pieces;

import com.example.chessgame.chess.Conditions;
import com.example.chessgame.chess.PlayerColor;
import com.example.chessgame.chess.Position;

public abstract class Piece {

    public enum PieceType {
        PAWN,
        BISHOP,
        KNIGHT,
        ROOK,
        QUEEN,
        KING
    }

    protected PieceType _type;
    protected PlayerColor _player;

    public Piece(PieceType type, PlayerColor player) {
        _type = type;
        _player = player;
    }

    public PieceType getType() {
        return _type;
    }
    public PlayerColor getPlayerColor() {
        return _player;
    }

    public abstract Conditions canMoveTo(Position from, Position to);
}
