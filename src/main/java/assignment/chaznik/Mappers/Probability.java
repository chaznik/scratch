package assignment.chaznik.Mappers;

import java.util.Map;

public class Probability
{
    private int column;
    private int row;
    private Map<String, Integer> symbols;

    public Probability(int column, int row, Map<String, Integer> symbols) {
        this.column = column;
        this.row = row;
        this.symbols = symbols;
    }

    public Probability() {}

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Map<String, Integer> getSymbols() {
        return symbols;
    }

    @Override
    public String toString() {
        return "Probability{" + "column=" + column + ", row=" + row + ", symbols=" + symbols + "}";
    }
}
