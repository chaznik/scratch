package assignment.chaznik;

import assignment.chaznik.Mappers.Symbol;
import assignment.chaznik.Mappers.WinCombination;
import assignment.chaznik.Enums.Impact;
import assignment.chaznik.Enums.SymbolType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultGeneratorTests {

    private ResultGenerator resultGenerator;
    private String[][] boardLayout;
    private Map<String, WinCombination> winCombinations;

    @Before
    public void setUp() {
        // Initialize symbols using the correct constructor
        Map<String, Symbol> symbols = new HashMap<>();
        symbols.put("A", new Symbol(0, Impact.NONE, 5, SymbolType.STANDARD));
        symbols.put("B", new Symbol(0, Impact.NONE, 3, SymbolType.STANDARD));
        symbols.put("C", new Symbol(0, Impact.NONE, 2.5, SymbolType.STANDARD));
        symbols.put("D", new Symbol(0, Impact.NONE, 2, SymbolType.STANDARD));
        symbols.put("E", new Symbol(0, Impact.NONE, 1.2, SymbolType.STANDARD));
        symbols.put("F", new Symbol(0, Impact.NONE, 1, SymbolType.STANDARD));
        symbols.put("10x", new Symbol(0, Impact.MULTIPLY, 10, SymbolType.BONUS));
        symbols.put("5x", new Symbol(0, Impact.MULTIPLY, 5, SymbolType.BONUS));
        symbols.put("+1000", new Symbol(1000, Impact.ADD, 0, SymbolType.BONUS));
        symbols.put("+500", new Symbol(500, Impact.ADD, 0, SymbolType.BONUS));
        symbols.put("MISS", new Symbol(0, Impact.NONE, 0, SymbolType.BONUS));

        resultGenerator = new ResultGenerator(symbols);

        winCombinations = new HashMap<>();
        winCombinations.put("same_symbol_3_times", new WinCombination(
                1.0, "same_symbols", 3, "same_symbols", null));
        winCombinations.put("same_symbol_4_times", new WinCombination(
                1.5, "same_symbols", 4, "same_symbols", null));
        winCombinations.put("same_symbol_5_times", new WinCombination(
                2.0, "same_symbols", 5, "same_symbols", null));
        winCombinations.put("same_symbol_6_times", new WinCombination(
                3.0, "same_symbols", 6, "same_symbols", null));
        winCombinations.put("same_symbol_7_times", new WinCombination(
                5.0, "same_symbols", 7, "same_symbols", null));
        winCombinations.put("same_symbol_8_times", new WinCombination(
                10.0, "same_symbols", 8, "same_symbols", null));
        winCombinations.put("same_symbol_9_times", new WinCombination(
                20.0, "same_symbols", 9, "same_symbols", null));
        winCombinations.put("same_symbols_horizontally", new WinCombination(
                2.0, "linear_symbols", 0, "horizontally_linear_symbols",
                new String[][]{
                        {"0:0", "0:1", "0:2"},
                        {"1:0", "1:1", "1:2"},
                        {"2:0", "2:1", "2:2"}
                }));

        winCombinations.put("same_symbols_vertically", new WinCombination(
                2.0, "linear_symbols", 0, "vertically_linear_symbols",
                new String[][]{
                        {"0:0", "1:0", "2:0"},
                        {"0:1", "1:1", "2:1"},
                        {"0:2", "1:2", "2:2"}
                }));

        winCombinations.put("same_symbols_diagonally_left_to_right", new WinCombination(
                5.0, "linear_symbols", 0, "ltr_diagonally_linear_symbols",
                new String[][]{
                        {"0:0", "1:1", "2:2"}
                }));

        winCombinations.put("same_symbols_diagonally_right_to_left", new WinCombination(
                5.0, "linear_symbols", 0, "rtl_diagonally_linear_symbols",
                new String[][]{
                        {"0:2", "1:1", "2:0"}
                }));
    }


    @Test
    public void testCheckWinningOutcomes_givenASymbolAppearsThreeTimes() {

        boardLayout = new String[][] {
                {"A", "B", "C"},
                {"A", "A", "E"},
                {"D", "F", "MISS"}
        };

        Map<String, List<String>> outcomes = resultGenerator.checkWinningOutComes(boardLayout, winCombinations);

        Assert.assertTrue("Expected to find 'A' in winning outcomes", outcomes.containsKey("A"));
        Assert.assertEquals("Expected 'A' to have one winning combination", 1, outcomes.get("A").size());
        Assert.assertTrue("Expected winning combination 'same_symbol_3_times'", outcomes.get("A").contains("same_symbol_3_times"));
    }

    @Test
    public void testCheckWinningOutcomes_givenSymbol_A_AppearsThreeTimesAndHorizontally() {

        boardLayout = new String[][] {
                {"B", "B", "C"},
                {"A", "A", "A"},
                {"D", "F", "MISS"}
        };

        Map<String, List<String>> outcomes = resultGenerator.checkWinningOutComes(boardLayout, winCombinations);

        Assert.assertTrue("Expected to find 'A' in winning outcomes", outcomes.containsKey("A"));
        Assert.assertEquals("Expected 'A' to have one winning combination", 2, outcomes.get("A").size());
        Assert.assertTrue("Expected winning combination 'same_symbol_3_times'", outcomes.get("A").contains("same_symbol_3_times"));
        Assert.assertTrue("Expected winning combination 'same_symbols_horizontally'", outcomes.get("A").contains("same_symbols_horizontally"));
    }

    @Test
    public void testCheckWinningOutcomes_givenWinningCombinationsForSymbolAAndSymbolB() {

        boardLayout = new String[][] {
                {"B", "B", "C"},
                {"B", "A", "A"},
                {"D", "A", "MISS"}
        };

        Map<String, List<String>> outcomes = resultGenerator.checkWinningOutComes(boardLayout, winCombinations);

        Assert.assertTrue("Expected to find 'A' in winning outcomes", outcomes.containsKey("A"));
        Assert.assertTrue("Expected to find 'B' in winning outcomes", outcomes.containsKey("B"));

        Assert.assertEquals("Expected 'A' to have one winning combination", 1, outcomes.get("A").size());
        Assert.assertEquals("Expected 'B' to have one winning combination", 1, outcomes.get("B").size());

        Assert.assertTrue("Expected winning combination 'same_symbol_3_times'", outcomes.get("A").contains("same_symbol_3_times"));
        Assert.assertTrue("Expected winning combination 'same_symbol_3_times'", outcomes.get("B").contains("same_symbol_3_times"));
    }

    @Test
    public void testCalculateRewardWithoutBonus() {

        boardLayout = new String[][] {
                {"A", "B", "C"},
                {"A", "A", "E"},
                {"D", "F", "MISS"}
        };

        int reward = resultGenerator.calculateReward(boardLayout, winCombinations, 100);

        // (betAmount: 100) * (A multiplier: 5) * (same_symbol_3_times: 1)
        // 100 * 5 * 1 = 500
        Assert.assertEquals("Expected reward to be 500", 500, reward);
    }

    @Test
    public void testCalculateReward_SameSymbol_TwoWinningOutcomes() {

        boardLayout = new String[][] {
                {"A", "B", "C"},
                {"A", "B", "E"},
                {"A", "F", "MISS"}
        };

        int reward = resultGenerator.calculateReward(boardLayout, winCombinations, 100);

        // (betAmount: 100) * (A multiplier: 5) * (same_symbol_3_times: 1) * (same_symbols_horizontally: 2.0)
        // 100 * 5 * 1 * 2.0 = 1000
        Assert.assertEquals("Expected reward to be 1000", 1000, reward);
    }

    @Test
    public void testCalculateRewardWithBonus() {
        // Update the board with a bonus symbol
        boardLayout = new String[][] {
                {"A", "B", "C"},
                {"A", "A", "E"},
                {"D", "F", "10x"}
        };
        int reward = resultGenerator.calculateReward(boardLayout, winCombinations, 100);

        // (betAmount: 100) * (A multiplier: 5) * (same_symbol_3_times: 1) * (10x multiplier: 10)
        // 100 * 5 * 1 * 10 = 5000
        Assert.assertEquals("Expected reward to be 5000", 5000, reward);
    }

    @Test
    public void testApplyBonusSymbol() {

        boardLayout = new String[][] {
                {"A", "B", "C"},
                {"A", "A", "E"},
                {"D", "F", "5x"}
        };

        resultGenerator.calculateReward(boardLayout, winCombinations, 100);

        Assert.assertEquals("Expected applied bonus symbol to be '5x'", "5x", resultGenerator.getAppliedBonusSymbol());
    }
}
