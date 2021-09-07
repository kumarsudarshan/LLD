package facebookcomment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Post {
    private String id;
    private String postedBy;
    private List<Comment> comments;

    public Post(String postedBy){
        this.id = "" + new Random().nextInt();
        this.postedBy = postedBy;
        comments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
