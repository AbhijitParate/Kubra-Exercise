package com.kubra.abhijit.exercise.kubraexercise.screen.user;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kubra.abhijit.exercise.kubraexercise.R;
import com.kubra.abhijit.exercise.kubraexercise.network.model.User;

import java.util.List;

/**
 * Created by abhij on 6/26/2017.
 *
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> userList;
    private OnUserClickListener clickListener;

    public UserAdapter(List<User> users , OnUserClickListener clickListener) {
        this.userList = users;
        this.clickListener = clickListener;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card_user, parent, false);
        return new UserHolder(v);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        User user = userList.get(position);
        Log.d("UserAdapter", "onBindViewHolder: " + position);
        holder.tvUsername.setText(user.getUsername());
        String address
                =   user.getAddress().getAddressLine(1) + "\n" +
                    user.getAddress().getAddressLine(2) + "\n" +
                    user.getAddress().getCountryName() + "\n" +
                    user.getAddress().getPostalCode();

        holder.tvAddress.setText(address);

        if(clickListener!=null) {
            holder.bindClickListener();
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void updateDataset(List<User> users) {
        if (users.size() > 0){
            userList.clear();
            userList.addAll(users);
            notifyDataSetChanged();
        }
    }

    class UserHolder extends RecyclerView.ViewHolder {

        View cardView;
        TextView tvUsername, tvAddress;

        public UserHolder(View itemView) {
            super(itemView);
            cardView = itemView;
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);
        }


        public void bindClickListener() {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onCardClick(userList.get(getAdapterPosition()));
                }
            });
        }
    }

    interface OnUserClickListener{
        void onCardClick(User user);
    }
}
