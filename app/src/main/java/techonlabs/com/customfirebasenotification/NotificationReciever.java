package techonlabs.com.customfirebasenotification;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Destroyer on 6/20/2017.
 */

// This activity is used to receive data from intent used in MyFirebaseMessagingService.java

public class NotificationReciever extends AppCompatActivity {
    private  String action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_reciever);
// Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Dismiss Notification
        notificationmanager.cancel(0);

        // Retrive the data from intent
        Intent i = getIntent();
//          get data from notification
        action = i.getStringExtra("action");
        if (action.equals("CONFIRM")){
            Toast.makeText(this, "CONFIRM", Toast.LENGTH_SHORT).show();
        }
        else if (action.equals("CANCEL")){
            Toast.makeText(this, "CANCEL", Toast.LENGTH_SHORT).show();

        }
    }
}
