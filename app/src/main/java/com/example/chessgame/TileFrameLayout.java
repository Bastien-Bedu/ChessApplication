package com.example.chessgame;

import android.content.Context;

class TileFrameLayout extends android.widget.FrameLayout {
    public TileFrameLayout(Context context) {
        super(context);
    }

    //the position of the plane head.
    int xPos,yPos;

    public void setPosInGrid(int x,int y) {
        xPos=x;yPos=y;
    }

    public int[] getPosInGrid() {
        int[] pos=new int[2];
        pos[0]=xPos;
        pos[1]=yPos;
        return pos;
    }
}
