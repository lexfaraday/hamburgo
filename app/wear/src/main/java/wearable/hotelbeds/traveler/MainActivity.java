package wearable.hotelbeds.traveler;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

import java.util.List;

import wearable.hotelbeds.shared.event.EventInfoBean;
import wearable.hotelbeds.shared.event.EventUtils;

public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    private static final String TAG = "Traveler";
    private static final int ACTIVITY_ID_SPEECH_RECOGNIZER = 0;
    private static final int ACTIVITY_ID_LIST = 1;
    private static final int ACTIVITY_ID_PRICE = 2;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;


    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    private TextView mTextView;
    private int reconectTrys = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGoogleApiClient();
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });
        //Start the Speech Recognizer
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Start the Activity
        startActivityForResult(intent, ACTIVITY_ID_SPEECH_RECOGNIZER);
    }

    public void btnSimpleClick(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
        // Start the Activity
        startActivityForResult(intent, ACTIVITY_ID_SPEECH_RECOGNIZER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_ID_SPEECH_RECOGNIZER) {
            if (resultCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                boolean eventFound = false;
                if (results != null && results.size() > 0) {
                    String recognized = results.get(0);
                    try {
                        List<EventInfoBean> events = EventUtils.searchEventByName(recognized);
                        if (events != null && events.size() > 0) {
                            eventFound = true;
                            Intent intent = new Intent(this, SimpleListActivity.class);
                            Bundle b = new Bundle();
                            for (int i = 0; i < events.size(); i++) {
                                b.putSerializable("event" + i, events.get(i));
                            }
                            b.putParcelable("location", mCurrentLocation);
                            intent.putExtras(b);
                            mGoogleApiClient.disconnect();
                            startActivityForResult(intent, ACTIVITY_ID_LIST);
                        }
                    } catch (Exception e) {
                        Log.e("Traveler", e.getMessage());
                    }
                }
                if (!eventFound) {
                    mTextView.setText("No result");
                } else {
                    mTextView.setText("");
                }
            } else {
                mTextView.setText(getErrorText(resultCode));
            }
        } else if ((requestCode == ACTIVITY_ID_LIST || requestCode == ACTIVITY_ID_PRICE)) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null && data.getExtras() != null && data.getExtras().getBoolean("confirmed")) {
                    setResult(Activity.RESULT_OK, data);
                    mGoogleApiClient.disconnect();
                    finish();
                } else {
                    mGoogleApiClient.connect();
                }
            } else {
                mGoogleApiClient.connect();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No Match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "Error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }


    protected synchronized void buildGoogleApiClient() {
        Log.i(TAG, "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Wearable.API)
                .build();
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "Connected to GoogleApiClient");
        reconectTrys = 0;
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL_IN_MILLISECONDS)
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
        if (mCurrentLocation == null) {
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            logLocation();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            mCurrentLocation = location;
            logLocation();
        }
    }

    private void logLocation() {
        if (mCurrentLocation != null) {
            Log.i("Traveler", "Update location: Longitude(" + mCurrentLocation.getLongitude() + ") Latitude(" + mCurrentLocation.getLatitude() + ")");
        } else {
            Log.i("Traveler", "Update location:" + "null");
        }
    }


    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "Connection suspended");
        if (reconectTrys < 5) {
            mGoogleApiClient.connect();
            reconectTrys++;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
        if (reconectTrys < 5) {
            mGoogleApiClient.connect();
            reconectTrys++;
        }
    }

}
