package com.example.nibikoski.testforcamera004;

import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, Camera.PreviewCallback{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
        //System.loadLibrary("opencv_core");
        //System.loadLibrary("opencv_highgui");
        //System.loadLibrary("cppMain");


    }

    SurfaceView cameraView;
    SurfaceHolder surfaceHolder;
    Camera camera;
    ConstraintLayout conLayout;
    Long nativeCommunicatorPointer;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        cameraView = (SurfaceView) this.findViewById(R.id.CameraView);
        surfaceHolder = cameraView.getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

    }
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
    }

    public void surfaceCreated(SurfaceHolder holder) {
        if(camera!=null){
            camera.release();
        }
        camera = Camera.open();
        try
        {
            camera.setPreviewDisplay(holder);
            camera.setPreviewCallback(this);
        }
        catch (IOException exception)
        {
            camera.release();
        }
        camera.startPreview();
        //nativeCommunicatorPointer = getNativeClassPointer();
        //Log.d("Pointer From c++ : " , Long.toString(nativeCommunicatorPointer));
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
    }

    //public native String stringFromJNI();
    public native int intFromJNI();
    public native String stringFromJNI();
    //public native long getNativeClassPointer();
    //public native void deleteNativeClassPointer(long pointer);
    //public native void setTestInt(long pointer, int value);
    //public native int getTestInt(long pointer);



    @Override
    public void onPreviewFrame(byte[] bytes, Camera camera) {
        Log.d("Data [] size : ", Integer.toString(bytes.length));
        Camera.Parameters params = camera.getParameters();
        Log.d("Data [] format : ", Integer.toString(params.getPreviewFormat()));
        Log.d("String From OpenCV : ", stringFromJNI());
        Log.d("Int From Opencv : " , Integer.toString(intFromJNI()));
        //Log.d("Int value to 5 : " , "starting!");
        //setTestInt(nativeCommunicatorPointer, 5);
        //Log.d("Int value to 5 : " , "thats it!");
        //Log.d("Getting Int value : " , Integer.toString(getTestInt(nativeCommunicatorPointer)));





    }
}

