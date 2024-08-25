package assignment.chaznik;

public class GameEngine {

    public static void main(String[] args) {

        String configFilePath = "";//"src/main/resources/config.json";
        int betAmount = 0;

        // Parse command-line arguments
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--config":
                    configFilePath = args[i + 1];
                    break;
                case "--betting-amount":
                    betAmount = Integer.parseInt(args[i + 1]);
                    break;
            }
        }

        // Ensure required arguments are provided
        if (configFilePath == null || betAmount <= 0) {
            System.out.println("Usage: java -jar target/scratch-1.0-SNAPSHOT.jar --config <config-file-path> --betting-amount <amount>");
            return;
        }

        GameInitialzer game = new GameInitialzer();
        game.Initialize(configFilePath, betAmount);
    }
}
