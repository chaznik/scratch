package assignment.chaznik.Board;

import java.util.Map;
import java.util.Random;

public class SymbolGenerator {

    public static String getRandomSymbol(Map<String, Integer> symbols, Random random) {
        int totalWeight = symbols.values().stream().mapToInt(Integer::intValue).sum();
        double randomValue = random.nextDouble();

        double cumulativeWeight = 0;
        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            cumulativeWeight += (double) entry.getValue() /totalWeight;
            if (randomValue < cumulativeWeight) {
                return entry.getKey();
            }
        }

        return "";
    }
}
