/*
 *  FIT VUT
 * Project for IJA, 2018/2019
 * Authors: Jan Beran (xberan43)
 *           Daniel Bubenicek (xbuben05)
 *
 * ParserTest class.
 *
 * */
package project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import project.game.Board;
import project.game.InnerGameNotation;
import project.game.Parser;
import project.game.TestingReaderWriter;

public class ParserTests {

    public ParserTests() {
    }

    @Before
    public void setUp() {
    }
    @Test
    public void testParseGameToInner()
    {
        TestingReaderWriter readerWriter = new TestingReaderWriter();
        Parser parser = new Parser(readerWriter, new Board(8));
        InnerGameNotation gameNotation = parser.ParseGameToInner();
        Assert.assertNotNull(gameNotation);
    }
}