package com.ngdev;

public abstract class BaseBoard {

    public abstract void initBoard();
    public abstract boolean isCellEmpty(int row, int col);
    public abstract void move(Player player, int row, int col);

}
