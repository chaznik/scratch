package assignment.chaznik;

import assignment.chaznik.Mappers.Probability;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board
{
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

    public void setBoardConfiguration(List<Probability> standardSymbols, Probability bonusSymbol)
    {
        Random random = new Random();

        for (Probability probability : standardSymbols) {
            int row = probability.getRow();
            int col = probability.getColumn();
            Map<String, Integer> symbols = probability.getSymbols();
            layout[row][col] = getRandomSymbol(symbols, random);
        }

        int bonusRow = random.nextInt(rows);
        int bonusCol = random.nextInt(columns);
        layout[bonusRow][bonusCol] = getRandomSymbol(bonusSymbol.getSymbols(), random);
    }

    private String getRandomSymbol(Map<String, Integer> symbols, Random random) {
        int sum = symbols.values().stream().mapToInt(Integer::intValue).sum();
        double randomValue = random.nextDouble();

        double cumulativeWeight = 0.0;
        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            cumulativeWeight += (entry.getValue() / (double)sum);
            if (randomValue < cumulativeWeight) {
                return entry.getKey();
            }
        }

        return "";
    }

    public void printBoard()
    {
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                System.out.print(layout[i][j] + " ");
            }
            System.out.println();
        }
    }
}
