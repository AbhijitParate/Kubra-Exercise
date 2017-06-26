package com.kubra.abhijit.exercise.kubraexercise.screen.post;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.kubra.abhijit.exercise.kubraexercise.R;
import com.kubra.abhijit.exercise.kubraexercise.network.controller.UserController;
import com.kubra.abhijit.exercise.kubraexercise.network.controller.UserPostController;
import com.kubra.abhijit.exercise.kubraexercise.network.model.Post;
import com.kubra.abhijit.exercise.kubraexercise.screen.user.UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserPostActivity extends AppCompatActivity implements UserPostController.OnResponseListener {

    public static final String TAG = UserPostActivity.class.getSimpleName();

    private static final String USER_ID = "USER_ID";
    private static final int DEFAULT_USER_ID = -99;

    private int userId;

    RecyclerView recyclerView;

    UserPostController controller;
    PostAdapter adapter;

    public static Intent getLauncherIntent(Activity activity, int userId) {
        Intent intent = new Intent(activity, UserPostActivity.class);
        intent.putExtra(USER_ID, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post);

        userId = getIntent().getIntExtra(USER_ID, DEFAULT_USER_ID);

        recyclerView = (RecyclerView) findViewById(R.id.rvPosts);
        controller = new UserPostController(this, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PostAdapter(new ArrayList<Post>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (userId != DEFAULT_USER_ID) {
            controller.getUserPosts(userId);
        } else {
            Toast.makeText(this, "Invalid userID", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(List<Post> posts) {
        Log.d(TAG, "onSuccess: " + posts.size() + " posts retrieved");
        adapter = new PostAdapter(posts);
        recyclerView.swapAdapter(adapter, true);
    }

    @Override
    public void onFailure(String errorMessage) {
        Toast.makeText(this, "Failed to retrieve posts", Toast.LENGTH_SHORT).show();
    }
}
