package com.polytech.si3.mypolytech.html.video;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gunther on 4/4/2016.
 */
public class YoutubeAPI {

    private static final String key = "AIzaSyAcCNvE5EhTySO_z5Unde49QYf2KGliO-w";
    //https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=UUC0wTzkzyLDrJt1NjeIT3Yg&key=AIzaSyAcCNvE5EhTySO_z5Unde49QYf2KGliO-w

    private String playlist;

    public YoutubeAPI(String playlist) {
        this.playlist = playlist;
    }

    public List<Video> getVideos() {
        List<Video> videos = new ArrayList<>();
        try {
            URL url = new URL(String.format("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=%s&key=%s", playlist, key));
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            StringBuilder builder = new StringBuilder();
            while(line != null) {
                builder.append(line);
                line = reader.readLine();
            }
            Log.i("VIDEOS", builder.toString());
            JSONObject object = new JSONObject(builder.toString());
            JSONArray items = object.getJSONArray("items");
            for(int i = 0; i < items.length(); i++) {
                JSONObject video = items.getJSONObject(i);
                JSONObject snippet = video.getJSONObject("snippet");
                String thumbnail = snippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
                videos.add(new Video(
                        snippet.getString("title"),
                        snippet.getString("description"),
                        snippet.getJSONObject("resourceId").getString("videoId"),
                        thumbnail
                        ));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videos;
    }
}
