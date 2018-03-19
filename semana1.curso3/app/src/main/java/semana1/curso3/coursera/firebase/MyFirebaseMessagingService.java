package semana1.curso3.coursera.firebase;

import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;

import semana1.curso3.coursera.MainActivity;
import semana1.curso3.coursera.R;
import semana1.curso3.coursera.UserActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getName();

    public static final int NOTIFICATION_ID = 001;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), remoteMessage.getData().get("id_usuario_notificacion"));
    }

    private void sendNotification(String title, String description, String id_usuario_notificacion) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.app_name);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        ArrayList<NotificationCompat.Action> actions = new ArrayList<>();

        Intent intentViewProfile = new Intent(this, MainActivity.class);
        PendingIntent pendingIntentViewProfile = PendingIntent.getActivity(this, 0, intentViewProfile, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Action action_view_profile = new NotificationCompat.Action.Builder(R.drawable.ic_full_profile,
            getString(R.string.view_profile), pendingIntentViewProfile).build();

        Intent intentFollowUnfollow = new Intent();
        intentFollowUnfollow.setAction("FOLLOW_UNFOLLOW").putExtra("id_usuario_notificacion", id_usuario_notificacion);
        PendingIntent pendingIntentFollowUnfollow = PendingIntent.getBroadcast(this, 0, intentFollowUnfollow, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action_follow_unfollow = new NotificationCompat.Action.Builder(R.drawable.ic_full_follow_unfollow,
                getString(R.string.follow_unfollow), pendingIntentFollowUnfollow).build();

        Intent intentViewUser = new Intent(this, UserActivity.class);
        intentViewUser.putExtra("id_usuario_notificacion", id_usuario_notificacion);
        PendingIntent pendingIntentViewUser = PendingIntent.getActivity(this, 0, intentViewUser, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Action action_view_user = new NotificationCompat.Action.Builder(R.drawable.ic_full_user,
                getString(R.string.view_user), pendingIntentViewUser).build();

        actions.add(action_view_profile);
        actions.add(action_follow_unfollow);
        actions.add(action_view_user);

        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();

        NotificationCompat.Builder notificationBuilder = new
            NotificationCompat.Builder(MyFirebaseMessagingService.this, channelId)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addActions(actions));
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

}