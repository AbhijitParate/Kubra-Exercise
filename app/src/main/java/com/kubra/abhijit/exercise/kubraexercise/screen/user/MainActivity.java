package com.kubra.abhijit.exercise.kubraexercise.screen.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kubra.abhijit.exercise.kubraexercise.R;
import com.kubra.abhijit.exercise.kubraexercise.network.controller.UserController;
import com.kubra.abhijit.exercise.kubraexercise.network.model.User;
import com.kubra.abhijit.exercise.kubraexercise.screen.post.UserPostActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnUserClickListener, UserController.OnResponseListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    UserController controller;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rvUsers);
        controller = new UserController(this, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    protected void onStart() {
        super.onStart();
        controller.getUsers();
    }

    @Override
    public void onCardClick(User user) {
        Log.d(TAG, "onCardClick: " +  user.getId());
        Intent intent = UserPostActivity.getLauncherIntent(this, user.getId());
        startActivity(intent);
    }

    @Override
    public void onSuccess(List<User> users) {
        Log.d(TAG, "onSuccess: users list retrieved : " + users.size() + " items");
        adapter = new UserAdapter(users, this);
        recyclerView.swapAdapter(adapter, true);
    }

    @Override
    public void onFailure(String errorMessage) {
        Log.d(TAG, "onFailure: failed to retrieve user's list");
    }
}
