package wearable.hotelbeds.traveler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import java.util.List;

import wearable.hotelbeds.shared.event.EventInfoBean;
import wearable.hotelbeds.shared.price.PriceInfoBean;
import wearable.hotelbeds.shared.price.PriceUtils;

/**
 * Created by lexfaraday on 12/10/15.
 */
public class HotelFlightActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EventInfoBean event;
    private List<PriceInfoBean> prices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_flight);
        prepareMenu();
        loadR();
    }

    private void prepareMenu() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        // Back navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadR() {
        RecyclerView mHotelFlightRecyclerView = (RecyclerView) findViewById(R.id.hotelFlightRecyclerView);
        mHotelFlightRecyclerView.setHasFixedSize(true);
        // 2. set layoutManger
        mHotelFlightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 3. An adapter
        event = (EventInfoBean) getIntent().getExtras().getSerializable("event");
        prices = PriceUtils.searchPrices(event, null, 1);
        mAdapter = new HotelsFlightsRecyclerViewAdapter(prices);//TODO Put gps location
        mHotelFlightRecyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        mHotelFlightRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((HotelsFlightsRecyclerViewAdapter) mAdapter).setOnItemClickListener(new HotelsFlightsRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(v.getContext(), ConfirmActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("price", prices.get(position));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
