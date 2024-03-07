package cs1302.game;

import cs1302.gameutil.GamePhase;
import cs1302.gameutil.Token;
import cs1302.gameutil.TokenGrid;

/**
 * {@code ConnectFour} represents a two-player connection game involving a two-dimensional grid of
 * {@linkplain cs1302.gameutil.Token tokens}. When a {@code ConnectFour} game object is
 * constructed, several instance variables representing the game's state are initialized and
 * subsequently accessible, either directly or indirectly, via "getter" methods. Over time, the
 * values assigned to these instance variables should change so that they always reflect the
 * latest information about the state of the game. Most of these changes are described in the
 * project's <a href="https://github.com/cs1302uga/cs1302-c4-alpha#functional-requirements">
 * functional requirements</a>.
 */
public class ConnectFour {
    private int rows;        // number of grid rows
    private int cols;        // number of grid columns
    private Token[][] grid;  // 2D array of tokens in the grid
    private Token[] player;  // 1D array of player tokens (length 2)
    private int numDropped;  // number of tokens dropped so far
    private int lastDropRow; // row index of the most recent drop
    private int lastDropCol; // column index of the most recent drop
    private GamePhase phase; // current game phase

    /**
     * Constructs a {@link cs1302.game.ConnectFour} game with a grid that has {@code rows}-many
     * rows and {@code cols}-many columns. All of the game's instance variables are expected to
     * be initialized by this constructor as described in the project's
     * <a href="https://github.com/cs1302uga/cs1302-c4-alpha#functional-requirements">functional
     * requirements</a>.
     *
     * @param rows the number of grid rows
     * @param cols the number of grid columns
     * @throws IllegalArgumentException if the value supplied for {@code rows} or {@code cols} is
     *     not supported. The following values are supported: {@code 6 <= rows <= 9} and
     *     {@code 7 <= cols <= 9}.
     */
    public ConnectFour(int rows, int cols)  {
        this.rows = rows;
        this.cols = cols;
        grid = new Token[rows][cols];
        player = new Token[2];
        numDropped = 0;
        lastDropRow = -1;
        lastDropCol = -1;
        if (rows < 6 || rows > 9 || cols < 7 || cols > 9) {
            throw new IllegalArgumentException();
        } // if
        phase = GamePhase.NEW;
    } // ConnectFour

    /**
     * Return the number of rows in the game's grid.
     *
     * @return the number of rows
     */
    public int getRows() {
        return this.rows;
    } // getRows

    /**
     * Return the number of columns in the game's grid.
     *
     * @return the number of columns
     */
    public int getCols() {
        return this.cols;
    } // getCols

    /**
     * Return whether {@code row} and {@code col} specify a location inside this game's grid.
     *
     * @param row the position's row index
     * @param col the positions's column index
     * @return {@code true} if {@code row} and {@code col} specify a location inside this game's
     *     grid and {@code false} otherwise
     */
    public boolean isInBounds(int row, int col) {
        if (0 < row && row < getRows() && 0 < col && col < getCols()) {
            return true;
        } else {
            return false;
        } // if
    } // isInBounds

    /**
     * Return the grid {@linkplain cs1302.gameutil.Token token} located at the specified position
     * or {@code null} if no token has been dropped into that position.
     *
     * @param row the token's row index
     * @param col the token's column index
     * @return the grid token located in row {@code row} and column {@code col}, if it exists;
     *     otherwise, the value {@code null}
     * @throws IndexOutOfBoundsException if {@code row} and {@code col} specify a position that is
     *     not inside this game's grid.
     */
    public Token getTokenAt(int row, int col) {
        if (row <= 0 && row >= getRows() && col <= 0 && col >= getCols()) {
            throw new IndexOutOfBoundsException();
        } // if
        if (grid[row][col] == null) {
            return null;
        } // if
        return this.grid[row][col];
    } // getTokenAt

    /**
     * Set the first player token and second player token to {@code token0} and {@code token1},
     * respectively. If the current game phase is {@link cs1302.gameutil.GamePhase#NEW}, then
     * this method changes the game phase to {@link cs1302.gameutil.GamePhase#READY}, but only
     * if no exceptions are thrown.
     *.
     * @param token0 token for first player
     * @param token1 token for second player
     * @throws NullPointerException if {@code token0} or {@code token1} is {@code null}.
     * @throws IllegalArgumentException if {@code token0 == token1}.
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#PLAYABLE} or {@link cs1302.gameutil.GamePhase#OVER}.
     */
    public void setPlayerTokens(Token token0, Token token1) {
        if (token0 == null || token1 == null) {
            throw new NullPointerException();
        } // if
        if (token0 == token1) {
            throw new IllegalArgumentException();
        } // if
        if (getPhase() == GamePhase.PLAYABLE || getPhase() == GamePhase.OVER) {
            throw new IllegalStateException();
        } // if
        player[0] = token0;
        player[1] = token1;
        phase = GamePhase.READY;
    } // setPlayerTokens

    /**
     * Return a player's token.
     *
     * @param player the player ({@code 0} for first player and {@code 1} for second player)
     * @return the token for the specified player
     * @throws IllegalArgumentException if {@code player} is neither {@code 0} nor {@code 1}
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW}.
     */
    public Token getPlayerToken(int player) {
        if (player != 0 && player != 1) {
            throw new IllegalArgumentException();
        } // if
        if (getPhase() == GamePhase.NEW) {
            throw new IllegalStateException();
        } // if
        return this.player[player];
    } // getPlayerToken

