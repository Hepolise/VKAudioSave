package ml.hepolise.vkaudiosave;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by hepolise on 14.01.17.
 */

public class WebInterface {
    Context mContext;

    String TAG = "VKAudioSave";
    Boolean started = false;

    WebInterface(Context c) {
        mContext = c;
    }
    MediaPlayer mediaPlayer = new MediaPlayer();
    @JavascriptInterface
    public void playSound(String url) {
        Log.d(TAG, "player from android");
        try {
            if (started) {
                mediaPlayer.release();
                mediaPlayer = null;
                mediaPlayer = new MediaPlayer();
            }
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
            //mediaPlayer.pause();
            mediaPlayer.start();
            started = true;

        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @JavascriptInterface
    public void pauseSound() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

}
