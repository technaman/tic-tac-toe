package com.ngdev;

import java.util.Scanner;

public class Player {
    private User user;

    Game game;
    Symbol symbol;

    public Player(User user, Symbol symbol) {
        this.user = user;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getUserId(){
        return user.getUserId();
    }

    public void setGame(Game game) {
        this.game = game;
        System.out.println("Player with userId: " + user.getUserId() + " joined the game: " + game.getGameId());
    }

    public Player(Symbol symbol) {
        this.user = User.getDummyUser();
        this.symbol = symbol;
        System.out.println("Player created with symbol: " + this.getSymbol().getValue());
    }

    public Game getGame() {
        return game;
    }

    public void move() {
        int row, col;
        System.out.println("Turn for User: " + user.getUserId() + ". Enter row and col to move: ");
        Scanner sc = new Scanner(System.in);

        row = sc.nextInt();
        col = sc.nextInt();

        this.getGame().move(this, row, col);
    }

    public int getSymbolValue() {
        return symbol.getValue();
    }

    public void moveFailed(){
        System.out.println("Please enter valid row column values. Try Again.");
        this.move();
    }
}
