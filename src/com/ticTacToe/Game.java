package com.ticTacToe;

import com.ngdev.Player;
import com.ngdev.Status;

import java.util.List;

public class Game extends com.ngdev.Game {
    private int gameId;
    private Board board;
    private List<Player> playerList;
    private Status status;
    private RuleSet ruleSet;
    private Player currentPlayer;
    private int playerIndex;
    private Player winner;

    public Game(Board board, List<Player> playerList, Status status, RuleSet ruleSet, Player currentPlayer) {
        this.gameId = createRandomGameId();
        this.board = board;
        this.playerList = playerList;
        this.status = status;
        this.ruleSet = ruleSet;
        this.currentPlayer = currentPlayer;
        this.addPlayersToGame();
        this.board.setGame(this);
    }

    public Game(Board board, List<Player> playerList, RuleSet ruleSet) {
        this(board, playerList, Status.NOT_STARTED, ruleSet, null);
    }

    private int createRandomGameId(){
        return (int) (Math.random()*1000);
    }

    public void addPlayersToGame(){
        for(Player player: playerList){
            player.setGame(this);
        }
    }

    @Override
    public void start() {
        System.out.println("Game: " + gameId + " Started");
        currentPlayer = getPlayerTurn();
        currentPlayer.move();
    }

    @Override
    public Player getPlayerTurn() {
        if(this.getStatus().equals(Status.NOT_STARTED)){
            return toss();
        } else {
            playerIndex = (playerIndex + 1) % playerList.size();
            return playerList.get(playerIndex);
        }
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Player toss() {
        setStatus(Status.IN_PROGRESS);
        System.out.println("toss() called");
        playerIndex = 0;
        return playerList.get(0);
        // TODO: Add a toss logic
    }

    @Override
    public int getGameId() {
        return gameId;
    }

    @Override
    public void move(Player p, int row, int col) {
        currentPlayer = p;
        if(status == Status.FINISHED) {
            System.out.println("Game Over. No more moves left.");
            return;
        }
        board.move(p, row, col);

        checkGameStatus();
        if(status == Status.IN_PROGRESS) {
            nextMove();
        }
    }

    private void checkGameStatus() {
        if(board.isGameWon()) {
            winner = currentPlayer;
            status = Status.FINISHED;
            System.out.println("Player: " + winner.getUserId() + " won the game.");
        }
    }

    private void nextMove() {
        currentPlayer = getPlayerTurn();
        currentPlayer.move();
    }

    public Status getStatus() {
        return status;
    }

}
