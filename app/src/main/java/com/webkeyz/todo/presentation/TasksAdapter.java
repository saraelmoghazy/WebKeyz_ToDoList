package com.webkeyz.todo.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webkeyz.todo.R;
import com.webkeyz.todo.entities.task.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ItemsViewHolder> {
    private Context context;
    private List<Task> list;

    public TasksAdapter(List<Task> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_items, parent, false);
        return new ItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {

        Task details = list.get(position);
        String taskName = details.getName();
        String taskDate = details.getDate();
        holder.taskNameTextView.setText(taskName);
        holder.taskDateTextView.setText(taskDate);


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class ItemsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.task_name_text_view)
        TextView taskNameTextView;
        @BindView(R.id.task_date_text_view)
        TextView taskDateTextView;

        ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
