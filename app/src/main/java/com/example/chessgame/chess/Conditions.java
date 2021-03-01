package com.example.chessgame.chess;

import com.example.chessgame.chess.pieces.Piece;

import java.util.Stack;

public class Conditions {
    public enum Type {
        BLACK,
        WHITE,
        BLACK_OR_EMPTY,
        WHITE_OR_EMPTY,
        EMPTY
    }
    private boolean _doable;
    private Stack<Position> _positions;
    private Stack<Type> _types;

    public Conditions(boolean doable) {
        _doable = doable;
        _positions = new Stack<>();
        _types = new Stack<>();
    }

    public void addCondition(Position p, Type t) {
        _positions.push(p);
        _types.push(t);
    }
    public boolean isDoable() {
        return _doable;
    }

    public boolean isCompleted() {
        return _positions.empty();
    }

    public Position popNextPosition() { return _positions.pop(); }
    public Type popNextType() { return _types.pop(); }

    public boolean isConditionValid(Piece piece) {
        Type pieceType;
        if (piece == null)
            pieceType = Type.EMPTY;
        else if (piece.getPlayerColor() == PlayerColor.BLACK)
            pieceType = Type.BLACK;
        else
            pieceType = Type.WHITE;
        switch (popNextType()) {
            case BLACK:
                return (pieceType == Type.BLACK);
            case WHITE:
                return (pieceType == Type.WHITE);
            case BLACK_OR_EMPTY:
                return (pieceType == Type.BLACK || pieceType == Type.EMPTY);
            case WHITE_OR_EMPTY:
                return (pieceType == Type.WHITE || pieceType == Type.EMPTY);
            case EMPTY:
                return (pieceType == Type.EMPTY);
            default:
                return false;
        }

    }
}
