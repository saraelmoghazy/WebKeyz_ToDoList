
package com.webkeyz.todo.entities.addtask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskBody {

    public TaskBody(String name, String date, String status) {
        this.name = name;
        this.date = date;
        this.status = status;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status")
    @Expose
    private String status;

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

}
