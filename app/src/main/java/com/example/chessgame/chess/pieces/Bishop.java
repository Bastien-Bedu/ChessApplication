package com.example.chessgame.chess.pieces;

import com.example.chessgame.chess.PlayerColor;
import com.example.chessgame.chess.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop extends LinearPiece {
    public Bishop(PlayerColor pColor) {
        super(PieceType.BISHOP, pColor,
                new ArrayList<>(Arrays.asList(
                        new Position(1, 1), new Position(1, -1),
                        new Position(-1, -1), new Position(-1, 1)
                )),
                8);
    }
}
