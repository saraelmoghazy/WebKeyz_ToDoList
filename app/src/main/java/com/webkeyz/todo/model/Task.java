package com.webkeyz.todo.model;

import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("date")
    private String date;
    @SerializedName("status")
    private String status;
    @SerializedName("content")
    private String content;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
