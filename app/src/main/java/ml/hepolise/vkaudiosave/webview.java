package ml.hepolise.vkaudiosave;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class webview extends WebViewClient {
    private static final String TAG = "VKAudioSave";

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(Uri.parse(url).getHost().endsWith("vkaudiosave.tk")) {

            Log.d(TAG, "return false");

            return false;

        }
        if(Uri.parse(url).getHost().endsWith("vk.com")) {

            Log.d(TAG, "return false");

            return false;
        }
        if(Uri.parse(url).getHost().endsWith("yandex.ru")) {

            Log.d(TAG, "return false");

            return false;
        }



        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        Log.d(TAG, "return false");
        return true;
    }
}