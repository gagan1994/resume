package com.example.gagan.resumebuilder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.gagan.resumebuilder.fragments.Teamplate1Fragment;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST = 123;
    FrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CheckPermission();
        } else {
            init();
        }
    }
    private void init() {
        layout = findViewById(R.id.layout);
        intiFragment();
    }

    private void intiFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager
                .beginTransaction()
                .add(R.id.layout,
                        new Teamplate1Fragment())
                .addToBackStack(null).commit()
        ;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void CheckPermission() {
        if ((ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)) {

            requestPermissions(new String[]
                            {
                                    android.Manifest.permission.ACCESS_NETWORK_STATE,
                                    android.Manifest.permission.INTERNET,
                                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                            },
                    MY_PERMISSIONS_REQUEST);
        } else {
            init();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {

        boolean canUseExternalStorage = false;

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    canUseExternalStorage = true;
                }

                if (!canUseExternalStorage) {
                    Toast.makeText(this,
                            "Cannot use this add image feature without requested permission",
                            Toast.LENGTH_SHORT).show();
                } else {
                    init();
                }
            }
        }

    }


}
