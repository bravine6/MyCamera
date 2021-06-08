package com.example.mycamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;

    ImageView imageView1;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = (ImageView) this.findViewById(R.id.imageView1);
        button1 = findViewById(R.id.button1);
        button2= findViewById(R.id.button2);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }

        //create explicit intent
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open camera
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });


   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //get capture image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");

            //set captured image to imageview (load image on the image view)
            imageView1.setImageBitmap(captureImage);

            //Homework
            //send the image to the gallery

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedImageURL = MediaStore.Images.Media.insertImage(
                        getContentResolver(),
                        captureImage,
                        "Test",
                        "Test"
                );
                Toast.makeText(MainActivity.this,"Image saved successfully",Toast.LENGTH_SHORT).show();
            }
        });

        }
    }


}