package hotciv.standard;

import hotciv.framework.*;
import java.util.*;

public class DeltaCivImpl extends GameImpl {
    TileImpl[][] board;

    // Default constructor
    DeltaCivImpl() {
        setBoard();
    }

    //Add tiles to board matrix
    private void setBoard() {
        //Create game board w/ AlphaCiv tile requirements
        board = new TileImpl[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i == 0) && (j == 1)) {
                    board[i][j] = new TileImpl(GameConstants.HILLS);
                }
                else if ((i == 1) && (j == 0)) {
                    board[i][j] = new TileImpl(GameConstants.OCEANS);
                }
                else if ((i == 2) && (j == 2)) {
                    board[i][j] = new TileImpl(GameConstants.MOUNTAINS);
                }
                else {
                    board[i][j] = new TileImpl(GameConstants.PLAINS);
                }
            }
        }
    }

    //Overwrite getTileAt()
    public Tile getTileAt(Position pos) {
        int row = pos.getRow();
        int col = pos.getColumn();
        return (board[row][col]);
    }
}
