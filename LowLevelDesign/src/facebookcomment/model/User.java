package facebookcomment.model;

import java.util.Random;

public class User {
    private String id;
    private String name;

    public User(String name) {
        this.name = name;
        this.id = "" + new Random().nextInt();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
