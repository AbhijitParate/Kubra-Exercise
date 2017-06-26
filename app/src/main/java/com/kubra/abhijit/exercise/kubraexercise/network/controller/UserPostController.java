package com.kubra.abhijit.exercise.kubraexercise.network.controller;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.kubra.abhijit.exercise.kubraexercise.network.model.Post;
import com.kubra.abhijit.exercise.kubraexercise.network.request.UserPostRequest;
import com.kubra.abhijit.exercise.kubraexercise.network.volley.VolleySingleton;

import java.util.List;

/**
 * Created by abhij on 6/26/2017.
 *
 */

public class UserPostController {

    private final int TAG = 104;

    private OnResponseListener responseListener;
    private Context context;

    public UserPostController(Context context, OnResponseListener responseListener) {
        this.context = context;
        this.responseListener = responseListener;
    }

    public void getUserPosts(int userId){
        // Create new request using JsonRequest
        UserPostRequest request
                = new UserPostRequest(
                        userId,
                        new Response.Listener<List<Post>>() {
                            @Override
                            public void onResponse(List<Post> posts) {
                                responseListener.onSuccess(posts);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                responseListener.onFailure(error.getMessage());
                            }
                        });

        // Add tag to request
        request.setTag(TAG);

        // Get RequestQueue from VolleySingleton
        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }

    public void cancelAllRequests() {
        VolleySingleton.getInstance(context).cancelAllRequests(TAG);
    }


    public interface OnResponseListener {
        void onSuccess(List<Post> posts);
        void onFailure(String errorMessage);
    }
}
