package com.example.rentamate;

import android.content.Context;
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
import com.parse.ParseFile;

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
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName;
        private ImageView picImage;
        private TextView userDescription;
        private RatingBar homeRatingBar;
        private TextView tvTitle;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            picImage = itemView.findViewById(R.id.picImage);
            homeRatingBar = itemView.findViewById(R.id.homeRatingBar);
            userDescription = itemView.findViewById(R.id.userDescription);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            }
        public void bind(Post post) {
            //Bind the post data to the view elements
            userDescription.setText(post.getDescription());
            userName.setText("@" + post.getUser().getUsername());
            homeRatingBar.setRating(post.getRating());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(post.getImage().getUrl()).into(picImage);
            }
        }
    }
}
