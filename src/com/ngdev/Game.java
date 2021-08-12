package com.ngdev;

public abstract class Game {

    public abstract void start();

    public abstract Player getPlayerTurn();

    public abstract Player toss();

    public abstract int getGameId();

    public abstract void move(Player p, int row, int col);
}
