package com.interpixel.geter_geterbersensasi;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GeterListener {

    private Vibrator vibrator;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_custom:
                    CustomFragment customFragment = new CustomFragment();
                    customFragment.setGeterListener(MainActivity.this);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, customFragment)
                            .commit();
                    break;
                case R.id.navigation_morse:
                    MorseFragment morseFragment = new MorseFragment();
                    morseFragment.setGeterListener(MainActivity.this);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, morseFragment)
                            .commit();
                    break;
                case R.id.navigation_song:
                    SongFragment songFragment = new SongFragment();
                    songFragment.setGeterListener(MainActivity.this);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, songFragment)
                            .commit();
                    break;
            }

            //reset vibrate tiap kali ganti fragment
            if(vibrator.hasVibrator()){
                vibrator.cancel();
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.title_bar);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_custom);

        if(!vibrator.hasVibrator()){
            Toast.makeText(this, "HP ini tidak bisa bergetar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void geter(long ms) {
        vibrator.vibrate(ms);
    }

    @Override
    public void stopGetar() {
        vibrator.cancel();
    }

    @Override
    public void geterPattern(long[] pattern, boolean repeat) {
        vibrator.vibrate(pattern, repeat ? 1 : -1);
    }
}
