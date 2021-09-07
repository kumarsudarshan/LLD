package facebookcomment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Comment {
    private String id;
    private String parentId;
    private String userId;
    private String postId;
    private String description;
    private List<Comment> comments;

    public Comment(String postId, String userId, String description) {
        this.postId = postId;
        this.userId = userId;
        this.description = description;
        this.id = "" + new Random().nextInt();
        comments = new ArrayList<>();
    }

    public void setNestedDescription(String commentId, String description){
        for(Comment comment : comments){
            if(commentId == comment.getId()) {
                comment.setDescription(description);
            }
        }
    }

    public void setNestedDelete(String commentId){
        for(Comment comment : comments){
            if(commentId == comment.getId()) {
                comments.remove(comment);
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
