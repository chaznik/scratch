package assignment.chaznik.Board;

import java.util.Map;
import java.util.Random;

public interface IBoardSetUp {
    void setSymbols(String[][] layout, Map<String, Integer> symbols, Random random);
}