    /**
     * Return the number of tokens that have been dropped into this game's grid so far.
     *
     * @return the number of dropped tokens
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getNumDropped() {
        if (getPhase() == GamePhase.NEW || getPhase() == GamePhase.READY) {
            throw new IllegalStateException();
        } // if
        return this.numDropped;
    } // getNumDropped

    /**
     * Return the row index of the last (i.e., the most recent) token dropped into this
     * game's grid.
     *
     * @return the row index of the last drop
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getLastDropRow() {
        if (getPhase() == GamePhase.NEW || getPhase() == GamePhase.READY) {
            throw new IllegalStateException();
        } // if
        return this.lastDropRow;
    } // getLastDropRow

    /**
     * Return the col index of the last (i.e., the most recent) token dropped into this
     * game's grid.
     *
     * @return the column index of the last drop
     * @throws IllegalStateException if {@link #getPhase getPhase()} returns
     *     {@link cs1302.gameutil.GamePhase#NEW} or {@link cs1302.gameutil.GamePhase#READY}.
     */
    public int getLastDropCol() {
        if (getPhase() == GamePhase.NEW || getPhase() == GamePhase.READY) {
            throw new IllegalStateException();
        }
        return this.lastDropCol;
    } // getLastDropCol

    /**
     * Return the current game phase.
     *
     * @return current game phase
     */
    public GamePhase getPhase() {
        return this.phase;
    } // getPhase

    /**
     * Drop a player's token into a specific column in the grid. This method should not enforce turn
     * order -- that is the players' responsibility should they desire an polite and honest game.
     *
     * @param player the player ({@code 0} for first player and {@code 1} for second player)
     * @param col the grid column where the token will be dropped
     * @throws IndexOutOfBoundsException if {@code col} is not a valid column index
     * @throws IllegalArgumentException if {@code player} is neither {@code 0} nor {@code 1}
     * @throws IllegalStateException if {@link #getPhase getPhase()} does not return
     *    {@link cs1302.gameutil.GamePhase#READY} or {@link cs1302.gameutil.GamePhase#PLAYABLE}
     * @throws IllegalStateException if the specified column in the grid is full
     */
    public void dropToken(int player, int col) {
        if (col > getCols()) {
            throw new IndexOutOfBoundsException();
        } // if
        if (player != 0 && player != 1) {
            throw new IllegalArgumentException();
        } // if
        if (getPhase() == GamePhase.NEW) {
            throw new IllegalStateException();
        } // if
        if (getPhase() == GamePhase.OVER) {
            throw new IllegalStateException();
        } // if
        if (getPhase() == GamePhase.READY || getPhase() == GamePhase.PLAYABLE) {
            for (int i = getRows() - 1; i >= 0; i--) {
                if (grid[i][col] == null) {
                    grid[i][col] = getPlayerToken(player);
                    lastDropRow = i;
                    lastDropCol = col;
                    numDropped++;
                    this.phase = GamePhase.PLAYABLE;
                    return;
                } // if
            } // for
        } // if
    } // dropToken

    /**
     * Return {@code true} if the last token dropped via {@link #dropToken} created a
     * <em>connect four</em>. A <em>connect four</em> is a sequence of four equal tokens (i.e., they
     * have the same color) -- this sequence can occur horizontally, vertically, or diagonally.
     * If the grid is full or the last drop created a <em>connect four</em>, then this method
     * changes the game's phase to {@link cs1302.gameutil.GamePhase#OVER}.
     *
     * @return {@code true} if the last token dropped created a <em>connect four</em>, else
     *     {@code false}
     */
    public boolean isLastDropConnectFour() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j + 3 < getCols(); j++) {
                if (grid[i][j] != null &&
                    grid[i][j] == grid[i][j + 1] &&
                    grid[i][j] == grid[i][j + 2] &&
                    grid[i][j] == grid[i][j + 3]) {
                    phase = GamePhase.OVER;
                    return true;
                } // if
            } // for
        } // for
        for (int i = 0; i + 3 < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if (grid[i][j] != null &&
                    grid[i][j] == grid[i + 1][j] &&
                    grid[i][j] == grid[i + 2][j] &&
                    grid[i][j] == grid[i + 3][j]) {
                    phase = GamePhase.OVER;
                    return true;
                } // if
            } // for
        } // for
        for (int i = 0; i - 3 >= 0 && i < getRows(); i++) {
            for (int j = 0; j + 3 < getCols(); j++) {
                if (grid[i][j] != null &&
                    grid[i][j] == grid[i - 1][j + 1] &&
                    grid[i][j] == grid[i - 2][j + 2] &&
                    grid[i][j] == grid[i - 3][j + 3]) {
                    phase = GamePhase.OVER;
                    return true;
                } // if
            } // for
        } // for
        for (int i = 0; i + 3 < getRows(); i++) {
            for (int j = 0; j + 3 < getCols(); j++) {
                if (grid[i][j] != null &&
                    grid[i][j] == grid[i + 1][j + 1] &&
                    grid[i][j] == grid[i + 2][j + 2] &&
                    grid[i][j] == grid[i + 3][j + 3]) {
                    phase = GamePhase.OVER;
                    return true;
                } // if
            } // for
        } // for
        if (numDropped == getRows() * getCols()) {
            phase = GamePhase.OVER;
            return false;
        } // if
        return false;
    } // isLastDropConnectFour

    /**
     * Print the game grid to standard output. This method assumes that the constructor
     * is implemented correctly.
     */
    public void printGrid() {
        TokenGrid.println(this.grid);
    } // printGrid
} // ConnectFour
