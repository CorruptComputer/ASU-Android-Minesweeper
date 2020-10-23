package edu.angelo.midtermprojectgupton;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Minesweeper Android App.
 * @author Nickolas Gupton
 */
public class MainActivity extends AppCompatActivity {

    /**
     * MinesweeperTracker: gameBoard | The object of the current MinesweeperTracker.
     */
    private MinesweeperTracker gameBoard;

    /**
     * Button[][]: buttons | 2d array of the buttons on the game board.
     */
    private Button[][] buttons;

    /**
     * Essentially a "main" method. Gets called when the app opens.
     * @param savedInstanceState Bundle: Provided by Android.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameBoard = new MinesweeperTracker(8, 8);
        buttons = new Button[][]{
                {
                        findViewById(R.id.button00),
                        findViewById(R.id.button01),
                        findViewById(R.id.button02),
                        findViewById(R.id.button03),
                        findViewById(R.id.button04),
                        findViewById(R.id.button05),
                        findViewById(R.id.button06),
                        findViewById(R.id.button07)
                },
                {
                        findViewById(R.id.button10),
                        findViewById(R.id.button11),
                        findViewById(R.id.button12),
                        findViewById(R.id.button13),
                        findViewById(R.id.button14),
                        findViewById(R.id.button15),
                        findViewById(R.id.button16),
                        findViewById(R.id.button17)
                },
                {
                        findViewById(R.id.button20),
                        findViewById(R.id.button21),
                        findViewById(R.id.button22),
                        findViewById(R.id.button23),
                        findViewById(R.id.button24),
                        findViewById(R.id.button25),
                        findViewById(R.id.button26),
                        findViewById(R.id.button27)
                },
                {
                        findViewById(R.id.button30),
                        findViewById(R.id.button31),
                        findViewById(R.id.button32),
                        findViewById(R.id.button33),
                        findViewById(R.id.button34),
                        findViewById(R.id.button35),
                        findViewById(R.id.button36),
                        findViewById(R.id.button37)
                },
                {
                        findViewById(R.id.button40),
                        findViewById(R.id.button41),
                        findViewById(R.id.button42),
                        findViewById(R.id.button43),
                        findViewById(R.id.button44),
                        findViewById(R.id.button45),
                        findViewById(R.id.button46),
                        findViewById(R.id.button47)
                },
                {
                        findViewById(R.id.button50),
                        findViewById(R.id.button51),
                        findViewById(R.id.button52),
                        findViewById(R.id.button53),
                        findViewById(R.id.button54),
                        findViewById(R.id.button55),
                        findViewById(R.id.button56),
                        findViewById(R.id.button57)
                },
                {
                        findViewById(R.id.button60),
                        findViewById(R.id.button61),
                        findViewById(R.id.button62),
                        findViewById(R.id.button63),
                        findViewById(R.id.button64),
                        findViewById(R.id.button65),
                        findViewById(R.id.button66),
                        findViewById(R.id.button67)
                },
                {
                        findViewById(R.id.button70),
                        findViewById(R.id.button71),
                        findViewById(R.id.button72),
                        findViewById(R.id.button73),
                        findViewById(R.id.button74),
                        findViewById(R.id.button75),
                        findViewById(R.id.button76),
                        findViewById(R.id.button77)
                }
        };

        updateGameBoard();
    }

    /**
     * Updates the buttons on the current board. Is called every time something is clicked.
     */
    private void updateGameBoard() {
        // Found the code for this setColorFilter method here:
        // https://stackoverflow.com/a/37287354

        for (int r = 0; r < gameBoard.ROW_COUNT; r++) {
            for (int c = 0; c < gameBoard.COL_COUNT; c++) {
                String s = gameBoard.getSquare(r, c);

                // For 0 and unknowns we want it to be blank.
                if (!(s.equals("0") || s.equals("#"))) {
                    buttons[r][c].setText(s);
                } else {
                    buttons[r][c].setText(" ");
                }

                switch (s) {
                    // Flagged
                    case "^":
                        buttons[r][c].getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                        break;
                    // Mine
                    case "*":
                        buttons[r][c].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                        break;
                    // Unknown
                    case "#":
                        buttons[r][c].getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        break;
                    // Shown tile
                    default:
                        buttons[r][c].getBackground().clearColorFilter();
                        break;
                }
            }
        }

        // If the player is flagging set the flagging button to yellow.
        if (gameBoard.isFlagging()) {
            findViewById(R.id.flagging).getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
        } else {
            findViewById(R.id.flagging).getBackground().clearColorFilter();
        }

        TextView textView = findViewById(R.id.textView);
        if (gameBoard.hasLost()) {
            textView.setText(" Game over, press 'Reset Game' to try again!");
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(Color.RED);
        } else if (gameBoard.hasWon()) {
            textView.setText(" A winner is you!");
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundColor(Color.GREEN);
        } else {
            textView.setText(" Get ready to sweep some mines! Can you find all " + gameBoard.NUM_MINES + "?");
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button00(View view) {
        gameBoard.click(0, 0);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button01(View view) {
        gameBoard.click(0, 1);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button02(View view) {
        gameBoard.click(0, 2);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button03(View view) {
        gameBoard.click(0, 3);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button04(View view) {
        gameBoard.click(0, 4);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button05(View view) {
        gameBoard.click(0, 5);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button06(View view) {
        gameBoard.click(0, 6);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button07(View view) {
        gameBoard.click(0, 7);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button10(View view) {
        gameBoard.click(1, 0);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button11(View view) {
        gameBoard.click(1, 1);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button12(View view) {
        gameBoard.click(1, 2);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button13(View view) {
        gameBoard.click(1, 3);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button14(View view) {
        gameBoard.click(1, 4);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button15(View view) {
        gameBoard.click(1, 5);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button16(View view) {
        gameBoard.click(1, 6);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button17(View view) {
        gameBoard.click(1, 7);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button20(View view) {
        gameBoard.click(2, 0);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button21(View view) {
        gameBoard.click(2, 1);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button22(View view) {
        gameBoard.click(2, 2);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button23(View view) {
        gameBoard.click(2, 3);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button24(View view) {
        gameBoard.click(2, 4);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button25(View view) {
        gameBoard.click(2, 5);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button26(View view) {
        gameBoard.click(2, 6);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button27(View view) {
        gameBoard.click(2, 7);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button30(View view) {
        gameBoard.click(3, 0);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button31(View view) {
        gameBoard.click(3, 1);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button32(View view) {
        gameBoard.click(3, 2);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button33(View view) {
        gameBoard.click(3, 3);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button34(View view) {
        gameBoard.click(3, 4);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button35(View view) {
        gameBoard.click(3, 5);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button36(View view) {
        gameBoard.click(3, 6);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button37(View view) {
        gameBoard.click(3, 7);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button40(View view) {
        gameBoard.click(4, 0);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button41(View view) {
        gameBoard.click(4, 1);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button42(View view) {
        gameBoard.click(4, 2);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button43(View view) {
        gameBoard.click(4, 3);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button44(View view) {
        gameBoard.click(4, 4);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button45(View view) {
        gameBoard.click(4, 5);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button46(View view) {
        gameBoard.click(4, 6);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button47(View view) {
        gameBoard.click(4, 7);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button50(View view) {
        gameBoard.click(5, 0);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button51(View view) {
        gameBoard.click(5, 1);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button52(View view) {
        gameBoard.click(5, 2);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button53(View view) {
        gameBoard.click(5, 3);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button54(View view) {
        gameBoard.click(5, 4);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button55(View view) {
        gameBoard.click(5, 5);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button56(View view) {
        gameBoard.click(5, 6);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button57(View view) {
        gameBoard.click(5, 7);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button60(View view) {
        gameBoard.click(6, 0);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button61(View view) {
        gameBoard.click(6, 1);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button62(View view) {
        gameBoard.click(6, 2);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button63(View view) {
        gameBoard.click(6, 3);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button64(View view) {
        gameBoard.click(6, 4);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button65(View view) {
        gameBoard.click(6, 5);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button66(View view) {
        gameBoard.click(6, 6);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button67(View view) {
        gameBoard.click(6, 7);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button70(View view) {
        gameBoard.click(7, 0);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button71(View view) {
        gameBoard.click(7, 1);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button72(View view) {
        gameBoard.click(7, 2);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button73(View view) {
        gameBoard.click(7, 3);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button74(View view) {
        gameBoard.click(7, 4);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button75(View view) {
        gameBoard.click(7, 5);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button76(View view) {
        gameBoard.click(7, 6);
        updateGameBoard();
    }

    /**
     * Button for the game.
     * @param view View: Provided by Android.
     */
    public void button77(View view) {
        gameBoard.click(7, 7);
        updateGameBoard();
    }

    /**
     * Toggles flagging for the game.
     * @param view View: Provided by Android.
     */
    public void toggleFlagging(View view) {
        gameBoard.toggleFlagging();
        updateGameBoard();
    }

    /**
     * Resets the game.
     * @param view View: Provided by Android.
     */
    public void resetGame(View view) {
        gameBoard = new MinesweeperTracker(8, 8);
        updateGameBoard();
    }

}
