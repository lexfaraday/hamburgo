package wearable.hotelbeds.traveler;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;

import java.util.ArrayList;

import wearable.hotelbeds.traveler.grid.CustomCardFragment;
import wearable.hotelbeds.traveler.grid.SimplePage;
import wearable.hotelbeds.traveler.grid.SimpleRow;

public class GridActivity extends Activity {
    private Bundle params;
    private DotsPageIndicator mPageIndicator;
    private GridViewPager mViewPager;
    private ArrayList<SimpleRow> mPages;

    private void initDummy() {
        mPages = new ArrayList<SimpleRow>();

        SimpleRow row1 = new SimpleRow();
        row1.addPages(new SimplePage("20 €", "16/10/2015 - 17/10/2015","Hilton DBL-ST *****", "Vueling 17/10/2015 20:10","Air Berlin 16/10/2015 06:40"));

        SimpleRow row2 = new SimpleRow();
        row2.addPages(new SimplePage("20 €", "16/10/2015 - 17/10/2015","Hilton DBL-ST *****", "Vueling 17/10/2015 20:10","Air Berlin 16/10/2015 06:40"));


        SimpleRow row3 = new SimpleRow();
        row3.addPages(new SimplePage("20 €", "16/10/2015 - 17/10/2015","Hilton DBL-ST *****", "Vueling 17/10/2015 20:10","Air Berlin 16/10/2015 06:40"));

        SimpleRow row4 = new SimpleRow();
        row4.addPages(new SimplePage("20 €", "16/10/2015 - 17/10/2015","Hilton DBL-ST *****", "Vueling 17/10/2015 20:10","Air Berlin 16/10/2015 06:40"));

        mPages.add(row1);
        mPages.add(row2);
        mPages.add(row3);
        mPages.add(row4);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        initDummy();
        params = getIntent().getExtras();
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                // Get UI references
                mPageIndicator = (DotsPageIndicator) stub.findViewById(R.id.page_indicator);
                mViewPager = (GridViewPager) stub.findViewById(R.id.pager);
                // Assigns an adapter to provide the content for this pager
                mViewPager.setAdapter(new GridPagerAdapter(getFragmentManager(), mPages));
                mPageIndicator.setPager(mViewPager);
            }
        });
    }

    private static final class GridPagerAdapter extends FragmentGridPagerAdapter {

        private ArrayList<SimpleRow> mPages;

        private GridPagerAdapter(FragmentManager fm, ArrayList<SimpleRow> data) {
            super(fm);
            mPages = data;
        }

        @Override
        public Fragment getFragment(int row, int col) {
            return CustomCardFragment.create(((SimpleRow) mPages.get(row)).getPages(col));
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