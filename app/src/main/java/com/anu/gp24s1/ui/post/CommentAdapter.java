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
import com.anu.gp24s1.pojo.vo.CommentVo;
import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    Context context;
    ArrayList<CommentVo> commentModels;

    public CommentAdapter (Context context, ArrayList<CommentVo> commentModels) {
        this.context = context;
        this.commentModels = commentModels;
    }
    @NonNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.post_comment, parent, false);

        return new CommentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.MyViewHolder holder, int position) {
        holder.UserName.setText(commentModels.get(position).getUsername());
        Uri imageUrl = Uri.parse(commentModels.get(position).getUserAvatar());
        Picasso.get().load(imageUrl)
                .error(R.drawable.default_avatar_profile)
                .into(holder.userImg);
        holder.time.setText(String.valueOf(commentModels.get(position).getCommentTime()));
        holder.content.setText(commentModels.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return commentModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView userImg;
        TextView UserName;
        TextView time;
        TextView content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userImg = itemView.findViewById(R.id.userImg);
            UserName = itemView.findViewById(R.id.UserName);
            time = itemView.findViewById(R.id.time);
            content = itemView.findViewById(R.id.content);
        }
    }
}
