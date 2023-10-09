package com.chaurasiyashivani.torchapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageButton toggleButton;
    boolean hasCameraFlash=false;
    boolean flashOn=false;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton=findViewById(R.id.toggleButton);

        hasCameraFlash=getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        toggleButton.setOnClickListener(v -> {
            if(hasCameraFlash){
                if(flashOn){
                    flashOn=false;
                    toggleButton.setImageResource(R.drawable.off);
                    flashLightOff();
                }
                else{
                    flashOn=true;
                    toggleButton.setImageResource(R.drawable.power);
                    flashLightOn();
                }

            }
            else{
                Toast.makeText(MainActivity.this, "Your device is not have accessiblity", Toast.LENGTH_SHORT).show();
            }

        });
    }
    private  void flashLightOn(){
        CameraManager cameraManager=(CameraManager)getSystemService(Context.CAMERA_SERVICE);
        String cameraId;
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
        try {
            cameraManager.setTorchMode(cameraId,true);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
        Toast.makeText(this, "Flash light is on", Toast.LENGTH_SHORT).show();
    }
    private  void flashLightOff(){
        CameraManager cameraManager=(CameraManager)getSystemService(Context.CAMERA_SERVICE);
        String cameraId;
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
        try {
            cameraManager.setTorchMode(cameraId ,false);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
        Toast.makeText(this, "Flash light is off", Toast.LENGTH_SHORT).show();
    }

}