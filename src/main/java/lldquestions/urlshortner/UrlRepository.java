package lldquestions.urlshortner;

import java.util.concurrent.ConcurrentHashMap;

public class UrlRepository {

    private final ConcurrentHashMap<String, UrlMapping> store = new ConcurrentHashMap<>();

    public void save(UrlMapping mapping) {
        store.put(mapping.getShortKey(), mapping);
    }

    public UrlMapping findByShortKey(String shortKey) {
        return store.get(shortKey);
    }
}
