package com.anu.gp24s1.ui.post;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anu.gp24s1.R;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.MyViewHolder> {
    Context context;
    ArrayList<PostVo> postListModels;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(PostVo item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PostListAdapter (Context context, ArrayList<PostVo> postListModels) {
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
        PostVo item = postListModels.get(position);
        if (listener != null) {
            holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
        }

        holder.UserName.setText(item.getAuthorName());
        Uri imageUrl = Uri.parse(item.getAuthorAvatar());
        Picasso.get().load(imageUrl).into(holder.userImg);
        holder.time.setText(item.getPublishTime().toString());
        holder.PostTitle.setText(item.getTitle());
        holder.locationTag.setText(item.getLocation());
        holder.activityTag.setText(item.getTag());
        holder.postContent.setText(item.getContent());
        holder.numberFollowing.setText(String.valueOf(item.getFollowerNumber()));
        holder.numberComments.setText(String.valueOf(item.getCommentsNumber()));
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
            userImg = itemView.findViewById(R.id.userImg);
            UserName = itemView.findViewById(R.id.UserName);
            time = itemView.findViewById(R.id.time);
            PostTitle = itemView.findViewById(R.id.PostTitle);
            locationTag = itemView.findViewById(R.id.locationTag);
            activityTag = itemView.findViewById(R.id.activityTag);
            postContent = itemView.findViewById(R.id.content);
            numberFollowing = itemView.findViewById(R.id.numberFollowing);
            numberComments = itemView.findViewById(R.id.numberComments);
        }
    }
}
