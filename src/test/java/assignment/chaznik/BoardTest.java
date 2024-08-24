package assignment.chaznik;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest
{
    Board board = new Board(3, 3);

    @Test
    public void getRowsAndColumns_givenThreeRows3Columns_shouldGenerateThreeByThreeBoard()
    {
        Assert.assertEquals(3, board.getRows());
        Assert.assertEquals(3, board.getColumns());
    }

    @Test
    public void getLayout_givenThreeRows3Columns_shouldGenerateThreeByThreeBoardWithNulls()
    {
        String[] emptyRow = new String[]{null, null, null};

        String[][] layout = board.getLayout();

        Assert.assertArrayEquals(emptyRow, layout[0]);
        Assert.assertArrayEquals(emptyRow, layout[1]);
        Assert.assertArrayEquals(emptyRow, layout[2]);
    }
}
