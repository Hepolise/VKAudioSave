package ml.hepolise.vkaudiosave;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;


public class webview extends WebViewClient {
    private static final String TAG = "VKAudioSave";

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {



//        if(Uri.parse(url).getHost().endsWith("vkaudiosave.tk")) {
//
//            Log.d(TAG, "return false");
//
//            return false;
//
//        }
//        if(Uri.parse(url).getHost().endsWith("vk.com")) {
//
//            Log.d(TAG, "return false");
//
//            return false;
//        }
//        if(Uri.parse(url).getHost().endsWith("srvr.tk")) {
//
//            Log.d(TAG, "return false");
//
//            return false;
//        }
        if(Uri.parse(url).getHost().endsWith("yandex.ru")) {

            Log.d(TAG, "return false");

            return false;
        }
        if(Uri.parse(url).getHost().endsWith("vk-as.tk")) {

            Log.d(TAG, "return false");

            return false;
        }




        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        Log.d(TAG, "return true");
        return true;
    }
}