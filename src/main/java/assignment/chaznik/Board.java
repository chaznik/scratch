package assignment.chaznik;

import java.util.List;
import java.util.Map;

public class Board
{
    private int rows;
    private int columns;
    private String[][] layout;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.layout = generateBoard(rows, columns);
    }

    public Board(){}

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String[][] getLayout() {
        return layout;
    }

    private String[][] generateBoard(int rows, int columns)
    {
        return new String[rows][columns];
    }

    public void printBoard()
    {
        for(int i = 0; i < columns; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                System.out.print(layout[i][j] + " ");
            }
            System.out.println();
        }
    }
}
