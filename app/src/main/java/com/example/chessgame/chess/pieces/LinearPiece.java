package com.example.chessgame.chess.pieces;

import android.util.Log;

import com.example.chessgame.chess.Conditions;
import com.example.chessgame.chess.PlayerColor;
import com.example.chessgame.chess.Position;

import java.util.ArrayList;

public class LinearPiece extends Piece {
    private ArrayList<Position> _directions;
    private int _distance;

    public LinearPiece(PieceType type, PlayerColor player, ArrayList<Position> directions, int distance) {
        super(type, player);
        _distance = distance;
        _directions = directions;
    }

    @Override
    public Conditions canMoveTo(Position from, Position to) {
        Position delta = to.sub(from);
        Log.println(Log.WARN, "e", "col: " + delta.getCol() + " row: " + delta.getRow());
        boolean isMultiple = false;
        int ratio = 0;
        int i = 0;
        while (!isMultiple && i < _directions.size()) {
            Position e = _directions.get(i);
            Log.println(Log.WARN, "direction", "col: " + e.getCol() + " row: " + e.getRow());
            if (delta.getCol() == 0 && delta.getRow() == 0) {
                if (e.getRow() == 0 && e.getCol() == 0)
                    isMultiple = true;
            } else if (delta.getRow() == 0) {
                 if (e.getRow() == 0 && e.getCol() != 0) {
                     ratio = delta.getCol() / e.getCol();
                     isMultiple = ratio > 0 && ratio <= _distance;
                 }
            } else if (delta.getCol() == 0) {
                if (e.getCol() == 0 && e.getRow() != 0) {
                    ratio = delta.getRow() / e.getRow();
                    isMultiple = ratio > 0 && ratio <= _distance;
                }
            } else {
                if (e.getRow() != 0 && e.getCol() != 0) {
                    int ratioRow = (int)(delta.getRowDouble() / e.getRowDouble() * 100);
                    int ratioCol = (int)(delta.getColDouble() / e.getColDouble() * 100);
                    ratio = ratioCol / 100;
                    isMultiple = ratioCol == ratioRow && ratio > 0 && ratio <= _distance;
                }
            }
            if (!isMultiple)
                i++;
        }
        Log.println(Log.WARN, "e", "ratio: " + ratio + " i: " + i);

        Conditions conditions = new Conditions(isMultiple);
        if (isMultiple) {
            Log.println(Log.WARN, "e", "move ok if empty between");
            for (int ratioIdx = 1; ratioIdx < ratio; ratioIdx++) {
                conditions.addCondition(from.add(_directions.get(i).mul(ratioIdx)), Conditions.Type.EMPTY);
                Log.println(Log.WARN, "e", "move ok if empty between");
            }
            if (this.getPlayerColor() == PlayerColor.WHITE)
                conditions.addCondition(to, Conditions.Type.BLACK_OR_EMPTY);
            else
                conditions.addCondition(to, Conditions.Type.WHITE_OR_EMPTY);
        }
        return conditions;
    }
}
