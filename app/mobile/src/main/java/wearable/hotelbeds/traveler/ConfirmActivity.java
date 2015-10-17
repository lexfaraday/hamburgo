package wearable.hotelbeds.traveler;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import wearable.hotelbeds.traveler.nav.MenuUtils;

public class ConfirmActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = "ConfirmActivity";

    private TextView event;
    private TextView price;
    private TextView hotel;
    private TextView flyOut;
    private TextView flyIn;
    private TextView flySection;
    private PriceInfoBean priceBean;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        event = (TextView) findViewById(R.id.event);
        price = (TextView) findViewById(R.id.price);
        hotel = (TextView) findViewById(R.id.hotel);
        flyOut = (TextView) findViewById(R.id.takeout);
        flyIn = (TextView) findViewById(R.id.takein);
        flySection = (TextView) findViewById(R.id.fly_in_section_label);

        //Load info
        priceBean = (PriceInfoBean) getIntent().getExtras().getSerializable("price");
        if (priceBean != null) {
            event.setText(priceBean.getEvent().getName());
            if (priceBean.getTotalAmount().stripTrailingZeros().scale() <= 0) {
                price.setText(priceBean.getTotalAmount().toString());
            } else {
                price.setText(priceBean.getTotalAmount().setScale(2, BigDecimal.ROUND_UP).toString());
            }
            String mHotel = priceBean.getHotelName() + " " + priceBean.getRoomInfo() + " ";
            for (int i = 0; i < priceBean.getHotelStars(); i++) {
                mHotel += "*";
            }
            hotel.setText(mHotel);
            flySection.setText("Fly " + PriceUtils.DATE_FORMATER.format(priceBean.getFlyDeparture().get(0).getDeparture()) + "-" + PriceUtils.DATE_FORMATER.format(priceBean.getFlyArrival().get(0).getArrival()));

            //flyIn.setText(priceBean.getFlyOutAerolineName() + " " + PriceUtils.FORMATER_HOUR.format(priceBean.getFlyOut()));
            //flyOut.setText(priceBean.getFlyInAerolineName() + " " + PriceUtils.FORMATER_HOUR.format(priceBean.getFlyIn()));
        }


        //Menu
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

    }

    private View insertPhoto(String path) {
        Uri uri = Uri.parse(path);
        View cell = getLayoutInflater().inflate(R.layout.horizontal_image_cell, null);
        ImageView imageView = (ImageView) cell.findViewById(R.id.image);
        Picasso.with(imageView.getContext()).load(uri).resize(0, imageView.getLayoutParams().height).into(imageView);
        return cell;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem) {
        MenuUtils.onMenuSelected(this, menuItem);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void btnConfirmMIS(View v) {
        Log.i(TAG, "Confirmacion pulsada");
        ConfirmDataBean confirmBean = PriceUtils.confirmBooking(this, priceBean);
    }

    public void btnConfirmGP(View v) {
        Log.i(TAG, "Confirmacion pulsada");
        ConfirmDataBean confirmBean = PriceUtils.confirmBooking(this, priceBean);
    }

    public void btnCancel(View v) {
        finish();
    }

}

