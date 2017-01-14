/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ml.hepolise.vkaudiosave;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import java.util.Date;

public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        String content = data.getString("content");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);
        Log.d(TAG, "Content: " + content);
        sendNotification(message, content);
    }
    private void sendNotification(String message, String content) {
        Intent intent = new Intent(this, ml.hepolise.vkaudiosave.MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long time = new Date().getTime();
        String tmpStr = String.valueOf(time);
        String last4Str = tmpStr.substring(tmpStr.length() - 5);
        int notificationId = Integer.valueOf(last4Str);
        if (content.equals("done")) {
            Notification.Builder notificationBuilder = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_done_white_36dp)
                    .setContentTitle("VKAudioSave")
                    .setContentText("Твой плейлист готов!")
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setSound(defaultSoundUri)
                    .setLights(0x00FFFF, 2000, 1000)
                    .setContentIntent(pendingIntent);
            Notification notification = new Notification.BigTextStyle(notificationBuilder)
                    .bigText("Твой плейлист готов\n" +
                            "Перейти к приложению").build();
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId /* ID of notification */, notification);

        } else if (content.equals("captcha")) {
            Notification.Builder notificationBuilder = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_reply_all_white_36dp)
                    .setContentTitle("VKAudioSave")
                    .setContentText("Необходим ввод каптчи")
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setSound(defaultSoundUri)
                    .setLights(0x00FFFF, 2000, 1000)
                    .setContentIntent(pendingIntent);
            Notification notification = new Notification.BigTextStyle(notificationBuilder)
                    .bigText("Нужен ввод каптчи\n" +
                            "Перейти к приложению").build();
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId /* ID of notification */, notification);
        } else {
            Notification.Builder notificationBuilder = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_done_white_36dp)
                    .setContentTitle("VKAudioSave")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setSound(defaultSoundUri)
                    .setLights(0x00FFFF, 2000, 1000)
                    .setContentIntent(pendingIntent);
            Notification notification = new Notification.BigTextStyle(notificationBuilder)
                    .bigText(message).build();
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId /* ID of notification */, notification);
        }
    }
}
