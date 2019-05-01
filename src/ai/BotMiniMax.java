package ai;

import model.TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class BotMiniMax {
    private String currentTurn;
    private String otherPlayer;

    public BotMiniMax(String currentTurn) {
        this.currentTurn = currentTurn;
        if (currentTurn.equals(TicTacToe.p1)) {
            otherPlayer = TicTacToe.p2;
        } else
            otherPlayer = TicTacToe.p1;
    }

    public int miniMax(TicTacToe game, int depth) {
        if (!game.getWinner().equals("_") || game.isBoardFull()) {
            return score(game, depth);
        } else if (game.getCurrentTurn().equals(currentTurn)) {
            return getMaxScore(game, depth);
        } else if (game.getCurrentTurn().equals(otherPlayer)) {
            return getMinScore(game, depth);
        } else
            System.out.println("ERROR " + depth);
            return -1000;
    }

    private int getMaxScore(TicTacToe game, int depth) {
        depth++;
        List<Integer> emptySpots = game.getEmptySpots();
        List<Integer> scores = new ArrayList<>();
        for (Integer i: emptySpots) {
            TicTacToe copyGame = game.deepCopy();
            copyGame.move(i);
            scores.add(miniMax(copyGame, depth));
        }
        int bestScore = (int) Double.NEGATIVE_INFINITY;
        int bestMove = 0;
        int cursor = 0;
        for (Integer i : scores) {
            if (i > bestScore) {
                bestScore = i;
                bestMove = cursor;
            }
            cursor++;
        }
        game.move(emptySpots.get(bestMove));
        return bestScore;
    }

    private int getMinScore(TicTacToe game, int depth) {
        depth++;
        List<Integer> emptySpots = game.getEmptySpots();
        List<Integer> scores = new ArrayList<>();
        for (Integer i: emptySpots) {
            TicTacToe copyGame = game.deepCopy();
            copyGame.move(i);
            scores.add(miniMax(copyGame, depth));
        }
        int worstScore = (int) Double.POSITIVE_INFINITY;
        int worstMove = 0;
        int cursor = 0;
        for (Integer i : scores) {
            if (i < worstScore) {
                worstScore = i;
                worstMove = cursor;
            }
            cursor++;
        }
        game.move(emptySpots.get(worstMove));
        return worstScore;
    }


    public int score(TicTacToe game, int depth) {
        if (game.getWinner().equals(currentTurn)) {
            return 10-depth;
        } else if (game.getWinner().equals(otherPlayer)){
            return -10+depth;
        } else
            return 0;
    }
}
