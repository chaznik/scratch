package assignment.chaznik.Board;

import assignment.chaznik.Mappers.Probability;

import java.util.List;
import java.util.Random;

public class Board {
    private final int rows;
    private final int columns;
    private final String[][] layout;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.layout = new String[rows][columns];
    }

    public String[][] getLayout() {
        return layout;
    }

    public void configureBoard(List<Probability> standardSymbols, Probability bonusSymbol) {
        IBoardSetUp setStandardSymbols;

        for (Probability probability : standardSymbols) {
            setStandardSymbols = new StandardSymbolSetUp(probability);
            setStandardSymbols.setSymbols(layout, probability.getSymbols(), new Random());
        }

        IBoardSetUp setBonusSymbols = new BonusSymbolSetUp(rows, columns);
        setBonusSymbols.setSymbols(layout, bonusSymbol.getSymbols(), new Random());
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(layout[i][j] + " ");
            }
            System.out.println();
        }
    }
}
