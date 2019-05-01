package gui.consoleGUI;

import ai.BotMiniMax;
import model.TicTacToe;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class consoleTicTacToe {
    private static TicTacToe game;
    private static Scanner input;
    private static String userInput;
    private static boolean exit = false;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        game = new TicTacToe();

        while (!exit) {
            displayStartGame();
            userInput = input.nextLine();
            takeActionBasedOnInput();
        }
    }

    private static void displayStartGame() {
        System.out.println("\t\t---------------------");
        System.out.println("\t\t\t TIC-TAC-TOE");
        System.out.println("\t\t---------------------");
        System.out.println("-Press \"N\" to start a game against a friend.");
        System.out.println("-Press \"1\" to start a game against a bot. (Bot has second turn)");
        System.out.println("-Press \"2\" to start a game against a bot. (Bot has first turn)");
        System.out.println("-Press \"Q\" to quit.");
        System.out.println("Current Scores:\n\t" + "Player 1: " + TicTacToe.getP1Score() +
                "\n\tPlayer 2: " + TicTacToe.getP2Score() +
                "\n\tBot: " + TicTacToe.getBotScore());
    }

    private static void takeActionBasedOnInput() {
        switch (userInput.toUpperCase().charAt(0)) {
            case 'N':
                System.out.println("Starting new game");
                createNewGame();
                break;
            case '1':
                System.out.println("Creating game against AI");
                createAINewGameAISecond();
                break;
            case '2':
                System.out.println("Creating game against AI");
                createAINewGameAIFirst();
                break;
            case 'Q':
                System.out.println("Quitting");
                exit = true;
                break;
            default: System.out.println("Invalid input!");
        }
    }

    private static void createAINewGameAIFirst() {
        System.out.println("Player 2: " + TicTacToe.p2);
        System.out.println("Bot: " + TicTacToe.p1);
        game.newGame();
        while (validGameState()) {
            AIinputFirst();
        }
        if (game.getWinner().equals(TicTacToe.p2)) {
            TicTacToe.setP2Score(TicTacToe.getP2Score()+1);
            System.out.println("\n" + game.getWinner() + " won the game!");
        } else if (game.getWinner().equals(TicTacToe.p1)) {
            TicTacToe.setBotScore(TicTacToe.getBotScore()+1);
            System.out.println("\n" + "AI won the game!");
        } else
            System.out.println("Tie game");
    }

    private static boolean validGameState() {
        return !game.getWinner().equals("X") && !game.getWinner().equals("O") && !game.isBoardFull();
    }

    private static void AIinputFirst() {
        if (game.getCurrentTurn().equals(TicTacToe.p1) && validGameState()) {
            try {
                TimeUnit.SECONDS.sleep((long) 0.5);
            } catch (InterruptedException e) {

            }
            System.out.println("Bots move");
            BotMiniMax bot = new BotMiniMax(game.getCurrentTurn());
            bot.miniMax(game,0);
            game.printBoard();
        }
        twoPlayerUserInput();
    }

    private static void createAINewGameAISecond() {
        System.out.println("Player 1: " + TicTacToe.p1);
        System.out.println("Bot: " + TicTacToe.p2);
        game.newGame();
        game.printBoard();
        while (validGameState()) {
            AIinput();
        }
        if (game.getWinner().equals(TicTacToe.p1)) {
            TicTacToe.setP1Score(TicTacToe.getP1Score()+1);
            System.out.println("\n" + game.getWinner() + " won the game!");
        } else if (game.getWinner().equals(TicTacToe.p2)) {
            TicTacToe.setBotScore(TicTacToe.getBotScore()+1);
            System.out.println("\n" + "AI won the game!");
        } else
            System.out.println("Tie game");
    }

    private static void AIinput() {
        twoPlayerUserInput();
        if (game.getCurrentTurn().equals(TicTacToe.p2) && !game.isBoardFull()) {
            try {
                TimeUnit.SECONDS.sleep((long) 0.5);
            } catch (InterruptedException e) {

            }
            System.out.println("Bots move");
            BotMiniMax bot = new BotMiniMax(game.getCurrentTurn());
            bot.miniMax(game,0);
            game.printBoard();
        }
    }

    private static void createNewGame() {
        System.out.println("Player 1: " + TicTacToe.p1);
        System.out.println("Player 2: " + TicTacToe.p2);
        game.newGame();
        game.printBoard();
        while (validGameState())
            twoPlayerUserInput();
        System.out.println("\n"+game.getWinner() + " won the game!");
        if (game.getWinner().equals(TicTacToe.p2)) {
            TicTacToe.setP2Score(TicTacToe.getP2Score()+1);
        } else {
            TicTacToe.setP1Score(TicTacToe.getP1Score()+1);
        }
    }

    private static void twoPlayerUserInput() {
        boolean validInput = false;
        while (!validInput && validGameState()) {
            askForMoveInput();
            userInput = input.nextLine();
            if (isAValidMove()) {
                int move = Integer.parseInt(userInput);
                validInput = true;
                game.move(move);
                game.printBoard();
            } else
                System.out.println("Invalid Input");
        }
    }

    private static boolean isAValidMove() {
        return userInput.equals("0") || userInput.equals("1") || userInput.equals("2")
        ||userInput.equals("3") || userInput.equals("4") || userInput.equals("5")
        ||userInput.equals("6") || userInput.equals("7") || userInput.equals("8");
    }

    private static void askForMoveInput() {
        if (game.getCurrentTurn().equals(TicTacToe.p1))
            System.out.println("Player 1(X): Input a move from 0-8");
        else {
            System.out.println("Player 2(O): Input a move from 0-8");
        }
    }
}
