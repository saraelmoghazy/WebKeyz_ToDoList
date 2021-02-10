
package com.webkeyz.todo.entities.task;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TasksResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tasks")
    @Expose
    private List<Task> tasks = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
