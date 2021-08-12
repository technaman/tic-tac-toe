package com.ticTacToe;

import com.ngdev.BaseMove;
import com.ngdev.BaseBoard;

public class Move extends BaseMove {

    public static boolean isValidMove(BaseBoard board, int row, int col) {
        return board.isCellEmpty(row, col);
    }

    @Override
    public <List> BaseMove getPossibleMoves() {
        return null;
    }

}
