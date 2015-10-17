package wearable.hotelbeds.traveler;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

import wearable.hotelbeds.shared.event.EventInfoBean;
import wearable.hotelbeds.traveler.nav.MenuUtils;

public class WebActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "RecyclerViewActivity";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<EventInfoBean> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        WebView myWebView = (WebView) this.findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("MYAPP", "Page loaded");
            }
        });
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("https://popup-sandbox.herokuapp.com/#/setup?pid=makeitso_demo_ski");

        //Menu
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.hideOverflowMenu();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem) {
        MenuUtils.onMenuSelected(this, menuItem);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

