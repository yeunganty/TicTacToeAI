import model.TicTacToe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTicTacToe {
    TicTacToe ttt;

    @BeforeEach
    void runBefore() {
        ttt = new TicTacToe();
    }

    @Test
    void testGetEmptySpots() {
        assertEquals(9,ttt.getEmptySpots().size());
        ttt.move(2);
        assertEquals(8,ttt.getEmptySpots().size());
        ttt.move(4);
        assertEquals(7,ttt.getEmptySpots().size());
        List<Integer> emptySpots = new ArrayList<>();
        emptySpots.add(0);
        emptySpots.add(1);
        emptySpots.add(3);
        emptySpots.add(5);
        emptySpots.add(6);
        emptySpots.add(7);
        emptySpots.add(8);
        assertEquals(emptySpots,ttt.getEmptySpots());
    }

    @Test
    void testMove() {
        ttt.move(2);
        for (Integer i : ttt.getEmptySpots()) {
            assertTrue(i != 2);
        }
        assertEquals(TicTacToe.p2, ttt.getCurrentTurn());
        ttt.move(5);
        for (Integer i : ttt.getEmptySpots()) {
            assertTrue(i != 2);
            assertTrue(i != 5);
        }
        assertEquals(TicTacToe.p1, ttt.getCurrentTurn());
    }

    @Test
    void testPrintBoard() {
        ttt.printBoard();
        ttt.move(3);
        ttt.move(5);
        ttt.printBoard();
    }
}
