package com.example.day11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.day11.adapter.MusicPagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerMain;
    private NavigationView navMain;
    private ViewPager vp_music;
    private MusicPagerAdapter adapter;
    private TabLayout tabMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        drawerMain = findViewById(R.id.drawerMain);
        navMain = findViewById(R.id.navMain);
        navMain.setNavigationItemSelectedListener(this);
        vp_music = findViewById(R.id.vp_music);
        adapter = new MusicPagerAdapter(getSupportFragmentManager());
        vp_music.setAdapter(adapter);
        tabMain = findViewById(R.id.tab_main);
        tabMain.setupWithViewPager(vp_music);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerMain.isDrawerOpen(GravityCompat.START)) {
                drawerMain.closeDrawer(GravityCompat.START);
            } else {
                drawerMain.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_menu:
                Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_song:
                Toast.makeText(this, "Song", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerMain.closeDrawer(GravityCompat.START);
        return true;
    }
}