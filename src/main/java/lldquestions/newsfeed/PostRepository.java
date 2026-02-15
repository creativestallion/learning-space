package lldquestions.newsfeed;

import java.util.*;

public class PostRepository {

    private final Map<String, List<Post>> postsByUser = new HashMap<>();

    public void save(Post post) {
        postsByUser
                .computeIfAbsent(post.getUserId(), k -> new ArrayList<>())
                .add(post);
    }

    public List<Post> getPostsByUser(String userId) {
        return postsByUser.getOrDefault(userId, List.of());
    }
}
