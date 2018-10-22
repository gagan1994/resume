package com.example.gagan.resumebuilder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.gagan.resumebuilder.fragments.Teamplate1Fragment;
import com.example.gagan.resumebuilder.fragments.Template1ZoomOutFragment;
import com.example.gagan.resumebuilder.pdfconverter.ViewToPdfConverter;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST = 123;
    FrameLayout layout;
    private FloatingActionButton fabAdd;
    private Teamplate1Fragment template1 = new Teamplate1Fragment();
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermissions()) {
                init();
            }
        } else {
            init();
        }
    }

    private void init() {
        layout = findViewById(R.id.layout);
        fabAdd = findViewById(R.id.fab_pdf);
        intiFragment();
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             template1.OnGeneratingPdf();
            }});
    }

    private void changeView(Fragment instance) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.layout,
                        instance)
                .addToBackStack(null).commitAllowingStateLoss();
        currentFragment = instance;
    }

    private void generatePdf(Template1ZoomOutFragment currentFragment) {
        currentFragment.generatePDF();

    }

    private void intiFragment() {
        changeView(template1);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkPermissions() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat
                    .requestPermissions(
                            this,
                            new String[]{
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST);
            return false;
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
