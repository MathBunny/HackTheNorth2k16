package com.pebblepolo.pebblepolo;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        long[] pattern = new long[] {100, 100};
        v.vibrate(pattern, 0);
    }
}