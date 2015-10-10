package wearable.hotelbeds.traveler;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.view.DelayedConfirmationView;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;

import wearable.hotelbeds.shared.price.ConfirmDataBean;
import wearable.hotelbeds.shared.price.PriceInfoBean;
import wearable.hotelbeds.shared.price.PriceUtils;

public class ConfirmActivity extends Activity implements DelayedConfirmationView.DelayedConfirmationListener {
    private static final int CONFIRMATION_TIME_IN_MS = 2000;
    private TextView mTextView;
    private DelayedConfirmationView mDelayedView;
    private boolean mRunning;
    private ConfirmActivity context;
    private Bundle params;
    private PriceInfoBean price;
    ConfirmDataBean confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        context = this;
        params = getIntent().getExtras();
        price = (PriceInfoBean) params.getSerializable("key");
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.confirm_panel);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.textView2);
                String amount = null;
                if (price.getTotalAmount().stripTrailingZeros().scale() <= 0) {
                    amount = String.valueOf(price.getTotalAmount());
                } else {
                    amount = String.valueOf(price.getTotalAmount().setScale(2, BigDecimal.ROUND_UP));
                }
                mTextView.setText(amount + "€");
                mDelayedView = (DelayedConfirmationView) stub.findViewById(R.id.delayed_confirm);
                mDelayedView.setListener(context);
            }
        });
    }


    public void btnCancel(View view) {
        if (mRunning) {
            mDelayedView.reset();
            mRunning = false;
        } else {
            Intent resultIntent = new Intent();
            Bundle b = new Bundle();
            b.putBoolean("confirmed", false);
            resultIntent.putExtras(b);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }


    @Override
    public void onTimerFinished(View view) {
        if (mRunning) {
            mRunning = false;
            performConfirmAction();
        }
    }

    @Override
    public void onTimerSelected(View view) {
        if (mRunning) {
            mDelayedView.reset();
            mRunning = false;
        } else {
            mDelayedView.setTotalTimeMs(CONFIRMATION_TIME_IN_MS);
            mRunning = true;
            mDelayedView.start();
        }
    }

    private void performConfirmAction() {
        confirmation = PriceUtils.confirmBooking(price);
        Intent intent = new Intent(this, ConfirmationActivity.class);
        if (confirmation.isSuccess()) {
            intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.SUCCESS_ANIMATION);
            intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Booking Confirmed!");
        } else {
            intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.FAILURE_ANIMATION);
            intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Booking Failed!");
        }
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (confirmation != null && confirmation.isSuccess()) {
            Intent resultIntent = new Intent();
            Bundle b = new Bundle();
            b.putBoolean("confirmed", true);
            resultIntent.putExtras(b);
            setResult(Activity.RESULT_OK, resultIntent);
            makeNotification();
            finish();
        }
    }

    private void makeNotification() {
        int notificationId = 101;
        Intent intent = new Intent(this, WearActionReceiver.class);
        intent.putExtra(WearActionReceiver.NOTIFICATION_ID_STRING, notificationId);
        intent.putExtra(WearActionReceiver.WEAR_ACTION, WearActionReceiver.OPEN_ON_PHONE);

        // Create pending intent and wrap our intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Building notification layout
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Booking Confirmed!")
                        .setContentText("Your booking reference is " + confirmation.getToken())
                        .addAction(R.drawable.ic_smartphone_black_24dp, "Open on Phone", pendingIntent);

        // instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and notify it using notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

}
