package lldquestions.urlshortner;

public class UrlShortenerService {

    private final IdGenerator idGenerator;
    private final Base62Encoder encoder;
    private final UrlRepository repository;

    public UrlShortenerService(
            IdGenerator idGenerator,
            Base62Encoder encoder,
            UrlRepository repository) {
        this.idGenerator = idGenerator;
        this.encoder = encoder;
        this.repository = repository;
    }

    public String shortenUrl(String longUrl) {
        long id = idGenerator.nextId();
        String shortKey = encoder.encode(id);

        UrlMapping mapping = new UrlMapping(id, shortKey, longUrl);
        repository.save(mapping);

        return shortKey;
    }

    public String getOriginalUrl(String shortKey) {
        UrlMapping mapping = repository.findByShortKey(shortKey);
        if (mapping == null) {
            throw new IllegalArgumentException("Short URL not found");
        }
        return mapping.getLongUrl();
    }
}

/*
UrlShortenerService
 ├── shorten(longUrl)
 ├── resolve(shortKey)
 ├── uses IdGenerator
 ├── uses Base62Encoder
 ├── uses UrlRepository

IdGenerator
 └── nextId()

Base62Encoder
 └── encode(id)

UrlRepository
 ├── save(shortKey, longUrl)
 ├── findLongUrl(shortKey)
 ├── findShortKey(longUrl)
 */