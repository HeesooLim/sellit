package com.project.sellit.model;

public class Authorities {
    /*
        username varchar(50) not null,
        authority varchar(50) not null,
        foreign key (username) references users (username)
     */

    private String username;
    private String authority;
    private String user;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
