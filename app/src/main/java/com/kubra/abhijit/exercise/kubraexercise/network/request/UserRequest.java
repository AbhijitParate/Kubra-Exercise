package com.kubra.abhijit.exercise.kubraexercise.network.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.kubra.abhijit.exercise.kubraexercise.network.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by abhij on 6/26/2017.
 */

public class UserRequest extends Request<List<User>> {

    public static final String URL = "http://jsonplaceholder.typicode.com/users";

    private Response.Listener<List<User>> successListener;

    public UserRequest(Response.Listener<List<User>> successListener, Response.ErrorListener errorListener) {
        super(Method.GET, URL, errorListener);
        this.successListener = successListener;
    }

    @Override
    protected Response<List<User>> parseNetworkResponse(NetworkResponse response) {
        // Convert byte[] data received in the response to String
        String jsonString = new String(response.data);
        List<User> users;
        JSONArray jsonArray;

        // Try to convert JsonString to list of users
        try {
            // Convert JsonString to JSONObject
            jsonArray = new JSONArray(jsonString);
            // Get list of users from received JSON
            users = User.parseUser(jsonArray);
        }
        // in case of exception, return volley error
        catch (JSONException e) {
            e.printStackTrace();
            // return new volley error with message
            return Response.error(new VolleyError("Failed to retrieve users list"));
        }
        // return list of users
        return Response.success(users, getCacheEntry());
    }

    @Override
    protected void deliverResponse(List<User> users) {
        successListener.onResponse(users);
    }
}
