package com.example.karahana.firebase;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtils {
    public interface runOnUIListener {
        void run();
    }
    public static void runOnUI(runOnUIListener listener) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    listener.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void sleep(long time){
        try{
            Thread.sleep(time);
        }catch (Exception e){

        }
    }
}
