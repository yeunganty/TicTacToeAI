import ai.BotMiniMax;
import model.TicTacToe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBotMiniMax {
    private TicTacToe game;
    @BeforeEach
    void runBefore() {
        game = new TicTacToe();
    }

    @Test
    void testBotMiniMaxScore() {
        game.move(2);
        game.move(0);
        game.move(3);
        game.move(8);
        game.move(5);
        game.move(7);
        game.move(6);
        BotMiniMax bot = new BotMiniMax(game.getCurrentTurn());
        assertEquals(0,bot.score(game,0));
        game.move(4);
        assertEquals(10,bot.score(game,0));
    }

    @Test
    void testBotMiniMax2Left() {
        game.move(2);
        game.move(0);
        game.move(3);
        game.move(8);
        game.move(5);
        game.move(7);
        game.move(6);
        game.printBoard();
        BotMiniMax bot = new BotMiniMax(game.getCurrentTurn());
        System.out.println(bot.miniMax(game,0));
        game.printBoard();
    }

    @Test
    void testBotMini3Left() {
        game.move(2);
        game.move(0);
        game.move(3);
        game.move(8);
        game.move(5);
        game.move(7);
        game.printBoard();
        BotMiniMax bot = new BotMiniMax(game.getCurrentTurn());
        System.out.println(bot.miniMax(game,0));
        game.printBoard();
    }

    @Test
    void testBotMiniMaxEmpty() {
        game.printBoard();
        BotMiniMax bot = new BotMiniMax(game.getCurrentTurn());
        System.out.println(bot.miniMax(game,0));
        game.printBoard();
        BotMiniMax bot2 = new BotMiniMax(game.getCurrentTurn());
        System.out.println(bot2.miniMax(game,0));
        game.printBoard();
        BotMiniMax bot3 = new BotMiniMax(game.getCurrentTurn());
        System.out.println(bot3.miniMax(game,0));
        game.printBoard();
        BotMiniMax bot4 = new BotMiniMax(game.getCurrentTurn());
        System.out.println(bot4.miniMax(game,0));
        game.printBoard();
        BotMiniMax bot5 = new BotMiniMax(game.getCurrentTurn());
        System.out.println(bot5.miniMax(game,0));
        game.printBoard();
        BotMiniMax bot6 = new BotMiniMax(game.getCurrentTurn());
        System.out.println(bot6.miniMax(game,0));
        game.printBoard();
        BotMiniMax bot7 = new BotMiniMax(game.getCurrentTurn());
        System.out.println(bot7.miniMax(game,0));
        game.printBoard();
        BotMiniMax bot8 = new BotMiniMax(game.getCurrentTurn());
        System.out.println(bot8.miniMax(game,0));
        game.printBoard();
        BotMiniMax bot9 = new BotMiniMax(game.getCurrentTurn());
        System.out.println(bot9.miniMax(game,0));
        game.printBoard();

    }

}
