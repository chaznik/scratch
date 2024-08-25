package assignment.chaznik;

import assignment.chaznik.Mappers.Probability;
import assignment.chaznik.Mappers.Symbol;
import assignment.chaznik.Mappers.WinCombination;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;
import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        String fileName = "src/main/resources/config.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try (Reader reader = new FileReader(fileName))
        {
            JsonNode rootNode = objectMapper.readTree(reader);

            int rows = rootNode.path("rows").asInt();
            int columns = rootNode.path("columns").asInt();

            JsonNode symbolNode = rootNode.path("symbols");
            Map<String, Symbol> symbols = objectMapper.treeToValue(symbolNode , new TypeReference<Map<String, Symbol>>(){});

            JsonNode standardSymbolNode = rootNode.path("probabilities").path("standard_symbols");
            List<Probability> standardSymbols = objectMapper.treeToValue(standardSymbolNode, new TypeReference<List<Probability>>(){});

            JsonNode bonusSymbolNode = rootNode.path("probabilities").path("bonus_symbols");
            Probability bonusSymbol = objectMapper.treeToValue(bonusSymbolNode, new TypeReference<Probability>(){});

            JsonNode winningCombinationNode = rootNode.path("win_combinations");
            Map<String, WinCombination> winningCombinations = objectMapper.treeToValue(winningCombinationNode, new TypeReference<Map<String, WinCombination>>(){});

            //Initialize empty board
            Board board = new Board(rows, columns);
            board.printBoard();

            //Generate standard and bonus symbols
            board.setBoardConfiguration(standardSymbols, bonusSymbol);
            board.printBoard();
        }
        catch (Exception ex)
        {
            System.out.println("Error reading file: " + fileName);
        }
    }
}
