package com.example.chessgame.chess;

import androidx.annotation.DrawableRes;

import com.example.chessgame.R;
import com.example.chessgame.chess.pieces.Piece;

import java.util.EnumMap;

public class Implementation {
    public static EnumMap<Piece.PieceType, Integer> typeToAsset = new EnumMap(Piece.PieceType.class) {{
        put(Piece.PieceType.PAWN, R.drawable.pawn);
        put(Piece.PieceType.BISHOP, R.drawable.bishop);
        put(Piece.PieceType.KNIGHT, R.drawable.knight);
        put(Piece.PieceType.ROOK, R.drawable.rook);
        put(Piece.PieceType.QUEEN, R.drawable.queen);
        put(Piece.PieceType.KING, R.drawable.king);
    }};
}
