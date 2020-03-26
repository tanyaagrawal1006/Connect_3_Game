package com.example.gameconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean red = true;
    int clicks = 0;
    //1:red, 2:yellow, 0:empty
    int[][] arr = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

    public void check() {
        if (clicks < 5)
            return;
        int color = 0;
        if (arr[0][0] == arr[0][1] && arr[0][0] == arr[0][2])
            color = arr[0][0];
        else {
            if (arr[1][0] == arr[1][1] && arr[1][0] == arr[1][2])
                color = arr[1][0];
            else {
                if (arr[2][0] == arr[2][1] && arr[2][0] == arr[2][2])
                    color = arr[2][0];
                else {
                    if (arr[0][0] == arr[1][0] && arr[0][0] == arr[2][0])
                        color = arr[0][0];
                    else {
                        if (arr[0][1] == arr[1][1] && arr[0][1] == arr[2][1])
                            color = arr[0][1];
                        else {
                            if (arr[0][2] == arr[1][2] && arr[0][2] == arr[2][2])
                                color = arr[0][2];
                            else {
                                if (arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2])
                                    color = arr[0][0];
                                else if (arr[0][2] == arr[1][1] && arr[0][2] == arr[2][0])
                                    color = arr[0][2];
                            }
                        }
                    }
                }
            }
        }
        winner(color);
    }

    public void playAgain(View view) {
        red = true;
        clicks = 0;
        LinearLayout wiin = findViewById(R.id.winner);
        wiin.setVisibility(View.INVISIBLE);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                arr[i][j] = 0;

        GridLayout grid = findViewById(R.id.grid);
        for (int i = 0; i < grid.getChildCount(); i++) {
            ImageView child = (ImageView) grid.getChildAt(i);
            child.setImageDrawable(null);
            child.setClickable(true);
        }

    }

    public void winner(int color) {
        if(color==0 && clicks!=9)
            return;
        TextView win = findViewById(R.id.textWinner);
        if (color == 1)
            win.setText("Red has won!");
        else {
            if (color == 2)
                win.setText("Yellow has won!");
            else if (clicks == 9)
                win.setText("Draw!");
        }
        LinearLayout wiin = findViewById(R.id.winner);
        wiin.setVisibility(View.VISIBLE);

        GridLayout grid = findViewById(R.id.grid);
        for (int i = 0; i < grid.getChildCount(); i++) {
            ImageView child = (ImageView) grid.getChildAt(i);
            child.setClickable(false);

        }
    }

    public void isClicked(View view) {
        String tag = view.getTag().toString();
        int i = Integer.parseInt(tag.split(",")[0]);
        int j = Integer.parseInt(tag.split(",")[1]);
        if (arr[i][j] != 0)
            return;
        ImageView token = (ImageView) view;
        token.setTranslationY(-1000);
        if (red) {
            token.setImageResource(R.drawable.red);
            red = false;
            arr[i][j] = 1;
        } else {
            token.setImageResource(R.drawable.yellow);
            red = true;
            arr[i][j] = 2;
        }
        token.animate().translationY(0).setDuration(500);
        clicks++;
        check();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
