package com.webkeyz.todo.usecases.network;

import com.webkeyz.todo.entities.addtask.AddTaskResponse;
import com.webkeyz.todo.entities.addtask.TaskBody;
import com.webkeyz.todo.entities.task.TasksResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TasksApi {


    @GET("getTasks")
    Observable<TasksResponse> getTasks ();

    @POST("addTask")
    Observable<AddTaskResponse> addTask (@Body TaskBody body);

    @POST("editTasks/{name}")
    Observable<AddTaskResponse> editTask (@Body TaskBody body,@Path("name") String name);

    @DELETE("removeTask/{name}")
    Observable<AddTaskResponse> removeTask (@Path("name") String name);


}
