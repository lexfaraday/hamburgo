package wearable.hotelbeds.traveler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wearable.hotelbeds.shared.price.ConfirmDataBean;
import wearable.hotelbeds.shared.price.FlyBean;
import wearable.hotelbeds.shared.price.PriceInfoBean;
import wearable.hotelbeds.shared.price.PriceUtils;
import wearable.hotelbeds.traveler.nav.MenuUtils;

public class ConfirmActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = "ConfirmActivity";
    private static int CONFIRM_ACTIVITY_ID = 0;

    private TextView event;
    private TextView price;
    private TextView hotel;
    private TextView flySection;
    private PriceInfoBean priceBean;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout departureFlys;
    private LinearLayout arrivalFlys;
    private LinearLayout buttons;
    private CardView more_detail;
    private FloatingActionButton more_detail_button;
    private LinearLayout modeDetailDiv;
    private ImageView qr;
    private boolean isConfirmed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        event = (TextView) findViewById(R.id.event);
        price = (TextView) findViewById(R.id.price);
        hotel = (TextView) findViewById(R.id.hotel);
        flySection = (TextView) findViewById(R.id.fly_in_section_label);
        departureFlys = (LinearLayout) findViewById(R.id.flydeparture_div);
        arrivalFlys = (LinearLayout) findViewById(R.id.flyarrival_div);
        buttons = (LinearLayout) findViewById(R.id.buttons);
        qr = (ImageView) findViewById(R.id.qr);
        more_detail = (CardView) findViewById(R.id.card_detail);
        more_detail_button = (FloatingActionButton) findViewById(R.id.mode_detail_button);
        modeDetailDiv = (LinearLayout) findViewById(R.id.mode_detail_div);

        //Load info
        isConfirmed = getIntent().getExtras().getBoolean("confirmed");
        if (isConfirmed) {
            showAsConfirmed();
        } else {
            buttons.setVisibility(View.VISIBLE);
        }
        priceBean = (PriceInfoBean) getIntent().getExtras().getSerializable("price");
        if (priceBean != null) {
            event.setText(priceBean.getEvent().getName());
            if (priceBean.getTotalAmount().stripTrailingZeros().scale() <= 0) {
                price.setText(priceBean.getTotalAmount().toString());
            } else {
                price.setText(priceBean.getTotalAmount().setScale(2, BigDecimal.ROUND_UP).toString());
            }
            String mHotel = (priceBean.getHotelInfo().getName() + " " + priceBean.getHotelInfo().getCodHab() + " " + priceBean.getHotelInfo().getReg() + " ");
            for (int i = 0; i < priceBean.getHotelInfo().getStars(); i++) {
                mHotel += "*";
            }
            hotel.setText(mHotel);
            flySection.setText("Fly " + PriceUtils.DATE_FORMATER.format(priceBean.getFlyDeparture().get(0).getDeparture()) + "-" + PriceUtils.DATE_FORMATER.format(priceBean.getFlyArrival().get(0).getArrival()));
            if (priceBean.getFlyDeparture() != null && priceBean.getFlyDeparture().size() > 0) {
                List<String> points = new ArrayList<>();
                for (FlyBean fly : priceBean.getFlyDeparture()) {
                    View v = LayoutInflater.from(this).inflate(R.layout.fly_departure_element, null);
                    TextView text = (TextView) v.findViewById(R.id.text);

                    text.setText(fly.getCompany() + " " + fly.getDepartureAirport() + "-" + fly.getArrivalAirport() + " " + PriceUtils.FORMATER_HOUR.format(fly.getDeparture()));
                    departureFlys.addView(v);
                    buildFlyMessage(points, fly);
                }
                if (priceBean.getFlyDeparture().get(0).getAmount() != null) {
                    addDetailedSection("Departure Flight - " + priceBean.getFlyDeparture().get(0).getAmount().setScale(2, BigDecimal.ROUND_UP).toString() + "€", points);
                } else {
                    addDetailedSection("Departure Flight", points);
                }
            }
            if (priceBean.getFlyArrival() != null && priceBean.getFlyArrival().size() > 0) {
                List<String> points = new ArrayList<>();
                for (FlyBean fly : priceBean.getFlyArrival()) {
                    View v = LayoutInflater.from(this).inflate(R.layout.fly_arrival_element, null);
                    TextView text = (TextView) v.findViewById(R.id.text);
                    text.setText(fly.getCompany() + " " + fly.getDepartureAirport() + "-" + fly.getArrivalAirport() + " " + PriceUtils.FORMATER_HOUR.format(fly.getDeparture()));
                    arrivalFlys.addView(v);
                    buildFlyMessage(points, fly);
                }
                if (priceBean.getFlyArrival().get(0).getAmount() != null) {
                    addDetailedSection("Arrival Flight - " + priceBean.getFlyArrival().get(0).getAmount().setScale(2, BigDecimal.ROUND_UP).toString() + "€", points);
                } else {
                    addDetailedSection("Arrival Flight", points);
                }

            }

            //MoreDetail
            List<String> points = new ArrayList<>();
            points.add(priceBean.getHotelInfo().getName() + " of " + priceBean.getHotelInfo().getStars() + " stars. " + priceBean.getHotelInfo().getCodHab() + " with " + priceBean.getHotelInfo().getReg() + ".");
            if (priceBean.getHotelInfo().getPrice() != null) {
                addDetailedSection("Hotel - " + priceBean.getHotelInfo().getPrice().setScale(2, BigDecimal.ROUND_UP).toString() + "€", points);
            } else {
                addDetailedSection("Hotel", points);
            }

            points = new ArrayList<>();
            points.add(priceBean.getEvent().getName() + " from " + PriceUtils.DATE_FORMATER_HOUR.format(priceBean.getEvent().getTimeStart()) + " to " + PriceUtils.DATE_FORMATER_HOUR.format(priceBean.getEvent().getTimeEnd()) + ". " + priceBean.getEvent().getShortDescription() + ".");
            if (priceBean.getEvent().getPrice() != null) {
                addDetailedSection("Event - " + priceBean.getEvent().getPrice().setScale(2, BigDecimal.ROUND_UP).toString() + "€", points);
            } else {
                addDetailedSection("Event", points);

            }
        }

        //Menu
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

    }

    private void buildFlyMessage(List<String> points, FlyBean fly) {
        if (points.size() == 0) {
            points.add("Departure at " + PriceUtils.DATE_FORMATER_HOUR.format(fly.getDeparture()) + " from " + fly.getDepartureAirport() + " with " + fly.getCompany() + ". Arrival at " + PriceUtils.DATE_FORMATER_HOUR.format(fly.getArrival()) + " to " + fly.getArrivalAirport() + ".");
        } else {
            points.add("Stopover in " + fly.getDepartureAirport() + " with departure at " + PriceUtils.DATE_FORMATER_HOUR.format(fly.getDeparture()) + " with " + fly.getCompany() + ". Arrival at " + PriceUtils.DATE_FORMATER_HOUR.format(fly.getArrival()) + " to " + fly.getArrivalAirport() + ".");
        }
    }

    private void addDetailedSection(String section, List<String> points) {
        View v = LayoutInflater.from(this).inflate(R.layout.detail_element, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.layout);
        TextView seccion = (TextView) v.findViewById(R.id.seccion);
        seccion.setText(section);
        for (String line : points) {
            LinearLayout textLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.text_element, null);
            TextView text = (TextView) textLayout.findViewById(R.id.texto);
            text.setText(line);
            layout.addView(textLayout);
        }
        modeDetailDiv.addView(v);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
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
        Log.i(TAG, "Confirmacion MakeItSocial pulsada");
        Intent intent = new Intent(v.getContext(), WebActivity.class);
        startActivityForResult(intent, CONFIRM_ACTIVITY_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CONFIRM_ACTIVITY_ID && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getExtras() != null && data.getExtras().getBoolean("confirmed")) {
                ConfirmDataBean confirmBean = PriceUtils.confirmBooking(this, priceBean);
                showAsConfirmed();
            }
        }
    }

    public void btnConfirmGP(View v) {
        Log.i(TAG, "Confirmacion GoogleWallet pulsada");
        ConfirmDataBean confirmBean = PriceUtils.confirmBooking(this, priceBean);
        showAsConfirmed();
    }

    public void showAsConfirmed() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buttons.setVisibility(View.GONE);
        qr.setVisibility(View.VISIBLE);
        qr.animate().alpha(1).scaleY(1);
    }

    public void btnShowMoreInfo(View v) {
        Log.i(TAG, "ShowMoreInfo pulsado");
        if (more_detail.getScaleY() == 1) {
            more_detail.animate().alpha(0).scaleY(0).withEndAction(new Runnable() {
                @Override
                public void run() {
                    more_detail.setVisibility(View.GONE);
                }
            });
            more_detail_button.setImageDrawable(getDrawable(R.drawable.ic_info_outline_black_24dp));
        } else {
            more_detail.setVisibility(View.VISIBLE);
            more_detail.animate().alpha(1).scaleY(1);
            more_detail_button.setImageDrawable(getDrawable(R.drawable.ic_close_black_24dp));
        }
    }

}

