package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.chessgame.chess.ChessBoard;
import com.example.chessgame.chess.Implementation;
import com.example.chessgame.chess.PlayerColor;
import com.example.chessgame.chess.Tile;

import java.util.ArrayList;

public class PlayChessActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_board);

        ChessBoard chessBoard = new ChessBoard();
        ArrayList<ArrayList<Tile>> tiles = chessBoard.getTiles();

        GridLayout chessBoardLayout = findViewById(R.id.chessBoard);


        boolean black = false;

        for (ArrayList<Tile> row : tiles) {
            for (Tile tile : row) {

                RelativeLayout.LayoutParams lpImage = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                lpImage.addRule(RelativeLayout.CENTER_IN_PARENT);

                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f));
                layoutParams.height = 0;
                layoutParams.width = 0;

                FrameLayout frameLayout = new FrameLayout(this);

                frameLayout.setBackgroundColor(getResources().getColor(
                        black ? R.color.blackTile : R.color.whiteTile
                ));
                frameLayout.setLayoutParams(layoutParams);

                if (!tile.isEmpty()) {
                    ImageView imageView = new ImageView(this);
                    imageView.setImageResource(Implementation.typeToAsset.get(tile.getPieceType()));
                    if (tile.getPlayerColor() == PlayerColor.BLACK) {
                        //imageView.setColorFilter(getResources().getColor(R.color.black));
                        float[] negative = {
                                -1.0f,     0,     0,    0, 255, // red
                                0, -1.0f,     0,    0, 255, // green
                                0,     0, -1.0f,    0, 255, // blue
                                0,     0,     0, 1.0f,   0  // alpha
                        };

                        imageView.setColorFilter(new ColorMatrixColorFilter(negative));

                    }

                    imageView.setLayoutParams(lpImage);
                    frameLayout.addView(imageView);
                }


                chessBoardLayout.addView(frameLayout);
                black = !black;
            }
            black = !black;
        }




    }
}