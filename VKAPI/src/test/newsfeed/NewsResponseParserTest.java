package newsfeed;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class NewsResponseParserTest {

    private static String testResponseWithoutNextForm;
    private static String testResponseWithNextForm;

    @BeforeAll
    public static void setup() throws IOException {
        String resourcesDir = "src/test/newsfeed/resources/";

        testResponseWithoutNextForm = new String(
                Files.readAllBytes(Paths.get(resourcesDir + "responseWithoutNextFrom.json")));
        testResponseWithNextForm = new String(
                Files.readAllBytes(Paths.get(resourcesDir + "responseWithNextFrom.json")));
    }


    @Test
    public void parseResponseWithoutNextForm() {
        NewsResponseParser parser = new NewsResponseParser();

        NewsInfo infoWithoutNextForm = parser.parseResponse(testResponseWithoutNextForm);

        assertEquals(infoWithoutNextForm.getCntNews(), 2);
        assertNull(infoWithoutNextForm.getNextFrom());
    }

    @Test
    public void parseResponseWithNextForm() {
        NewsResponseParser parser = new NewsResponseParser();

        NewsInfo infoWithNextForm = parser.parseResponse(testResponseWithNextForm);

        assertEquals(infoWithNextForm.getCntNews(), 30);
        assertNotNull(infoWithNextForm.getNextFrom());
        assertEquals(infoWithNextForm.getNextFrom(), "30/-204853896_20280");
    }
}
