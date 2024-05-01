package com.anu.gp24s1.ui.following;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anu.gp24s1.R;

import java.util.ArrayList;

public class followingListAdapter extends RecyclerView.Adapter<followingListAdapter.MyViewHolder> {

    Context context;
    ArrayList<FollowingModel> followingModel;

    public followingListAdapter (Context context, ArrayList<FollowingModel> followingModel) {
        this.context = context;
        this.followingModel = followingModel;
    }
    @NonNull
    @Override
    public followingListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout (Giving a look to rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.following_groups_list_row, parent, false);

        return new followingListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull followingListAdapter.MyViewHolder holder, int position) {
        //assigning values to the views created in following_groups_list_row layout file
        //base on the position of the recycler view
        holder.groupName.setText(followingModel.get(position).getGroupName());
        holder.groupIcon.setImageResource(followingModel.get(position).getGroupIcon());
    }

    @Override
    public int getItemCount() {
        //the number of items displayed on view
        return followingModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView groupIcon;
        TextView groupName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            groupIcon = itemView.findViewById(R.id.groupIcon);
            groupName = itemView.findViewById(R.id.groupName);
        }
    }
}
