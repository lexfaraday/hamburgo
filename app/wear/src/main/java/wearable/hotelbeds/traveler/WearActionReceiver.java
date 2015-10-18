package wearable.hotelbeds.traveler;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.wearable.activity.ConfirmationActivity;

/**
 * Created by Zavierazo on 10/10/2015.
 */
public class WearActionReceiver extends BroadcastReceiver {

    public static final String NOTIFICATION_ID_STRING = "NotificationId";
    public static final String WEAR_ACTION = "WearAction";
    public static final int OPEN_ON_PHONE = 1;
    public static final int OPEN_ON_WEAR = 2;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            int notificationId = intent.getIntExtra(NOTIFICATION_ID_STRING, 0);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            int action = intent.getIntExtra(WEAR_ACTION, 0);
            switch (action) {
                case OPEN_ON_PHONE:
                    manager.cancel(notificationId);
                    Intent confirmIntent = new Intent(context, ConfirmationActivity.class);
                    confirmIntent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.OPEN_ON_PHONE_ANIMATION);
                    confirmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(confirmIntent);
                    break;
                case OPEN_ON_WEAR:
                    Intent qrIntent = new Intent(context, QrActivity.class);
                    qrIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(qrIntent);
                    break;
                default:
                    break;
            }


        }
    }
}
