package newsfeed;

import http.UrlReader;

import javax.annotation.Nonnull;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class NewsfeedClient {

    @Nonnull
    private final String host;
    @Nonnull
    private final String token;
    @Nonnull
    private final UrlReader reader;
    @Nonnull
    private final NewsResponseParser parser;

    public NewsfeedClient(
            @Nonnull String host,
            @Nonnull String token) {
        this.host = requireNonNull(host, "host");
        this.token = requireNonNull(token, "token");
        this.reader = new UrlReader();
        this.parser = new NewsResponseParser();
    }

    public NewsfeedClient(
            @Nonnull String host,
            @Nonnull String token,
            @Nonnull UrlReader reader) {
        this.host = requireNonNull(host, "host");
        this.token = requireNonNull(token, "token");
        this.reader = reader;
        this.parser = new NewsResponseParser();
    }

    @Nonnull
    public int cntNews(@Nonnull String hash, long startTime, long endTime) {
        int cntNews = 0;
        String response = reader.readAsText(createUrl(hash, startTime, endTime));
        NewsInfo newsInfo = parser.parseResponse(response);
        while (newsInfo.getNextFrom() != null) {
            cntNews += newsInfo.getCntNews();
            response = reader.readAsText(createUrl(hash, startTime, endTime, newsInfo.getNextFrom()));
            newsInfo = parser.parseResponse(response);
        }
        return cntNews + newsInfo.getCntNews();
    }

    @Nonnull
    private String createUrl(@Nonnull String hash, long startTime, long endTime, @Nonnull String nextFrom) {
        return createUrl(hash, startTime, endTime) + "&" +
                "start_from=" + nextFrom;
    }

    @Nonnull
    private String createUrl(@Nonnull String hash, long startTime, long endTime) {
        String encodeHash;

        try {
            encodeHash = URLEncoder.encode("#" + hash, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return "https://" + host + "/method/newsfeed.search?" +
                "access_token=" + token + "&" +
                "q=" + encodeHash + "&" +
                "start_time=" + startTime + "&" +
                "end_time=" + endTime + "&" +
                "v=5.131";
    }

}
