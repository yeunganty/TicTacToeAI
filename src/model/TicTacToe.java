package model;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    private List<String> game;
    private String currentTurn;
    private String winner;
    public static final String p1 = "X";
    public static final String p2 = "O";
    private static int p1Score;
    private static int p2Score;
    private static int botScore;



    public static boolean botGame;


    public TicTacToe() {
        newGame();
        winner = "_";
        currentTurn = p1;
    }

    public void newGame() {
        game = new ArrayList<>();
        for (int i=0; i<9; i++) {
            game.add("_");
        }
        winner = "_";
        currentTurn = p1;
    }

    //MODIFIES: this
    //EFFECTS: creates a new game, resets the winner, if valid string input, sets the current Turn to input.
    //      Default first player is p1.
    public void newGame(String start) {
        newGame();
        if (start.equals(p1)) {
            currentTurn = p1;
        } else if (start.equals(p2)) {
            currentTurn = p2;
        }
    }


    public List<Integer> getEmptySpots() {
        List<Integer> emptySpots = new ArrayList<>();
        for (int i=0; i<9;i++) {
            if (game.get(i).equals("_")) {
                emptySpots.add(i);
            }
        }
        return emptySpots;
    }



    public boolean move(int position) {
        if (validGameState()) {
            if (!game.get(position).equals("_")) {
                System.out.println("Symbol already exists at " + position);
                return false;
            } else if (winner.equals("_")) {
                game.set(position, currentTurn);
                if (wonGame(currentTurn)) {
                    winner = currentTurn;
                } else {
                    changeTurns();
                }

                return true;
            }
        }
        return false;
    }

    private void changeTurns() {
        if (currentTurn.equals(p1)) {
            currentTurn = p2;
        } else
            currentTurn = p1;
    }

    public boolean wonGame(String p) {
        return ((game.get(0).equals(p) && game.get(1).equals(p) && game.get(2).equals(p))
                ||(game.get(3).equals(p) && game.get(4).equals(p) && game.get(5).equals(p))
                ||(game.get(6).equals(p) && game.get(7).equals(p) && game.get(8).equals(p))
                ||(game.get(0).equals(p) && game.get(3).equals(p) && game.get(6).equals(p))
                ||(game.get(1).equals(p) && game.get(4).equals(p) && game.get(7).equals(p))
                ||(game.get(2).equals(p) && game.get(5).equals(p) && game.get(8).equals(p))
                ||(game.get(0).equals(p) && game.get(4).equals(p) && game.get(8).equals(p))
                ||(game.get(2).equals(p) && game.get(4).equals(p) && game.get(6).equals(p)));
    }

    public TicTacToe deepCopy() {
        TicTacToe deepCopy = new TicTacToe();
        deepCopy.setGame(copyGameState());
        deepCopy.setCurrentTurn(currentTurn);
        deepCopy.setWinner(winner);
        return deepCopy;
    }

    private List<String> copyGameState() {
        List<String> copiedGameState = new ArrayList<>();
        for (int i=0;i<9;i++) {
            copiedGameState.add(i,game.get(i));
        }
        return copiedGameState;
    }

    private void setGame(List<String> gameState) {
        game = gameState;
    }

    public void printBoard() {
        String out = "\t";
        for (int i=0;i<9;i++) {
            out = out + game.get(i) + " ";
            if (i==2 || i==5) {
                out += "\n\t";
            }
        }
        System.out.println(out);
    }

    public boolean isBoardFull() {
        for (int i=0; i<9; i++) {
            if (game.get(i).equals("_")) {
                return false;
            }
        }
        return true;
    }

    public boolean validGameState() {
        return (!isBoardFull() && !winner.equals("X") && !winner.equals("O"));
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(String currentTurn) {
        this.currentTurn = currentTurn;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public static int getP1Score() {
        return p1Score;
    }

    public static void setP1Score(int p1Score) {
        TicTacToe.p1Score = p1Score;
    }

    public static int getP2Score() {
        return p2Score;
    }

    public static void setP2Score(int p2Score) {
        TicTacToe.p2Score = p2Score;
    }

    public static int getBotScore() {
        return botScore;
    }

    public static void setBotScore(int botScore) {
        TicTacToe.botScore = botScore;
    }

    public List<String> getGame() {
        return game;
    }

    public static boolean isBotGame() {
        return botGame;
    }

    public static void setBotGame(boolean botGame) {
        TicTacToe.botGame = botGame;
    }

}
