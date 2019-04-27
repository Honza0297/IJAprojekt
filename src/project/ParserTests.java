package project;

import org.junit.Before;
import org.junit.Test;
import project.game.Parser;
import project.game.TestingReaderWriter;

public class ParserTests {

    public ParserTests() {
    }

    @Before
    public void setUp() {
    }
    @Test
    public void testGetLine()
    {
        TestingReaderWriter readerWriter = new TestingReaderWriter();
        Parser parser = new Parser(readerWriter);
        parser.ParseGameToInner();
    }

    /**
     * Testovaci metoda. Testuje vytvoreni hry dama a zakladni operace.
     */
    /*
    @Test
    public void testCheckersGame() {
        Board board = new Board(8);
        Game game = GameFactory.createCheckersGame(board);

        for (int i = 1; i <= 8; i=i+2) {
            Assert.assertEquals("Na [" + i + ",1] je pesec.", "P[W]"+i+":1", board.getField(i, 1).get().getState());
        }
        for (int i = 2; i <= 8; i=i+2) {
            Assert.assertEquals("Na [" + i + ",2] je pesec.", "P[W]"+i+":2", board.getField(i, 2).get().getState());
        }

        Figure figure;
        Field field;

        figure = board.getField(3, 1).get();
        field = board.getField(2, 2);
        Assert.assertFalse("Presun pesce na [3,2]", game.move(figure, field));

        figure = board.getField(4, 2).get();
        field = board.getField(3, 3);
        Assert.assertTrue("Presun pesce na [3,3]", game.move(figure, field));
        field = board.getField(5, 3);
        Assert.assertFalse("Presun pesce na [5,3]", game.move(figure, field));

        // jeden krok zpet
        game.undo();

        field = board.getField(5, 3);
        Assert.assertTrue("Presun pesce na [5,3]", game.move(figure, field));

    }
*/
}