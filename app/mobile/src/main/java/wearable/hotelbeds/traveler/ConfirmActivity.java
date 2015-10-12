package wearable.hotelbeds.traveler;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import wearable.hotelbeds.shared.price.ConfirmDataBean;
import wearable.hotelbeds.shared.price.PriceInfoBean;
import wearable.hotelbeds.shared.price.PriceUtils;
import wearable.hotelbeds.traveler.nav.FragmentDrawer;
import wearable.hotelbeds.traveler.nav.MenuUtils;

public class ConfirmActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = "ConfirmActivity";

    private ImageView eventImage;
    private TextView price;
    private TextView calendar;
    private TextView hotel;
    private TextView flyOut;
    private TextView flyIn;
    private PriceInfoBean priceBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        eventImage = (ImageView) findViewById(R.id.eventImage);
        price = (TextView) findViewById(R.id.price);
        calendar = (TextView) findViewById(R.id.calendario);
        hotel = (TextView) findViewById(R.id.hotel);
        flyOut = (TextView) findViewById(R.id.takeout);
        flyIn = (TextView) findViewById(R.id.takein);

        //Load info
        priceBean = (PriceInfoBean) getIntent().getExtras().getSerializable("price");
        if (priceBean != null) {

            Uri uri = Uri.parse(priceBean.getEvent().getImageUrl());
            Picasso.with(eventImage.getContext()).load(uri).fit().into(eventImage);
            if (priceBean.getTotalAmount().stripTrailingZeros().scale() <= 0) {
                price.setText(priceBean.getTotalAmount().toString() + " €");
            } else {
                price.setText(priceBean.getTotalAmount().setScale(2, BigDecimal.ROUND_UP).toString() + " €");
            }
            calendar.setText(PriceUtils.DATE_FORMATER.format(priceBean.getFlyOut()) + " - " + PriceUtils.DATE_FORMATER.format(priceBean.getFlyIn()));
            String mHotel = priceBean.getHotelName() + " " + priceBean.getRoomInfo() + " ";
            for (int i = 0; i < priceBean.getHotelStars(); i++) {
                mHotel += "*";
            }
            hotel.setText(mHotel);
            flyIn.setText(priceBean.getFlyOutAerolineName() + " " + PriceUtils.DATE_FORMATER_HOUR.format(priceBean.getFlyOut()));
            flyOut.setText(priceBean.getFlyInAerolineName() + " " + PriceUtils.DATE_FORMATER_HOUR.format(priceBean.getFlyIn()));
        }


        //Menu
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FragmentDrawer drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar, MenuUtils.MENU_SELECTED_NONE);
        drawerFragment.setDrawerListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        MenuUtils.onMenuSelected(this, MenuUtils.MENU_SELECTED_NONE, position);
    }

    public void btnConfirm(View v) {
        Log.i(TAG, "Confirmacion pulsada");
        ConfirmDataBean confirmBean = PriceUtils.confirmBooking(this, priceBean);
    }

    public void btnCancel(View v) {
        finish();
    }

}

