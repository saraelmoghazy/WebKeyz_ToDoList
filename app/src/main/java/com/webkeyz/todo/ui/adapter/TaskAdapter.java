package com.webkeyz.todo.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.webkeyz.todo.R;
import com.webkeyz.todo.model.Task;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.webkeyz.todo.ui.MainFragment.TASK_FINISHED;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> list;
    private ColorGenerator mColorGenerator = ColorGenerator.DEFAULT;
    private TextDrawable mDrawableImage;
    private Context context;

    public TaskAdapter(Context context, List<Task> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Task task = list.get(position);
        holder.tvTitle.setText(task.getName());
        holder.tvContent.setText(task.getContent());
        String letter = task.getName().substring(0, 1);
        letter = letter.toUpperCase();
        int color = mColorGenerator.getRandomColor();
        mDrawableImage = TextDrawable.builder()
                .buildRound(letter, color);
        holder.image.setImageDrawable(mDrawableImage);

        String longV = list.get(position).getDate();
        long millisecond = Long.parseLong(longV);
        String dateString = DateFormat.format("MMM, dd yyyy", new Date(millisecond)).toString();
        holder.tvDate.setText(dateString);
        if(list.get(position).getStatus().equals(TASK_FINISHED)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.cardView.setBackgroundColor(context.getColor(R.color.green));
            }
            else{
                ContextCompat.getColor(context, R.color.green);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView tvTitle;
        @BindView(R.id.content)
        TextView tvContent;
        @BindView(R.id.date)
        TextView tvDate;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.cardView)
        CardView cardView;

        public MyViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
