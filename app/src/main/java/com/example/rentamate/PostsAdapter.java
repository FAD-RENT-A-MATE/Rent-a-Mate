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
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post =  posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
    //??
    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userName;
        private ImageView picImage;
        private TextView userDescription;
        private RatingBar homeRatingBar;
        private Button selectBTN;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            picImage = itemView.findViewById(R.id.picImage);
            homeRatingBar = itemView.findViewById(R.id.homeRatingBar);
            userDescription = itemView.findViewById(R.id.userDescription);
            selectBTN = itemView.findViewById(R.id.selectBTN);

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

            // An on click listener connected to the images
            picImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, SubmitRating.class);
                    i.putExtra("id", post.getObjectId());
                    context.startActivity(i);
                }
            });

            selectBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, SelectUser.class);
                    i.putExtra("id", post.getObjectId());
                    context.startActivity(i);
                }
            });
        }
    }
}
