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
import com.example.chessgame.chess.Position;
import com.example.chessgame.chess.Tile;

import java.util.ArrayList;

public class PlayChessActivity extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener {
    private final ChessBoard chessBoard = new ChessBoard();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_board);

        ArrayList<ArrayList<Tile>> tiles = chessBoard.getTiles();

        GridLayout chessBoardLayout = findViewById(R.id.chessBoard);

        createTilesLayout(tiles, chessBoardLayout);

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
            view.setVisibility(View.VISIBLE);
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
        View view;
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
                TileFrameLayout tileLayout = (TileFrameLayout) v;
                view = (View) event.getLocalState();
                TileFrameLayout parentTileLayout = (TileFrameLayout) view.getParent();

                if (chessBoard.canMove(new Position(parentTileLayout.yPos, parentTileLayout.xPos), new Position(tileLayout.yPos, tileLayout.xPos))) {
                    parentTileLayout.removeView(view);
                    tileLayout.removeAllViewsInLayout();
                    tileLayout.addView(view);
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.VISIBLE);
                }

                v.invalidate();

                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                if (!event.getResult()) {
                    view = (View) event.getLocalState();
                    view.setVisibility(View.VISIBLE);
                }

                return true;

            default:
                break;
        }

        return false;
    }

    private void createTilesLayout(ArrayList<ArrayList<Tile>> tiles, GridLayout chessBoardLayout) {
        boolean black = false;

        for (int rowNb = 0; rowNb < tiles.size(); rowNb++) {
            for (int colNb = 0; colNb < tiles.size(); colNb++) {
                Tile tile = tiles.get(rowNb).get(colNb);
                RelativeLayout.LayoutParams lpImage = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                lpImage.addRule(RelativeLayout.CENTER_IN_PARENT);

                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f));
                layoutParams.height = 0;
                layoutParams.width = 0;

                TileFrameLayout frameLayout = new TileFrameLayout(this);

                frameLayout.setBackgroundColor(getResources().getColor(
                        black ? R.color.blackTile : R.color.whiteTile
                ));
                frameLayout.setLayoutParams(layoutParams);
                frameLayout.setOnDragListener(this);
                frameLayout.setClipChildren(false);
                frameLayout.setPosInGrid(colNb, rowNb);

                if (!tile.isEmpty()) {
                    ImageView imageView = new ImageView(this);
                    imageView.setOnTouchListener(this);
                    imageView.setImageResource(Implementation.typeToAsset.get(tile.getPieceType()));
                    if (tile.getPlayerColor() == PlayerColor.BLACK) {
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


}