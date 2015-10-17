package wearable.hotelbeds.traveler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import wearable.hotelbeds.shared.price.BookingsBean;
import wearable.hotelbeds.shared.price.PriceUtils;
import wearable.hotelbeds.traveler.nav.MenuUtils;

public class BookingsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "RecyclerViewActivity";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private BookingsBean confirms;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.addPax);
        myFab.setVisibility(View.GONE);
        confirms = PriceUtils.getBookings(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        context = this;
        if (confirms != null && confirms.getConfirmDataBeans() != null && confirms.getConfirmDataBeans().size() > 0) {
            mAdapter = new BookingRecyclerViewAdapter(confirms.getConfirmDataBeans(), this);
            mRecyclerView.setAdapter(mAdapter);
            RecyclerView.ItemDecoration itemDecoration =
                    new DividerItemDecorator(this, LinearLayoutManager.VERTICAL);
            mRecyclerView.addItemDecoration(itemDecoration);
        }
        //Menu
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.hideOverflowMenu();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setCheckedItem(R.id.nav_bookings);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((BookingRecyclerViewAdapter) mAdapter).setOnItemClickListener(new BookingRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(v.getContext(), ConfirmActivity.class);
                Bundle b = new Bundle();
                b.putBoolean("confirmed", true);
                b.putSerializable("price", confirms.getConfirmDataBeans().get(position).getPrice());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        //ActionItemBadge.update(this, menu.findItem(R.id.item_samplebadge), FontAwesome.Icon.faw_group, ActionItemBadge.BadgeStyles.BLUE_LARGE, mTotalPaxes);

        SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(LOG_TAG, "Searching... " + query);
                changeAdapter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                Log.i(LOG_TAG, "Searching... " + query);
                if (query != null && query.length() >= 3) {
                    changeAdapter(query);//TODO para hacer este tipo de busqueda la respuesta tiene que ser muy rapida o sobre un objeto ya instanciado
                }
                return false;
            }

            private void changeAdapter(String query) {
                mAdapter = new BookingRecyclerViewAdapter(PriceUtils.searchEventByName(context, query), null);
                mRecyclerView.setAdapter(mAdapter);
            }
        };
        SearchView mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(searchListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_search:
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

