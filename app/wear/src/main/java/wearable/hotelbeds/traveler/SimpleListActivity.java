package wearable.hotelbeds.traveler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

public class SimpleListActivity extends Activity implements WearableListView.ClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private static final int PRICE_ACTIVITY_ID = 0;
    private static final String TAG = "Traveler";

    private WearableListView mListView;
    private Bundle params;
    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGoogleApiClient();
        setContentView(R.layout.activity_listview);
        params = getIntent().getExtras();
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mListView = (WearableListView) stub.findViewById(R.id.listView1);
                mListView.setAdapter(new MyAdapter(SimpleListActivity.this));
                mListView.setClickListener(SimpleListActivity.this);
                mListView.setHasFixedSize(false);
            }
        });
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        Intent intent = new Intent(this, GridActivity.class);
        Bundle b = new Bundle();
        b.putString("eventId", params.getStringArrayList("id").get(viewHolder.getAdapterPosition()));
        b.putParcelable("location", mCurrentLocation);
        intent.putExtras(b);
        startActivityForResult(intent, PRICE_ACTIVITY_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PRICE_ACTIVITY_ID && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getExtras() != null && data.getExtras().getBoolean("confirmed")) {
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        }
    }

    @Override
    public void onTopEmptyRegionClick() {

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
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }


    private class MyAdapter extends WearableListView.Adapter {
        private final LayoutInflater mInflater;

        private MyAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        // Provide a reference to the type of views you're using
        public class ItemViewHolder extends WearableListView.ViewHolder {
            private TextView textViewTitle;
            private TextView textViewPrice;

            public ItemViewHolder(View itemView) {
                super(itemView);
                // find the text view within the custom item's layout
                textViewTitle = (TextView) itemView.findViewById(R.id.title_list);
                textViewPrice = (TextView) itemView.findViewById(R.id.price_list);
            }
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(mInflater.inflate(R.layout.row_simple_item_list_layout, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            itemHolder.textViewPrice.setText(params.getStringArrayList("price").get(position) + "€");
            itemHolder.textViewTitle.setText(params.getStringArrayList("name").get(position));
            holder.itemView.setTag(position);

        }


        @Override
        public int getItemCount() {
            return params.getStringArrayList("id").size();
        }

    }
}