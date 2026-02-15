package lldquestions.newsfeed;

import java.util.*;
import java.util.stream.Collectors;

public class FeedService {

    private final PostRepository postRepository;
    private final FollowRepository followRepository;

    public FeedService(PostRepository postRepository,
                       FollowRepository followRepository) {
        this.postRepository = postRepository;
        this.followRepository = followRepository;
    }

    public void createPost(String userId, String content) {
        Post post = new Post(
                UUID.randomUUID().toString(),
                userId,
                content,
                System.currentTimeMillis()
        );
        postRepository.save(post);
    }

    public void follow(String userId, String targetUserId) {
        followRepository.follow(userId, targetUserId);
    }

    public void unfollow(String userId, String targetUserId) {
        followRepository.unfollow(userId, targetUserId);
    }

    /**
     * Pagination:
     * pageSize -> number of posts
     * cursorTimestamp -> fetch posts older than this
     */
    public List<Post> getFeed(String userId, int pageSize, Long cursorTimestamp) {

        Set<String> users = new HashSet<>();
        users.add(userId);
        users.addAll(followRepository.getFollowees(userId));

        return users.stream()
                .flatMap(u -> postRepository.getPostsByUser(u).stream())
                .filter(p -> cursorTimestamp == null || p.getTimestamp() < cursorTimestamp)
                .sorted(Comparator.comparingLong(Post::getTimestamp).reversed())
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
