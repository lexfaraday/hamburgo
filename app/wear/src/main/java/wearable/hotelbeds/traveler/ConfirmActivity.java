package wearable.hotelbeds.traveler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.view.DelayedConfirmationView;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ConfirmActivity extends Activity implements
        DelayedConfirmationView.DelayedConfirmationListener {
    private TextView mTextView;
    private DelayedConfirmationView mDelayedView;
    private ImageButton mCancelButton;
    private boolean mRunning;
    private ConfirmActivity context;
    private Bundle params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        context = this;
        params = getIntent().getExtras();
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.confirm_panel);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.textView2);
                mTextView.setText(getResources().getString(R.string.total_amount) + " " + params.get("price"));
                mDelayedView = (DelayedConfirmationView) stub.findViewById(R.id.delayed_confirm);
                mDelayedView.setListener(context);
                mCancelButton = (ImageButton) stub.findViewById(R.id.cancel_button);
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
            mDelayedView.setTotalTimeMs(3000);
            mRunning = true;
            mDelayedView.start();
        }
    }

    private void performConfirmAction() {
        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.SUCCESS_ANIMATION);
        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Booking 1-541164 confirmed");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent resultIntent = new Intent();
        Bundle b = new Bundle();
        b.putBoolean("confirmed", true);
        resultIntent.putExtras(b);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
