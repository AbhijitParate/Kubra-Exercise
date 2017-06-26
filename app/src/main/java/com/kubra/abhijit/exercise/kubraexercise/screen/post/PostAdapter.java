package com.kubra.abhijit.exercise.kubraexercise.screen.post;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kubra.abhijit.exercise.kubraexercise.R;
import com.kubra.abhijit.exercise.kubraexercise.network.model.Post;
import com.kubra.abhijit.exercise.kubraexercise.network.model.User;

import java.util.List;

/**
 * Created by abhij on 6/26/2017.
 *
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.UserHolder> {

    private List<Post> userList;

    public PostAdapter(List<Post> posts) {
        this.userList = posts;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card_post, parent, false);
        return new UserHolder(v);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        Post post = userList.get(position);
        Log.d("UserAdapter", "onBindViewHolder: " + position);
        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void updateDataset(List<Post> users) {
        if (users.size() > 0){
            userList.clear();
            userList.addAll(users);
            notifyDataSetChanged();
        }
    }

    class UserHolder extends RecyclerView.ViewHolder {

        View cardView;
        TextView tvTitle, tvBody;

        public UserHolder(View itemView) {
            super(itemView);
            cardView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
        }
    }

    interface OnUserClickListener{
        void onCardClick(Post post);
    }
}
