package com.kubra.abhijit.exercise.kubraexercise.network.model;

import android.graphics.Movie;
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

public class User {
    /*
    {
    "id": 1,
    "name": "Leanne Graham",
    "username": "Bret",
    "email": "Sincere@april.biz",
    "address": {
      "street": "Kulas Light",
      "suite": "Apt. 556",
      "city": "Gwenborough",
      "zipcode": "92998-3874",
      "geo": {
        "lat": "-37.3159",
        "lng": "81.1496"
      }
    },
    "phone": "1-770-736-8031 x56442",
    "website": "hildegard.org",
    "company": {
      "name": "Romaguera-Crona",
      "catchPhrase": "Multi-layered client-server neural-net",
      "bs": "harness real-time e-markets"
    }
     */

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    private User(JSONObject userObject) throws JSONException {
        if (userObject.has("id")) this.id = userObject.getInt("id");
        if (userObject.has("name")) this.name = userObject.getString("name");
        if (userObject.has("username")) this.username = userObject.getString("username");
        if (userObject.has("email")) this.email = userObject.getString("email");

        Address address = new Address(Locale.getDefault());
        if (userObject.has("address")){
            JSONObject addrObject = userObject.getJSONObject("address");
            address.setAddressLine(1, addrObject.getString("street"));
            address.setAddressLine(2, addrObject.getString("suite"));
            address.setCountryName(addrObject.getString("city"));
            address.setPostalCode(addrObject.getString("zipcode"));
            JSONObject geoObject = addrObject.getJSONObject("geo");
            address.setLatitude(Double.parseDouble(geoObject.getString("lat")));
            address.setLongitude(Double.parseDouble(geoObject.getString("lng")));
        }
        this.address = address;

        if (userObject.has("phone")) this.phone = userObject.getString("phone");
        if (userObject.has("website")) this.website = userObject.getString("website");
        if (userObject.has("company")) {
            this.company = new Company(userObject.getJSONObject("company"));
        }
    }

    public static List<User> parseUser(JSONArray array) throws JSONException {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            User u = new User(array.getJSONObject(i));
            users.add(u);
        }

        return users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public static class Company {
        private String name;
        private String catchPhrase;
        private String bs;

        public Company(JSONObject companyObject) throws JSONException {
            if (companyObject.has("name")) this.name = companyObject.getString("name");
            if (companyObject.has("catchPhrase")) this.catchPhrase = companyObject.getString("catchPhrase");
            if (companyObject.has("bs")) this.bs = companyObject.getString("bs");
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCatchPhrase() {
            return catchPhrase;
        }

        public void setCatchPhrase(String catchPhrase) {
            this.catchPhrase = catchPhrase;
        }

        public String getBs() {
            return bs;
        }

        public void setBs(String bs) {
            this.bs = bs;
        }
    }
}
