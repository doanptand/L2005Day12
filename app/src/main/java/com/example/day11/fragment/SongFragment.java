package com.example.day11.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day11.R;
import com.example.day11.model.Music;
import com.example.day11.adapter.MusicAdapter;
import com.example.day11.parser.MusicParser;

import java.util.ArrayList;
import java.util.List;

public class SongFragment extends Fragment implements Runnable {
    private List<Music> musics;
    private MusicAdapter adapter;
    private RecyclerView rvSong;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 1) {
                adapter.notifyDataSetChanged();
            }
            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        musics = new ArrayList<>();
        adapter = new MusicAdapter(musics, getContext());
        rvSong = view.findViewById(R.id.rv_song);
        rvSong.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        rvSong.setAdapter(adapter);
//        Thread thread = new Thread(this);
//        thread.start();
        new LoadMusicTask().execute("https://songserver.herokuapp.com/api/searchSong");
        return view;
    }

    class LoadMusicTask extends AsyncTask<String, Void, List<Music>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getContext(), "Start fetch data", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected List<Music> doInBackground(String... args) {
            String link = args[0];
            MusicParser parser = new MusicParser();
            return parser.parse(link);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<Music> music) {
            super.onPostExecute(music);
            musics.clear();
            musics.addAll(music);
            adapter.notifyDataSetChanged();
        }
    }

    private void fetchData() {
        MusicParser parser = new MusicParser();
        musics.clear();
        musics.addAll(parser.parse("https://songserver.herokuapp.com/api/searchSong"));
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                adapter.notifyDataSetChanged();
//            }
//        });
        handler.sendEmptyMessage(1);
//        Message message = new Message();
//        message.what=2;
//        handler.sendMessage(message);
    }

    @Override
    public void run() {
        fetchData();
    }
}
