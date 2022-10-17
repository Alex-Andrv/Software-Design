package newsfeed;

import javax.annotation.Nonnull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class NewsfeedManager {

    private static long ONE_HOUR_TO_SECONDS = 60L * 60L;

    @Nonnull
    private final NewsfeedClient newsfeedClient;

    public NewsfeedManager(@Nonnull NewsfeedClient newsfeedClient) {
        this.newsfeedClient = newsfeedClient;
    }

    @Nonnull
    public List<Integer> getNews(@Nonnull String hash, int n) {
        long now = Instant.now().getEpochSecond();
        long startTime = Instant.now().getEpochSecond() - n * ONE_HOUR_TO_SECONDS;

        List<Integer> results = new ArrayList<>();
        for (long endTime = startTime + ONE_HOUR_TO_SECONDS; endTime <= now; endTime += ONE_HOUR_TO_SECONDS) {
            results.add(newsfeedClient.cntNews(hash, startTime, endTime));
            startTime = endTime;
        }

        return results;
    }
}
