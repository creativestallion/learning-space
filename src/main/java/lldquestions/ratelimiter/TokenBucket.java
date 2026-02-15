package lldquestions.ratelimiter;

import java.util.concurrent.locks.ReentrantLock;

public class TokenBucket {

    private final long capacity;
    private final long refillRatePerSecond;

    private double tokens;
    private long lastRefillTimestamp;

    public TokenBucket(long capacity, long refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokens = tokens;
        this.lastRefillTimestamp = lastRefillTimestamp;
    }

    private final ReentrantLock lock = new ReentrantLock();

    public boolean tryConsume() {
        lock.lock();
        try{
            if (tokens >= 1) {
                tokens -= 1;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void refill() {
        long now = System.nanoTime();
        double secondsElapsed = (now - lastRefillTimestamp) / 1_000_000_000.0;

        double tokensToAdd = secondsElapsed * refillRatePerSecond;
        if (tokensToAdd > 0) {
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }
 }
