package lldquestions;

import lldquestions.ratelimiter.RateLimiter;
import lldquestions.ratelimiter.TokenBucketRateLimiter;

public class Mainnnn {
    static void main() {
        RateLimiter rateLimiter = new TokenBucketRateLimiter(10, 2);

        String userId = "user-123";

        for (int i = 0; i < 20; i++) {
            System.out.println(rateLimiter.allowRequest(userId));
        }
    }
}
