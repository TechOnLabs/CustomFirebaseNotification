package techonlabs.com.customfirebasenotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: To use this app, create a project in firebase console with package name techonlabs.com
        // TODO: Then download the google-service.json file and replace it with current google-service.json file
        //TODO: Send notifications from firebase console

        // Just an example of how notification will look like
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification("FCM Notification");
            }
        });
    }
    private void sendNotification(String messageBody) {
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder;
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Intent intentConfirm =new Intent(this, NotificationReciever.class);
        intentConfirm.putExtra("action","CONFIRM");
        PendingIntent pendingIntentConfirm= PendingIntent.getActivity(this,0, intentConfirm,
                PendingIntent.FLAG_ONE_SHOT);
        Intent intentCancel =new Intent(this, NotificationReciever.class);
        intentCancel.putExtra("action","CANCEL");
        PendingIntent pendingIntentCancel= PendingIntent.getActivity(this,0, intentCancel,
                PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Action confirm =
                new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Confirm", pendingIntentConfirm).build();
        NotificationCompat.Action cancel =
                new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Cancel", pendingIntentCancel).build();

        notificationBuilder= new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)//displays icon in notification bar.
                .setContentTitle("Custom Notification")
                .setContentText(messageBody)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody)) // Used to display multiline message
                .setAutoCancel(false) //Does't cancel the notification when clicked
                .setSound(defaultSoundUri)// set notification sound
                .setContentIntent(pendingIntent)// opens when notification is clicked
                .addAction(confirm)// opens when confirm button is clicked
                .addAction(cancel);// opens when cancel button is clicked


//            notificationBuilder=new NotificationCompat.Builder(this)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle("Notification")
//                    .setContentText(messageBody)
//                    .setAutoCancel(true)
//                    .setSound(defaultSoundUri)
//                    .setContentIntent(pendingIntent);



        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
