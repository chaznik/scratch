package assignment.chaznik.Board;

import assignment.chaznik.Mappers.Probability;

import java.util.Map;
import java.util.Random;

public class StandardSymbolSetUp implements IBoardSetUp {

    private final Probability probability;

    public StandardSymbolSetUp(Probability probability) {
        this.probability = probability;
    }

    @Override
    public void setSymbols(String[][]layout, Map <String, Integer> symbols, Random random){
        int row = probability.getRow();
        int col = probability.getColumn();

        layout[row][col] = SymbolGenerator.getRandomSymbol(symbols, random);
    }
}