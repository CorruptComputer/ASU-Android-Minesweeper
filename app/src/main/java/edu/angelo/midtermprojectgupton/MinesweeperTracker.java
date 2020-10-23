package edu.angelo.midtermprojectgupton;

import java.util.Random;

/**
 * Tracker for a Minesweeper game.
 * @author Nickolas Gupton
 */
public class MinesweeperTracker {
    /**
     * int: ROW_COUNT | The number of rows in the current game of Minesweeper.
     */
    public final int ROW_COUNT;

    /**
     * int: COL_COUNT | The number of columns in the current game of Minesweeper.
     */
    public final int COL_COUNT;

    /**
     * int: NUM_MINES | The number of mines in the current game of Minesweeper.
     *                  Calculated as (ROW_COUNT * COL_COUNT)/6.
     */
    public final int NUM_MINES;

    /**
     * int[][]: gameBoard | The board of the current game, 9's are mines, other numbers are the number of mines around them.
     */
    private final int[][] gameBoard;

    /**
     * boolean[][]: shown | True if the specified row and column has been shown.
     */
    private final boolean[][] shown;

    /**
     * boolean[][]: flagged | True if the specified row and column has been flagged.
     */
    private final boolean[][] flagged;

    /**
     * boolean: gameLost | Tracks if the current game has been lost.
     */
    private boolean gameLost = false;

    /**
     * boolean: gameWon | Tracks if the current game has been won.
     */
    private boolean gameWon = false;

    /**
     * boolean: flagging | Tracks if the player is currently flagging a tile.
     *                     Resets back to false after a tile has been flagged.
     */
    private boolean isFlagging = false;

    /**
     * Random: rdm | Generates random numbers, used for mine placement.
     */
    private static final Random rdm = new Random();


    /**
     * Creates a new MinesweeperTracker object.
     * @param rows int: Number of rows to create on the game board.
     * @param cols int: Number of columns to create on the game board.
     */
    public MinesweeperTracker(int rows, int cols) {
        ROW_COUNT = rows;
        COL_COUNT = cols;
        NUM_MINES = (ROW_COUNT * COL_COUNT) / 6;

        gameBoard = generateNewBoard();
        shown = new boolean[ROW_COUNT][COL_COUNT];
        flagged = new boolean[ROW_COUNT][COL_COUNT];
    }

    /**
     * Generates a new board in this Minesweeper Object.
     * @return int[][]: The game board to use for this Minesweeper Object.
     */
    private int[][] generateNewBoard() {
        int[][] newBoard = new int[ROW_COUNT][COL_COUNT];


        // Place mines randomly.
        for (int i = 0; i < NUM_MINES; i++) {
            int row = rdm.nextInt(ROW_COUNT);
            int col = rdm.nextInt(COL_COUNT);

            // If it is already a mine try again.
            if (newBoard[row][col] != 9) {
                newBoard[row][col] = 9;
            } else {
                i--;
            }
        }


        for (int r = 0; r < ROW_COUNT; r++) {
            for (int c = 0; c < COL_COUNT; c++) {
                // If it is a mine don't change it.
                if (newBoard[r][c] != 9) {
                    int count = 0;

                    // Look at the tiles around the current one to check for mines.
                    for (int i = r - 1; i < r + 2; i++) {
                        for (int k = c - 1; k < c + 2; k++) {
                            try {
                                // Make sure you aren't checking the current tile
                                // and that the tile your'e looking at is actually a mine.
                                if ((i != r || k != c) && newBoard[i][k] == 9) {
                                    count++;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                // Do nothing.
                            }
                        }
                    }
                    newBoard[r][c] = count;
                }
            }
        }

        return newBoard;
    }

    /**
     * Clicks a tile to reveal what is there, calls itself recursively for 0's.
     * @param row int: The row to click.
     * @param col int: The column to click.
     */
    public void click(int row, int col) {
        // If the player has lost, won, or the tile is already shown ignore the click.
        if (hasLost() || hasWon() || shown[row][col]) return;

        if (isFlagging()) {
            flagged[row][col] = !flagged[row][col];
            isFlagging = false;
            return;
        }

        // If this tile is flagged ignore the click.
        if (flagged[row][col]) return;

        // If its a mine the game has been lost.
        if (gameBoard[row][col] == 9) {
            gameLost = true;
        }

        shown[row][col] = true;

        // If there is no mines around this tile we can show the ones around it safely.
        if (gameBoard[row][col] == 0) {
            for (int i = row - 1; i < row + 2; i++) {
                for (int k = col - 1; k < col + 2; k++) {
                    try {
                        if (!shown[i][k]) {
                            click(i, k);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // Do nothing.
                    }
                }
            }
        }

        // After the player clicks we want to check if they have won.
        checkForWin();
    }

    /**
     * Gets the value of the specified tile.
     * @param row int: Row to return.
     * @param col int: Column to return.
     * @return String: The value of the tile. If its shown it will be the number, mines will be *, unknowns will be #, flags will be ^.
     */
    public String getSquare(int row, int col) {
        if (shown[row][col]) {
            return gameBoard[row][col] == 9 ? "*" : String.valueOf(gameBoard[row][col]);
        } else if (flagged[row][col]) {
            return "^";
        } else {
            return "#";
        }
    }

    /**
     * Checks if the game has been lost.
     * @return boolean: True if the game has been lost.
     */
    public boolean hasLost() {
        return gameLost;
    }

    /**
     * Checks the game for a win. Sets gameWon to true if every tile that isn't a mine has been shown.
     */
    private void checkForWin() {
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[r].length; c++) {
                if (gameBoard[r][c] != 9 && !shown[r][c]) {
                    return;
                }
            }
        }

        gameWon = true;
    }

    /**
     * Checks if the game has been won.
     * @return boolean: True if the game has been won.
     */
    public boolean hasWon() {
        return gameWon;
    }

    /**
     * Toggles the flagging variable.
     */
    public void toggleFlagging() {
        isFlagging = !isFlagging;
    }

    /**
     * Checks if the player is currently flagging.
     * @return boolean: True if the player is flagging.
     */
    public boolean isFlagging() {
        return isFlagging;
    }

    /**
     * Builds a string from the current game board.
     * @return String: The current game board with tiles hidden.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int[] row : gameBoard) {
            sb.append("[");
            for (int col : row) {
                sb.append(col);
                sb.append(" ");
            }
            sb.append("\b]\n");
        }

        return sb.toString();
    }
}
