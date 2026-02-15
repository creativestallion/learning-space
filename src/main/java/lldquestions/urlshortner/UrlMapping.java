package lldquestions.urlshortner;

public class UrlMapping {
    private final long id;
    private final String shortKey;
    private final String longUrl;

    public UrlMapping(long id, String shortKey, String longUrl) {
        this.id = id;
        this.shortKey = shortKey;
        this.longUrl = longUrl;
    }

    public String getShortKey() {
        return shortKey;
    }

    public String getLongUrl() {
        return longUrl;
    }
}