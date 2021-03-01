package com.example.chessgame.chess.pieces;

import com.example.chessgame.chess.Conditions;
import com.example.chessgame.chess.PlayerColor;
import com.example.chessgame.chess.Position;

public class Knight extends Piece {
    public Knight(PlayerColor pColor) {
        super(PieceType.KNIGHT, pColor);
    }

    @Override
    public Conditions canMoveTo(Position from, Position to) {
        Position delta = to.sub(from);

        Conditions conditions = new Conditions(Math.abs(delta.getRow()) + Math.abs(delta.getCol()) == 3 && delta.getCol() != 0 && delta.getRow() != 0);
        if (this.getPlayerColor() == PlayerColor.WHITE)
            conditions.addCondition(to, Conditions.Type.BLACK_OR_EMPTY);
        else
            conditions.addCondition(to, Conditions.Type.WHITE_OR_EMPTY);
        return conditions;
    }
}
