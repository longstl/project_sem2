package com.example.hoang.project_demo_3.entity;

public class Category {
    private int id;
    private String title;
    private String image_url;
    private String created_at;
    private String updated_at;

    public Category() {
    }

    public Category(int id, String title, String image_url, String created_at, String updated_at) {
        this.id = id;
        this.title = title;
        this.image_url = image_url;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
