package com.kubra.abhijit.exercise.kubraexercise.network.controller;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.kubra.abhijit.exercise.kubraexercise.network.model.User;
import com.kubra.abhijit.exercise.kubraexercise.network.request.UserRequest;
import com.kubra.abhijit.exercise.kubraexercise.network.volley.VolleySingleton;

import java.util.List;

/**
 * Created by abhij on 6/26/2017.
 *
 */

public class UserController {

    private final int TAG = 100;

    private OnResponseListener responseListener;
    private Context context;

    public UserController(Context context, OnResponseListener responseListener) {
        this.context = context;
        this.responseListener = responseListener;
    }

    public void getUsers(){
        // Create new request using JsonRequest
        UserRequest request
                = new UserRequest(
                new Response.Listener<List<User>>() {
                    @Override
                    public void onResponse(List<User> users) {
                        responseListener.onSuccess(users);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.onFailure(error.getMessage());
                    }
                }
        );

        // Add tag to request
        request.setTag(TAG);

        // Get RequestQueue from VolleySingleton
        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }

    public void cancelAllRequests() {
        VolleySingleton.getInstance(context).cancelAllRequests(TAG);
    }


    public interface OnResponseListener {
        void onSuccess(List<User> users);
        void onFailure(String errorMessage);
    }
}
