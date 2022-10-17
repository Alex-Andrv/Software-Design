package newsfeed;

import http.UrlReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static utils.PropertiesReader.getProperties;

public class NewsfeedClientTest {

    private static String host;
    private static String accessToken;
    private static NewsfeedClient client;

    @Mock
    private static UrlReader urlReader;

    @BeforeAll
    public static void beforeAll() {
        Map<String, String> properties = getProperties();

        host = properties.get("host");
        accessToken = properties.get("access_token");

        if (accessToken == null) {
            throw new IllegalStateException("access_token don't specify");
        }
    }

    @BeforeEach
    public void beforeEach() throws Exception {
        MockitoAnnotations.initMocks(this);
        client = new NewsfeedClient(host, accessToken, urlReader);
    }

    @Test
    public void cntNewsTest() throws IOException {
        String resourcesDir = "src/test/newsfeed/resources/";

        when(urlReader.readAsText(any(String.class)))
                .thenReturn(getResult(resourcesDir + "1.json"))
                .thenReturn(getResult(resourcesDir + "2.json"))
                .thenReturn(getResult(resourcesDir + "3.json"));

        long entTime = Instant.now().getEpochSecond();
        long startTime = Instant.now().getEpochSecond() - 5 * 60 * 60;

        int cntNews = client.cntNews("pop", startTime, entTime);
        assertEquals(cntNews, 90);
    }

    private String getResult(String path) throws IOException {
        return new String(
                Files.readAllBytes(Paths.get(path)));
    }
}
