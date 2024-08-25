package assignment.chaznik.Board;

import java.util.Map;
import java.util.Random;

public class BonusSymbolSetUp implements IBoardSetUp {

    private final int rows;
    private final int columns;

    public BonusSymbolSetUp(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public void setSymbols(String[][] layout, Map<String, Integer> symbols, Random random) {
        int bonusRow = random.nextInt(rows);
        int bonusCol = random.nextInt(columns);

        layout[bonusRow][bonusCol] = SymbolGenerator.getRandomSymbol(symbols, random);
    }
}
