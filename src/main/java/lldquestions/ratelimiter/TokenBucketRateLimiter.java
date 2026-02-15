package lldquestions.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {

    private final ConcurrentHashMap<String, TokenBucket> bucketMap = new ConcurrentHashMap<>();

    private final long capacity;
    private final long refillRatePerSecond;

    public TokenBucketRateLimiter(long capacity, long refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    @Override
    public boolean allowRequest(String key) {
        TokenBucket bucket = bucketMap.computeIfAbsent(
                key,
                k -> new TokenBucket(capacity, refillRatePerSecond)
        );
        return bucket.tryConsume();
    }
}
