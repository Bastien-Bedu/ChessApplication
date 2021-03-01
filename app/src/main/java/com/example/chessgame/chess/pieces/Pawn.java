package com.example.chessgame.chess.pieces;

import com.example.chessgame.chess.Conditions;
import com.example.chessgame.chess.PlayerColor;
import com.example.chessgame.chess.Position;

public class Pawn extends Piece {
    public Pawn(PlayerColor pColor) {
        super(PieceType.PAWN, pColor);
    }

    @Override
    public Conditions canMoveTo(Position from, Position to) {
        return new Conditions(false);
    }
}
