package com.kubra.abhijit.exercise.kubraexercise.network.model;

import android.location.Address;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by abhij on 6/26/2017.
 */

public class Post {
    /*
    {
    "userId": 1,
    "id": 1,
    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
  }
     */

    private int userId;
    private int id;
    private String title;
    private String body;

    private Post(JSONObject userObject) throws JSONException {
        if (userObject.has("userId")) this.userId = userObject.getInt("userId");
        if (userObject.has("id")) this.id = userObject.getInt("id");
        if (userObject.has("title")) this.title = userObject.getString("title");
        if (userObject.has("body")) this.body = userObject.getString("body");
    }

    public static List<Post> parsePosts(JSONArray array) throws JSONException {
        List<Post> posts = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            Post p = new Post(array.getJSONObject(i));
            posts.add(p);
        }

        return posts;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
