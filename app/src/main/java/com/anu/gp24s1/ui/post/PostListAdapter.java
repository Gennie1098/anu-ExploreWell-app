package com.anu.gp24s1.ui.post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anu.gp24s1.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.MyViewHolder> {
    Context context;
    ArrayList<PostListModel> postListModels;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(PostListModel item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PostListAdapter (Context context, ArrayList<PostListModel> postListModels) {
        this.context = context;
        this.postListModels = postListModels;
        this.listener = listener;
    }
    @NonNull
    @Override
    public PostListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.post_list_row, parent, false);

        return new PostListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListAdapter.MyViewHolder holder, int position) {
        PostListModel item = postListModels.get(position);
        if (listener != null) {
            holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
        }

        holder.userImg.setImageResource(postListModels.get(position).getUserImg());
        holder.UserName.setText(postListModels.get(position).getUserName());
        holder.time.setText(postListModels.get(position).getTime());
        holder.PostTitle.setText(postListModels.get(position).getPostTitle());
        holder.locationTag.setText(postListModels.get(position).getLocationTag());
        holder.activityTag.setText(postListModels.get(position).getActivityTag());
        holder.postContent.setText(postListModels.get(position).getPostContent());
        holder.numberFollowing.setText(String.valueOf(postListModels.get(position).getNumberFollowing()));
        holder.numberComments.setText(String.valueOf(postListModels.get(position).getNumberComments()));
    }

    @Override
    public int getItemCount() {
        return postListModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView userImg;
        TextView UserName;
        TextView time;
        TextView PostTitle;
        Chip locationTag;
        Chip activityTag;
        TextView postContent;
        TextView numberFollowing;
        TextView numberComments;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userImg = itemView.findViewById(R.id.userAva);
            UserName = itemView.findViewById(R.id.userName);
            time = itemView.findViewById(R.id.time);
            PostTitle = itemView.findViewById(R.id.postTitle);
            locationTag = itemView.findViewById(R.id.locationTag);
            activityTag = itemView.findViewById(R.id.activityTag);
            postContent = itemView.findViewById(R.id.content);
            numberFollowing = itemView.findViewById(R.id.numberFollowing);
            numberComments = itemView.findViewById(R.id.numberComments);
        }
    }
}
