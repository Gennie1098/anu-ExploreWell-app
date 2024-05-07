package com.anu.gp24s1.ui.home;

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
import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class RePostsByLocationAdapter extends RecyclerView.Adapter<RePostsByLocationAdapter.MyViewHolder>{

    Context context;
    ArrayList<RePostsByLocationModel> rePostsByLocationModel;

    public RePostsByLocationAdapter (Context context, ArrayList<RePostsByLocationModel> rePostsByLocationModel) {
        this.context = context;
        this.rePostsByLocationModel = rePostsByLocationModel;
    }
    @NonNull
    @Override
    public RePostsByLocationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout (Giving a look to rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.post_list_column, parent, false);

        return new RePostsByLocationAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //assigning values to the views created in following_groups_list_row layout file
        //base on the position of the recycler view
        Uri imageUrl = Uri.parse(rePostsByLocationModel.get(position).getUserAva());
        Picasso.get().load(imageUrl)
                .error(R.drawable.default_avatar_profile)
                .into(holder.userAva);
        holder.userName.setText(rePostsByLocationModel.get(position).getUserName());
        holder.locationTag.setText(rePostsByLocationModel.get(position).getLocation());
        holder.activityTag.setText(rePostsByLocationModel.get(position).getActivity());
        holder.postTitle.setText(rePostsByLocationModel.get(position).getPostTitle());
        holder.numberOfFollowing.setText(String.valueOf(rePostsByLocationModel.get(position).getNumberOfFollowing()));
        holder.numberOfComments.setText(String.valueOf(rePostsByLocationModel.get(position).getNumberOfComments()));
    }

    @Override
    public int getItemCount() {
        return rePostsByLocationModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView userAva;
        TextView userName;

        Chip locationTag;
        Chip activityTag;
        TextView postTitle;
        TextView numberOfFollowing;
        TextView numberOfComments;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            userAva = itemView.findViewById(R.id.userAva);
            userName = itemView.findViewById(R.id.userName);
            locationTag = itemView.findViewById(R.id.locationTag);
            activityTag = itemView.findViewById(R.id.activityTag);
            postTitle = itemView.findViewById(R.id.postTitle);
            numberOfFollowing = itemView.findViewById(R.id.numberFollowing);
            numberOfComments = itemView.findViewById(R.id.numberComments);

        }
    }
}
