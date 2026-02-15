package lldquestions.newsfeed;

public class Post {
    private final String postId;
    private final String userId;
    private final long timestamp;
    private final String content;

    public Post(String postId, String userId, String content, long timestamp) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }
}
