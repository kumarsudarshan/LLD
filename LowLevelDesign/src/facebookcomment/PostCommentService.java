package facebookcomment;

import facebookcomment.model.Comment;
import facebookcomment.model.Post;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PostCommentService {

    private static PostCommentService INSTANCE = null;
    private Map<String, Post> postMap = new HashMap<>();

    private PostCommentService() {
    }

    public static PostCommentService getInstance() {
        if (INSTANCE == null) {
            synchronized (PostCommentService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PostCommentService();
                }
            }
        }
        return INSTANCE;
    }

    public Post createPost(String  userId) {
        Post post = new Post(userId);
        System.out.println("Post created");
        return post;
    }

    void addComment(Post post, Comment comment) {
        post.getComments().add(comment);
        postMap.put(post.getId(), post);
    }

    void addNestedComment(String postId, String commentId, Comment newComment) {
        for (Comment comment : postMap.get(postId).getComments()) {
            if (comment.getId() == commentId) {
                comment.getComments().add(newComment);
                return;
            }
        }
    }

    void editComment(int postId, String rootCommentId, String commentId, String description) {
        Post post = postMap.get(postId);
        for (Comment comment : post.getComments()) {
            if(comment.getId() == rootCommentId){
                Queue<Comment> queue = new LinkedList<>();
                queue.add(comment);
                while (!queue.isEmpty()) {
                    Comment tempComment = queue.poll();
                    if (tempComment.getId() == commentId) {
                        tempComment.setDescription(description);
                        return;
                    }
                    for (Comment comment1 : tempComment.getComments()) {
                        queue.add(comment1);
                    }
                }
            }
        }
    }

    void deleteComment(int postId, String rootCommentId, String commentId) {
        Post post = postMap.get(postId);
        for (Comment comment : post.getComments()) {
            if(comment.getId() == rootCommentId){
                if(comment.getId() == commentId){
                    post.getComments().remove(comment);
                    return;
                }
                Queue<Comment> queue = new LinkedList<>();
                queue.add(comment);
                while (!queue.isEmpty()) {
                    Comment tempComment = queue.poll();
                    for (Comment comment1 : tempComment.getComments()) {
                        if(comment1.getId() == commentId){
                            tempComment.getComments().remove(comment1);
                            return;
                        }
                        queue.add(comment1);
                    }
                }
            }
        }
    }

}
