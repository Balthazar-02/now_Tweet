package com.codepath.apps.Balthazar.models;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

import com.codepath.apps.Balthazar.Convert;
import com.codepath.apps.Balthazar.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {


    public String body;
    public String createdAt;
    public long id;
    public User user;

    @ColumnInfo
    public  String media_url;


    @TypeConverters(Convert.class)
    @ColumnInfo
    public List<String> medias = new ArrayList<>();



    // empty constructor needed by the Parceler library
    public Tweet() {}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        // Takes media for this tweet
        try {
            JSONArray entities_media = jsonObject.getJSONObject("extended_entities").getJSONArray("media");
            for (int i = 0; i < entities_media.length(); i++) {
                String m = "";
                m += entities_media.getJSONObject(i).getString("media_url_https");
                m += " - ";
                m += entities_media.getJSONObject(0).getString("type");
                tweet.medias.add(m);
            }

        } catch (Exception e) {e.printStackTrace(); }

        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
    public  String getFormattedTimestamp() {
        return TimeFormatter.getTimeDifference(createdAt);
    }
    public String getTimeStamp() {
        return TimeFormatter.getTimeStamp(createdAt);
    }
}
