package facebookcomment;

import facebookcomment.model.Comment;
import facebookcomment.model.Post;
import facebookcomment.model.User;

public class Driver {
    public static void main(String[] args) {
        PostCommentService postCommentService = PostCommentService.getInstance();
        User user1 = new User("kumar");
        Post post = postCommentService.createPost(user1.getId());

        Comment comment1 = new Comment(post.getId(), user1.getId(), "Comment1");
        postCommentService.addComment(post, comment1);

        Comment comment2 = new Comment(post.getId(), user1.getId(), "Comment2");
        postCommentService.addComment(post, comment2);

        Comment newComment = new Comment(post.getId(), user1.getId(), "Comment2");
        postCommentService.addNestedComment(post.getId(), comment1.getId(), newComment);
    }
}
