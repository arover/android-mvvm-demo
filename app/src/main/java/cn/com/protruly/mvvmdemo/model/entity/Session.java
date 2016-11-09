package cn.com.protruly.mvvmdemo.model.entity;

import java.util.Date;

/**
 * Created by minstrel on 11/9/16.
 */

public class Session {

    private String email;
    private Date createdAt;

    public Session(String email) {
        this.email = email;
        createdAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Session{" +
                "createdAt=" + createdAt +
                ", email='" + email + '\'' +
                '}';
    }
}
