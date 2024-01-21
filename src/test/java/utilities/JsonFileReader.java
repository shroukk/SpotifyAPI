package utilities;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileReader {
    public static String jsonLoader(String filePath) {
        String jsonContent = null;
        try {
            // Read the content of the JSON file into a string
             jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("File not found at "+ filePath);
        }
        return  jsonContent;
    }
}
