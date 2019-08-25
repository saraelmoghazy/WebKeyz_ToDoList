package com.webkeyz.todo.remote;

import com.webkeyz.todo.model.AddTaskResponse;
import com.webkeyz.todo.model.Task;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClientAPI {

    @GET("/getTasks")
    Single<List<Task>> getTasks();

    @POST("/addTask")
    Single<AddTaskResponse> addTask(@Body Task task);

    @PUT("/addTask/{id}")
    Single<AddTaskResponse> editTask(@Path ("id") String id, @Body Task task);
}
