package com.example.day11.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.day11.fragment.HomeFragment;
import com.example.day11.fragment.MenuFragment;
import com.example.day11.fragment.SongFragment;

public class MusicPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{"Home", "Menu", "Song"};

    public MusicPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new MenuFragment();
            case 2:
                return new SongFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
