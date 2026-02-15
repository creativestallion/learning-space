package lldquestions;

import lldquestions.ratelimiter.RateLimiter;
import lldquestions.ratelimiter.TokenBucketRateLimiter;
import lldquestions.urlshortner.Base62Encoder;
import lldquestions.urlshortner.IdGenerator;
import lldquestions.urlshortner.UrlRepository;
import lldquestions.urlshortner.UrlShortenerService;

public class Mainnnn {
    static void main() {
//        RateLimiter rateLimiter = new TokenBucketRateLimiter(10, 2);
//
//        String userId = "user-123";
//
//        for (int i = 0; i < 20; i++) {
//            System.out.println(rateLimiter.allowRequest(userId));
//        }

        UrlShortenerService service =
                new UrlShortenerService(
                        new IdGenerator(),
                        new Base62Encoder(),
                        new UrlRepository()
                );

        String shortUrl = service.shortenUrl("https://example.com/very/long/url");
        System.out.println(shortUrl);

        System.out.println(service.getOriginalUrl(shortUrl));
    }
}
