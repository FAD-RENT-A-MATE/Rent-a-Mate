package com.example.rentamate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public ProfileAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.profile_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        Post post =  posts.get(position);
        try {
            holder.bind(post);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName;
        private ImageView picImage;
        private TextView userDescription;
        private RatingBar homeRatingBar;
        private TextView tvTitle;
        private TextView tvSelectedByUsers;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            picImage = itemView.findViewById(R.id.picImage);
            homeRatingBar = itemView.findViewById(R.id.homeRatingBar);
            userDescription = itemView.findViewById(R.id.userDescription);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSelectedByUsers = itemView.findViewById(R.id.tvSelectedByUsers);
            }
        public void bind(Post post) throws ParseException {
            //Bind the post data to the view elements
            userDescription.setText(post.getDescription());
            userName.setText("@" + post.getUser().getUsername());
            homeRatingBar.setRating(post.getRating());
            ParseUser selectedUser = post.getSelectedBy();
            if (selectedUser != null){
                tvSelectedByUsers.setText("@" + selectedUser.fetchIfNeeded().getUsername());
            }
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(post.getImage().getUrl()).into(picImage);
            }

            // An on click listener connected to the images
            userDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, EditDescription.class);
                    i.putExtra("id", post.getObjectId());
                    context.startActivity(i);
                }
            });

            picImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, EditProfileImage.class);
                    i.putExtra("id", post.getObjectId());
                    context.startActivity(i);
                }
            });


        }
    }
}
