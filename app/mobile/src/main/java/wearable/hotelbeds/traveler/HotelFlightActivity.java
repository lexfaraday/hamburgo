package wearable.hotelbeds.traveler;

<<<<<<< HEAD
=======
import android.content.Intent;
>>>>>>> origin/master
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
<<<<<<< HEAD
=======
import android.view.View;
>>>>>>> origin/master

import wearable.hotelbeds.shared.hotel.ProviderUtils;
<<<<<<< HEAD
=======
import wearable.hotelbeds.shared.price.PriceUtils;
>>>>>>> origin/master

/**
 * Created by lexfaraday on 12/10/15.
 */
public class HotelFlightActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private RecyclerView mHotelFlightRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        mHotelFlightRecyclerView = (RecyclerView) findViewById(R.id.hotelsCardView);
        mHotelFlightRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mHotelFlightRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new HotelsFlightsRecyclerViewAdapter(ProviderUtils.generateHotels());
        mHotelFlightRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecorator(this, LinearLayoutManager.VERTICAL);
        mHotelFlightRecyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((HotelsFlightsRecyclerViewAdapter) mAdapter).setOnItemClickListener(new HotelsFlightsRecyclerViewAdapter.MyClickListener() {
                                                                                 @Override
                                                                                 public void onItemClick(int position, View v) {
                                                                                     Intent intent = new Intent(v.getContext(), ConfirmActivity.class);
                                                                                     Bundle b = new Bundle();
                                                                                     b.putSerializable("price", PriceUtils.searchPrices(EventUtils.obtainAllEvent().get(0), null).get(0));
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
