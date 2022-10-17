import newsfeed.NewsfeedClient;
import newsfeed.NewsfeedManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static utils.PropertiesReader.getProperties;

public class Start {
    public static void main(String[] args) {
        if (args.length < 2 || args[0] == null || args[1] == null) {
            System.err.println("two args required");
            printUsage();
            System.exit(0);
        }
        String hash = args[0];
        int n = 0;
        try {
            n = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("second arg should be positive number");
            printUsage();
            System.exit(0);
        }

        NewsfeedManager newsfeedManager = getNewsfeedManager();

        List<Integer> result = newsfeedManager.getNews(hash, n);

        if (args.length == 3 && args[2] != null) {
            String outFile = args[2];
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
                writer.write(String.valueOf(result));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println(result);
        }
    }

    private static NewsfeedManager getNewsfeedManager() {

        Map<String, String> properties = getProperties();
        String token = properties.get("access_token");
        String host = properties.get("host");

        if (token == null || host == null) {
            System.err.println("some mandatory key isn't specify in properties file");
            System.exit(0);
        }

        NewsfeedClient newsfeedClient = new NewsfeedClient(host, token);

        return new NewsfeedManager(newsfeedClient);
    }

    private static void printUsage() {
        System.out.println("Usage: <hash> <n> [outFile]");
    }
}
