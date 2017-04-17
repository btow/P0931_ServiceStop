package com.example.samsung.p0931_servicestop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtn(View view) {

        startService(new Intent(this, MyService.class).putExtra(getString(R.string.time), 7));
        startService(new Intent(this, MyService.class).putExtra(getString(R.string.time), 2));
        startService(new Intent(this, MyService.class).putExtra(getString(R.string.time), 4));
    }
}
