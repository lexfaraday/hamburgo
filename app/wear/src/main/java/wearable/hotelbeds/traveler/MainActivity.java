package wearable.hotelbeds.traveler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wearable.hotelbeds.shared.event.EventInfo;
import wearable.hotelbeds.shared.event.EventUtils;

public class MainActivity extends Activity {
    private static final int SPEECH_RECOGNIZER_REQUEST_CODE = 0;
    private static final int LIST_REQUEST_CODE = 1;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        startActivityForResult(intent, SPEECH_RECOGNIZER_REQUEST_CODE);
    }

    public void btnSimpleClick(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
        // Start the Activity
        startActivityForResult(intent, SPEECH_RECOGNIZER_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SPEECH_RECOGNIZER_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                boolean eventFound = false;
                for (String recognized : results) {
                    try {
                        List<EventInfo> events = EventUtils.searchEventByName(recognized);
                        if (events != null && events.size() > 0) {
                            eventFound = true;
                            Intent intent = new Intent(this, SimpleListActivity.class);
                            Bundle b = new Bundle();
                            ArrayList<String> id = new ArrayList<>();
                            ArrayList<String> nameList = new ArrayList<>();
                            ArrayList<String> description = new ArrayList<>();
                            ArrayList<String> price = new ArrayList<>();
                            for (EventInfo event : events) {
                                id.add(event.getId());
                                nameList.add(event.getName());
                                description.add(EventUtils.DATE_FORMATER.format(event.getTimeStart()) + " to " + EventUtils.DATE_FORMATER.format(event.getTimeEnd()));
                                price.add(event.getPrice().setScale(2, BigDecimal.ROUND_UP).toString());
                            }
                            b.putStringArrayList("id", id);
                            b.putStringArrayList("name", nameList);
                            b.putStringArrayList("description", description);
                            b.putStringArrayList("price", price);
                            intent.putExtras(b);
                            startActivityForResult(intent, LIST_REQUEST_CODE);
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
        } else if (requestCode == LIST_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getExtras() != null && data.getExtras().getBoolean("confirmed")) {
                setResult(Activity.RESULT_OK, data);
                finish();
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

}
