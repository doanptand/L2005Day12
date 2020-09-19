package com.example.day11.parser;

import com.example.day11.model.Music;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MusicParser {


    public List<Music> parse(String link) {
        List<Music> musics = new ArrayList<>();
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream));
            String line = reader.readLine();
            StringBuilder result = new StringBuilder();
            while (line != null) {
                result.append(line);
                line = reader.readLine();
            }

            JSONArray rootArray = new JSONArray(result.toString());
            for (int i = 0; i < rootArray.length(); i++) {
                JSONObject objSong = rootArray.getJSONObject(i);
                String id = objSong.getString("id");
                String linkImage = objSong.getString("linkImage");
                String songName = objSong.getString("songName");
                String artistName = objSong.getString("artistName");
                String linkSong = objSong.getString("linkSong");
                String linkMusic = objSong.getString("linkMusic");
                Music music = new Music(id, linkImage, songName, artistName,
                        linkSong, linkMusic);
                musics.add(music);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return musics;
    }

}
