package com.hexagondev.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView status;
    Button[] buttons = new Button[9];
    public static int counter = 0;
    public boolean gameActive = true;
    public boolean isX = Math.random() < 0.5;
    public int[] cells = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6},
    };

    public void resetGame()
    {
        for (Button button : buttons) {
            button.setEnabled(true);
            button.setText("");
            button.setBackgroundColor(0xFF494949);
        }
        gameActive = true;
        cells = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        isX = Math.random() < 0.5;
        status.setText(isX ? "X player:" : "O player:");
        counter = 0;
    }

    @SuppressLint({"SetTextI18n"})
    public void checkCells() {
        // Check if any player has won
        for (int[] win : winPositions) {
            if (cells[win[0]] == cells[win[1]] && cells[win[1]] == cells[win[2]] && cells[win[0]] != 2) {
                gameActive = false;

                buttons[win[0]].setBackgroundColor(0xFF80ACF2);
                buttons[win[1]].setBackgroundColor(0xFF80ACF2);
                buttons[win[2]].setBackgroundColor(0xFF80ACF2);

                status.setText(cells[win[0]] == 0 ? "O has won" : "X has won");
            }
        }

        if (counter == 9 && gameActive) {
            status.setText("Match Draw");
            gameActive = false;
        }

        if (!gameActive) {
            for (Button button : buttons) {
                button.setEnabled(false);
            }
        }
    }

    public void addCellClicker(Button button, int num) {
        buttons[num] = button;
        button.setOnClickListener(v -> {
            if (!gameActive || button.getText() != "") {
                return;
            }

            counter++;
            button.setText(isX ? "X" : "O");
            isX = !isX;
            cells[num] = isX ? 0 : 1;
            status.setText(isX ? "X player:" : "O player:");
            checkCells();
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = findViewById(R.id.status);
        status.setText(isX ? "X player:" : "O player:");
        
        addCellClicker(findViewById(R.id.button1), 0);
        addCellClicker(findViewById(R.id.button2), 1);
        addCellClicker(findViewById(R.id.button3), 2);
        addCellClicker(findViewById(R.id.button4), 3);
        addCellClicker(findViewById(R.id.button5), 4);
        addCellClicker(findViewById(R.id.button6), 5);
        addCellClicker(findViewById(R.id.button7), 6);
        addCellClicker(findViewById(R.id.button8), 7);
        addCellClicker(findViewById(R.id.button9), 8);

        findViewById(R.id.reset).setOnClickListener(v -> resetGame());
    }
}