package newsfeed;


import javax.annotation.Nullable;

public class NewsInfo {

    @Nullable
    private final String nextFrom;

    private final int cntNews;

    public NewsInfo(@Nullable String nextFrom, int cntNews) {
        this.nextFrom = nextFrom;
        this.cntNews = cntNews;
    }

    @Nullable
    public String getNextFrom() {
        return nextFrom;
    }

    public int getCntNews() {
        return cntNews;
    }
}
