package com.example.chessgame.chess.pieces;

import com.example.chessgame.chess.PlayerColor;
import com.example.chessgame.chess.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class Rook extends LinearPiece {
    public Rook(PlayerColor pColor) {
        super(
                PieceType.ROOK,
                pColor,
                new ArrayList<>(Arrays.asList(
                        new Position(0, 1),
                        new Position(1, 0),
                        new Position(0, -1),
                        new Position(-1, 0)
                )),
                8
        );
    }
}
