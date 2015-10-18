package wearable.hotelbeds.traveler;

import android.app.Activity;
import android.os.Bundle;

public class QrActivity extends Activity {
    private static final String TAG = "Traveler";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
    }
}
