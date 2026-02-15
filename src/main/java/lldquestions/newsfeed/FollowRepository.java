package lldquestions.newsfeed;

import java.util.*;

public class FollowRepository {

    private final Map<String, Set<String>> followeesByUser = new HashMap<>();

    public void follow(String userId, String targetUserId) {
        followeesByUser
                .computeIfAbsent(userId, k -> new HashSet<>())
                .add(targetUserId);
    }

    public void unfollow(String userId, String targetUserId) {
        followeesByUser
                .getOrDefault(userId, Set.of())
                .remove(targetUserId);
    }

    public Set<String> getFollowees(String userId) {
        return followeesByUser.getOrDefault(userId, Set.of());
    }
}
