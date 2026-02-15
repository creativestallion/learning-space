package lldquestions.ratelimiter;

public interface RateLimiter {

    boolean allowRequest(String key);
}
