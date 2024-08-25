package assignment.chaznik;

import assignment.chaznik.Board.Board;
import assignment.chaznik.Mappers.Probability;
import assignment.chaznik.Mappers.Symbol;
import assignment.chaznik.Mappers.WinCombination;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

public class GameInitialzer {

    private final ObjectMapper objectMapper = new ObjectMapper();


    public void Initialize(String configFilePath, int betAmount) {
        try {
            // Load configuration
            JsonNode rootNode = loadConfiguration(configFilePath);

            // Get rows and columns
            int rows = rootNode.path("rows").asInt();
            int columns = rootNode.path("columns").asInt();

            // Load symbols
            Map<String, Symbol> symbols = loadSymbols(rootNode);

            // Load probabilities
            List<Probability> standardSymbols = loadStandardSymbols(rootNode);
            Probability bonusSymbol = loadBonusSymbol(rootNode);

            // Load winning combinations
            Map<String, WinCombination> winningCombinations = loadWinningCombinations(rootNode);
            
            // Initialize and configure the board
            Board board = new Board(rows, columns);
            board.configureBoard(standardSymbols, bonusSymbol);

            // Evaluate results and calculate reward
            ResultGenerator resultGenerator = new ResultGenerator(symbols);
            int reward = resultGenerator.calculateReward(board.getLayout(), winningCombinations, betAmount);
            String appliedBonusSymbol = resultGenerator.getAppliedBonusSymbol();

            Map<String, List<String>> outcomes = resultGenerator.checkWinningOutComes(board.getLayout(), winningCombinations);

            // Generate output
            ObjectNode resultNode = objectMapper.createObjectNode();

            // Add matrix to output
            ArrayNode matrixNode = resultNode.putArray("matrix");
            for (int i = 0; i < rows; i++) {
                ArrayNode rowNode = matrixNode.addArray();
                for (int j = 0; j < columns; j++) {
                    rowNode.add(board.getLayout()[i][j]);
                }
            }

            // Add reward
            resultNode.put("reward", reward);

            // Add winning combinations
            if (reward > 0) {
                ObjectNode winningCombinationsNode = resultNode.putObject("applied_winning_combinations");
                for (Map.Entry<String, List<String>> entry : outcomes.entrySet()) {
                    ArrayNode combinationArray = winningCombinationsNode.putArray(entry.getKey());
                    for (String combination : entry.getValue()) {
                        combinationArray.add(combination);
                    }
                }
            }

            // Add applied bonus symbol
            if (appliedBonusSymbol != null && reward > 0) {
                resultNode.put("applied_bonus_symbol", appliedBonusSymbol);
            }

            // Convert resultNode to a pretty-printed JSON string
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultNode);
            System.out.println(jsonString);
        }
        catch (Exception ex) {
            System.out.println("Error loading configuration");
        }
    }

    private JsonNode loadConfiguration(String fileName) throws Exception {
        try (Reader reader = new FileReader(fileName)) {
            return objectMapper.readTree(reader);
        }
    }

    private Map<String, Symbol> loadSymbols(JsonNode rootNode) throws Exception {
        JsonNode symbolNode = rootNode.path("symbols");
        return objectMapper.treeToValue(symbolNode, new TypeReference<Map<String, Symbol>>() {});
    }

    private List<Probability> loadStandardSymbols(JsonNode rootNode) throws Exception {
        JsonNode standardSymbolNode = rootNode.path("probabilities").path("standard_symbols");
        return objectMapper.treeToValue(standardSymbolNode, new TypeReference<List<Probability>>() {});
    }

    private Probability loadBonusSymbol(JsonNode rootNode) throws Exception {
        JsonNode bonusSymbolNode = rootNode.path("probabilities").path("bonus_symbols");
        return objectMapper.treeToValue(bonusSymbolNode, new TypeReference<Probability>() {});
    }

    private Map<String, WinCombination> loadWinningCombinations(JsonNode rootNode) throws Exception {
        JsonNode winningCombinationNode = rootNode.path("win_combinations");
        return objectMapper.treeToValue(winningCombinationNode, new TypeReference<Map<String, WinCombination>>() {});
    }
}
