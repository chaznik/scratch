package assignment.chaznik;

import assignment.chaznik.Mappers.WinCombination;
import assignment.chaznik.Mappers.Symbol;

import java.lang.reflect.Array;
import java.util.*;

public class ResultGenerator {

    public static final String SAME_SYMBOLS = "same_symbols";
    public static final String LINEAR_SYMBOLS = "linear_symbols";

    private String appliedBonusSymbol = null;
    private final Map<String, Symbol> symbols;

    public ResultGenerator(Map<String, Symbol> symbols) {
        this.symbols = symbols;
    }

    public Map<String, List<String>> checkWinningOutComes(String[][] boardLayout, Map<String, WinCombination> possibleWinningCombinations) {
        Map<String, List<String>> winningOutcomes = new HashMap<>();
        Map<String, Integer> symbolCount = getSymbolCount(boardLayout);

        getSameSymbolWins(symbolCount, possibleWinningCombinations, winningOutcomes);
        getLinearSymbolWins(boardLayout, possibleWinningCombinations, winningOutcomes);

        return winningOutcomes;
    }

    private void getSameSymbolWins(Map<String, Integer> symbolCount, Map<String, WinCombination> possibleWinningCombinations, Map<String, List<String>> winningOutcomes) {
        for (String currentSymbol : symbolCount.keySet()) {
            int count = symbolCount.get(currentSymbol);

            if (count < 3) {
                continue;
            }

            for (Map.Entry<String, WinCombination> entry : possibleWinningCombinations.entrySet()) {
                WinCombination winCombination = entry.getValue();

                if (SAME_SYMBOLS.equals(winCombination.getWhen()) && winCombination.getCount() == count) {
                    winningOutcomes.computeIfAbsent(currentSymbol, k -> new ArrayList<>()).add(entry.getKey());
                }
            }
        }
    }

    private void getLinearSymbolWins(String[][] boardLayout, Map<String, WinCombination> possibleWinningCombinations, Map<String, List<String>> winningOutcomes) {
        for (Map.Entry<String, WinCombination> entry : possibleWinningCombinations.entrySet()) {
            WinCombination winCombination = entry.getValue();

            if (LINEAR_SYMBOLS.equals(winCombination.getWhen())) {
                for (String[] area : winCombination.getCoveredAreas()) {
                    if (checkLinearCombination(boardLayout, area)) {
                        String firstPosition = area[0];
                        int row = Integer.parseInt(firstPosition.split(":")[0]);
                        int col = Integer.parseInt(firstPosition.split(":")[1]);
                        String symbol = boardLayout[row][col];

                        winningOutcomes.computeIfAbsent(symbol, k -> new ArrayList<>()).add(entry.getKey());
                    }
                }
            }
        }
    }

    private boolean checkLinearCombination(String[][] boardLayout, String[] area) {
        String firstSymbol = null;

        for (String position : area) {
            int row = Integer.parseInt(position.split(":")[0]);
            int col = Integer.parseInt(position.split(":")[1]);

            String currentSymbol = boardLayout[row][col];

            if (firstSymbol == null) {
                firstSymbol = currentSymbol;
            }
            else if (!firstSymbol.equals(currentSymbol)) {
                return false;
            }
        }

        return true;
    }

    private Map<String, Integer> getSymbolCount(String[][] boardLayout) {
        Map<String, Integer> symbolCount = new HashMap<>();

        for (String[] row : boardLayout) {
            for (String symbol : row) {
                symbolCount.merge(symbol, 1, Integer::sum);
            }
        }
        return symbolCount;
    }

    public String getAppliedBonusSymbol() {
        return appliedBonusSymbol;
    }

    public int calculateReward(String[][] boardLayout, Map<String, WinCombination> possibleWinningCombinations, int betAmount) {
        Map<String, List<String>> winningOutcomes = checkWinningOutComes(boardLayout, possibleWinningCombinations);
        int totalReward = 0;

        for (Map.Entry<String, List<String>> entry : winningOutcomes.entrySet()) {
            String symbol = entry.getKey();
            List<String> winCombinations = entry.getValue();
            double symbolReward = betAmount;

            // Apply the symbol's multiplier
            Symbol symbolDefinition = symbols.get(symbol);
            if (symbolDefinition != null) {
                symbolReward *= symbolDefinition.getRewardMultiplier();
            }

            // Multiply rewards for each winning combination for this symbol, including linear combinations
            for (String winCombinationKey : winCombinations) {
                WinCombination winCombination = possibleWinningCombinations.get(winCombinationKey);
                symbolReward *= winCombination.getRewardMultiplier();
            }

            totalReward += symbolReward;
        }

        if (totalReward > 0) {
            totalReward = applyBonusSymbols(boardLayout, totalReward);
        }

        return totalReward;
    }

    private int applyBonusSymbols(String[][] boardLayout, int totalReward) {
        for (String[] row : boardLayout) {
            for (String cellSymbol : row) {
                switch (cellSymbol) {
                    case "+1000" -> {
                        totalReward += 1000;
                        appliedBonusSymbol = cellSymbol;
                    }
                    case "+500" -> {
                        totalReward += 500;
                        appliedBonusSymbol = cellSymbol;
                    }
                    case "10x" -> {
                        totalReward *= 10;
                        appliedBonusSymbol = cellSymbol;
                    }
                    case "5x" -> {
                        totalReward *= 5;
                        appliedBonusSymbol = cellSymbol;
                    }
                    case "MISS" -> {
                        appliedBonusSymbol = cellSymbol;
                    }
                }
            }
        }

        return totalReward;
    }
}
