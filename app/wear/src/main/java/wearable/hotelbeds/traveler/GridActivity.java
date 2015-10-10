package wearable.hotelbeds.traveler;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wearable.hotelbeds.shared.GPSService;
import wearable.hotelbeds.shared.price.PriceInfoBean;
import wearable.hotelbeds.shared.price.PriceUtils;
import wearable.hotelbeds.traveler.grid.CustomCardFragment;
import wearable.hotelbeds.traveler.grid.SimplePage;
import wearable.hotelbeds.traveler.grid.SimpleRow;

public class GridActivity extends Activity {
    private static final int CONFIRM_ACTIVITY_ID = 0;

    private Bundle params;
    private DotsPageIndicator mPageIndicator;
    private GridViewPager mViewPager;
    private ArrayList<SimpleRow> mPages;
    Location location;
    GPSService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new GPSService();
        setContentView(R.layout.activity_grid);
        params = getIntent().getExtras();
        loadPrices(params.getString("eventId"));
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                // Get UI references
                mPageIndicator = (DotsPageIndicator) stub.findViewById(R.id.page_indicator);
                mViewPager = (GridViewPager) stub.findViewById(R.id.pager);
                // Assigns an adapter to provide the content for this pager
                mViewPager.setAdapter(new GridPagerAdapter(getFragmentManager(), mPages, GridActivity.this));
                mPageIndicator.setPager(mViewPager);
            }
        });
    }

    private void loadPrices(String eventId) {
        location = GPSService.getLocation(this);
        Toast.makeText(GridActivity.this,
                "Better location found: " + location.getLongitude() + ", " + location.getLatitude(), Toast.LENGTH_LONG)
                .show();
        List<PriceInfoBean> prices = PriceUtils.searchPrices(eventId, location);
        mPages = new ArrayList<SimpleRow>();
        SimpleRow row1 = new SimpleRow();
        for (PriceInfoBean price : prices) {
            row1.addPages(new SimplePage(price));
        }
        mPages.add(row1);
    }

    public void clicked(SimplePage page) {
        Intent intent = new Intent(this, ConfirmActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("key", page.getInfoBean());
        intent.putExtras(b);
        startActivityForResult(intent, CONFIRM_ACTIVITY_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CONFIRM_ACTIVITY_ID && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getExtras() != null && data.getExtras().getBoolean("confirmed")) {
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        }
    }


    private static final class GridPagerAdapter extends FragmentGridPagerAdapter {

        private ArrayList<SimpleRow> mPages;
        private GridActivity gridActivity;

        private GridPagerAdapter(FragmentManager fm, ArrayList<SimpleRow> data, GridActivity gridActivity) {
            super(fm);
            mPages = data;
            this.gridActivity = gridActivity;
        }

        @Override
        public Fragment getFragment(int row, int col) {
            CustomCardFragment fragment = CustomCardFragment.create(((SimpleRow) mPages.get(row)).getPages(col));
            fragment.setGridActivity(gridActivity);
            return fragment;
        }

        @Override
        public int getRowCount() {
            return mPages.size();
        }

        @Override
        public int getColumnCount(int row) {
            return mPages.get(row).size();
        }
    }
}