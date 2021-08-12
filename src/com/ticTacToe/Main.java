package com.ticTacToe;

import com.ngdev.Player;
import com.ngdev.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        System.out.println("Inside Main method");

        final int ticTacToeBoardSize = 3;
        Symbol defaultSymbol = new Symbol(0, '_');

        Board board = new Board(ticTacToeBoardSize, defaultSymbol);

        List<Player> playerList = new ArrayList<>();

        Symbol p1 = new Symbol(1, '@');
        Symbol p2 = new Symbol(-1, '/');



        Player player1 = new Player(p1);
        Player player2 = new Player(p2);
        playerList.add(player1);
        playerList.add(player2);

        RuleSet ruleSet = new RuleSet();

        Game game = new Game(board, playerList, ruleSet);

        game.start();

    }
}
