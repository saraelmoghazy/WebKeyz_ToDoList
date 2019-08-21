package com.webkeyz.todo.remote;

import com.webkeyz.todo.model.Task;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ClientAPI {

    @GET("/getTasks")
    Single<List<Task>> getTasks();
}
