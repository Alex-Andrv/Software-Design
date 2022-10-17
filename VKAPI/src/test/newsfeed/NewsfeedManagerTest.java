package newsfeed;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class NewsfeedManagerTest {

    private NewsfeedManager newsfeedManager;
    private final Random rand = new Random(0);

    @Mock
    private NewsfeedClient client;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        newsfeedManager = new NewsfeedManager(client);
    }

    @Test
    public void newsfeedManagerTest() {
        when(client.cntNews(any(String.class), anyLong(), anyLong()))
                .thenReturn(rand.nextInt(10));

        List<Integer> ans = newsfeedManager.getNews("новости",10);

        assertThat(ans).hasSize(10);
    }
}
