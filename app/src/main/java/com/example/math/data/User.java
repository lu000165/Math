package com.example.math.data;

public class User {
    private int id;
    private String name;
    private boolean isParent;
    private String email;

    public User(int id,String name, String email, boolean isParent){
        this.id = id;
        this.name = name;
        this.email = email;
        this.isParent = isParent;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isParent() {
        return isParent;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isParent=" + isParent +
                ", email='" + email + '\'' +
                '}';
    }
}
