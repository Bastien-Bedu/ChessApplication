package com.example.chessgame.chess;

import java.util.Objects;

public class Position {
    private final int _col;
    private final int _row;

    public Position(int row, int col) {
        this._col = col;
        this._row = row;
    }

    public int getCol() { return _col; }
    public double getColDouble() { return _col; }
    public int getRow() { return _row; }
    public double getRowDouble() { return _row; }

    public Position add(Position other){
        return new Position(this._row + other._row, this._col + other._col);
    }

    public Position sub(Position other){
        return new Position(this._row - other._row, this._col - other._col);
    }

    public Position inv(){
        return new Position(-1 * this._row, -1 * this._col);
    }

    public Position mul(int nb) { return new Position(nb * this._row, nb * this._col);}

    /*public boolean isColinearScaled(Position other) {
        return false;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return _col == position._col &&
                _row == position._row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_col, _row);
    }
}
