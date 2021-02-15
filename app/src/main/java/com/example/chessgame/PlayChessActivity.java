package com.example.chessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.chessgame.chess.ChessBoard;
import com.example.chessgame.chess.Implementation;
import com.example.chessgame.chess.PlayerColor;
import com.example.chessgame.chess.Tile;

import java.util.ArrayList;

public class PlayChessActivity extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener {

    private float xCoordinate, yCoordinate;

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
                frameLayout.setOnDragListener(this);
                frameLayout.setClipChildren(false);

                if (!tile.isEmpty()) {
                    ImageView imageView = new ImageView(this);
                    imageView.setOnTouchListener(this);
                    imageView.setImageResource(Implementation.typeToAsset.get(tile.getPieceType()));
                    if (tile.getPlayerColor() == PlayerColor.BLACK) {
                        //imageView.setColorFilter(getResources().getColor(R.color.black));
                        float[] negative = {
                                -1.0f, 0, 0, 0, 255, // red
                                0, -1.0f, 0, 0, 255, // green
                                0, 0, -1.0f, 0, 255, // blue
                                0, 0, 0, 1.0f, 0  // alpha
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

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                    view);
            view.setVisibility(View.INVISIBLE);
            view.startDragAndDrop(data, shadowBuilder, view, 0);
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            view.performClick();
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean onDrag(View v, DragEvent event) {

        // Store the action type for the incoming event
        int action = event.getAction();

        // Handles each of the expected events
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                return true;

            case DragEvent.ACTION_DRAG_ENTERED:

                // Apply a gray tint to the View
                v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);

                // Invalidate the view to force a redraw in the new tint
                v.invalidate();

                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                // Ignore the event
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                // Re-sets the color tint to yellow
                v.getBackground().clearColorFilter();

                // Invalidate the view to force a redraw in the new tint
                v.invalidate();

                return true;

            case DragEvent.ACTION_DROP:
                v.getBackground().clearColorFilter();

                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                FrameLayout container = (FrameLayout) v;
                container.addView(view);
                view.setVisibility(View.VISIBLE);

                v.invalidate();

                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                return true;

            default:
                break;
        }

        return false;
    }

}