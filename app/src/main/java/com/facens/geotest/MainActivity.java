package com.facens.geotest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewPhoto;
    private Button btnGeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        btnGeo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                GPStracker g = new GPStracker(getApplication());
                Location l = g.getLocation();

                if (l != null){
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE: " +lat+"\nLONGITUDE: "+lon,Toast.LENGTH_LONG).show();
                }
            }
        });
        //
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 0);
        }
        //
        imageViewPhoto = (ImageView) findViewById(R.id.image_photo);
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener(){
            //
            @Override
            public void onClick(View view) {takePhoto();}
        });
    }
    //
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
        //
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
            //
            if(requestCode == 1 && resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                Bitmap image = (Bitmap) extras.get("data");
                imageViewPhoto.setImageBitmap(image);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }