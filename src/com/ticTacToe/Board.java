package com.ticTacToe;

import com.ngdev.Player;
import com.ngdev.Status;
import com.ngdev.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Board extends com.ngdev.BaseBoard {

    private int boardSize;
    private List<List<Symbol>> cells;
    private List<Symbol> allowedSymbols;
    private Symbol defaultSymbol;
    private Game game;
    private int emptyCellsCount;

    private int[] sumRows;
    private int[] sumCols;
    private int sumForwardDiagonal;
    private int sumReverseDiagonal;
    private boolean gameWon;

    public Board(int boardSize, Symbol defaultSymbol) {
        this.boardSize = boardSize;
        this.defaultSymbol = defaultSymbol;

        initBoard();
    }

    @Override
    public void initBoard(){
        this.cells = new ArrayList<>();
        for(int i = 0; i<boardSize; i++){
            List<Symbol> row = new ArrayList<>(boardSize);
            for(int j = 0; j < boardSize; j++){
                row.add(defaultSymbol);
            }
            this.cells.add(row);
        }
        sumRows = new int[boardSize];
        sumCols = new int[boardSize];
        sumForwardDiagonal = 0;
        sumReverseDiagonal = 0;
        gameWon = false;
        emptyCellsCount = boardSize * boardSize;
        System.out.println("Board initialized with default value " + defaultSymbol);
    }

    @Override
    public boolean isCellEmpty(int row, int col) {
        return this.cells.get(row).get(col).equals(defaultSymbol);
    }

    @Override
    public void move(Player player, int row, int col) {
        if(row >= boardSize || col >= boardSize || row < 0 || col < 0) {
            System.out.println("Out of bounds. Invalid Move.");
            player.moveFailed();
            return;
        }
        if(Move.isValidMove(this, row, col)){
            this.addMove(player, row, col);
        } else {
            System.out.println("Invalid move. Cell already occupied.");
            player.moveFailed();
            return;
        }
        if(isBoardFull()) {
            game.setStatus(Status.FINISHED);
            System.out.println("No more moves left. Game Over.");
        }
    }

    private boolean isBoardFull() {
        return emptyCellsCount == 0;
    }

    private void addMove(Player player, int row, int col){
        List<Symbol> cellRow = this.cells.get(row);
        cellRow.set(col, player.getSymbol());
        this.cells.set(row, cellRow);
        emptyCellsCount--;

        int val = player.getSymbolValue();
        updateBoardState(val, row, col);

        updateGameStatusFromBoard(val);

        System.out.println("Player : "+ player.getUserId() + " successfully moved to : " + row + " , " + col);
        printBoard();
    }

    private void updateGameStatusFromBoard(int val) {
        int sumVal = boardSize * val;
        for(int i = 0; i < boardSize; i++) {
            if(sumRows[i] == sumVal || sumCols[i] == sumVal || sumReverseDiagonal == sumVal || sumForwardDiagonal == sumVal){
                gameWon = true;
                break;
            }
        }
    }

    public boolean isGameWon() {
        return gameWon;
    }

    private void updateBoardState(int val, int row, int col){
        sumRows[row] += val;
        sumCols[col] += val;
        if(row == col){
            sumForwardDiagonal += val;
        }
        if(row + col == boardSize - 1){
            sumReverseDiagonal += val;
        }
    }

    public void printBoard(){
        for(List<Symbol> row: cells){
            System.out.println();
            for(Symbol cell: row) {
                System.out.print(cell.getSign() + " |");
            }
        }
        System.out.println();
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
