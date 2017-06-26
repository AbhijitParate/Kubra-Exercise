package com.kubra.abhijit.exercise.kubraexercise.network.request;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.kubra.abhijit.exercise.kubraexercise.network.model.Post;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by abhij on 6/26/2017.
 *
 */

public class UserPostRequest extends Request<List<Post>> {

    public static final String URL = "http://jsonplaceholder.typicode.com/posts?userId=";

    private Response.Listener<List<Post>> successListener;

    public UserPostRequest(int userId, Response.Listener<List<Post>> successListener, Response.ErrorListener errorListener) {
        super(Method.GET, URL + userId, errorListener);
        this.successListener = successListener;
    }

    @Override
    protected Response<List<Post>> parseNetworkResponse(NetworkResponse response) {
        // Convert byte[] data received in the response to String
        String jsonString = new String(response.data);
        List<Post> posts;
        JSONArray jsonArray;

        // Try to convert JsonString to list of users
        try {
            Log.d("JSON", jsonString);
            // Convert JsonString to JSONObject
            jsonArray = new JSONArray(jsonString);
            // Get list of users from received JSON
            posts = Post.parsePosts(jsonArray);
        }
        // in case of exception, return volley error
        catch (JSONException e) {
            e.printStackTrace();
            // return new volley error with message
            return Response.error(new VolleyError("Failed to retrieve users list"));
        }
        // return list of users
        return Response.success(posts, getCacheEntry());
    }

    @Override
    protected void deliverResponse(List<Post> posts) {
        successListener.onResponse(posts);
    }
}
