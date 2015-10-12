package wearable.hotelbeds.traveler;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import wearable.hotelbeds.shared.event.EventUtils;
import wearable.hotelbeds.traveler.nav.FragmentDrawer;
import wearable.hotelbeds.traveler.nav.MenuUtils;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "RecyclerViewActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventRecyclerViewAdapter(EventUtils.obtainAllEvent());
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecorator(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        //Menu
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FragmentDrawer drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar, MenuUtils.MENU_SELECTED_MAIN);
        drawerFragment.setDrawerListener(this);


        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((EventRecyclerViewAdapter) mAdapter).setOnItemClickListener(new
                                                                             EventRecyclerViewAdapter.MyClickListener() {
                                                                                 @Override
                                                                                 public void onItemClick(int position, View v) {
                                                                                     Log.i(LOG_TAG, " Clicked on Item " + position);
                                                                                 }
                                                                             });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        searchMenuItem.setVisible(true);//Poner esto si se quiere que en la activity se vea el boton buscar :-)
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
                changeAdapter(query);//TODO para hacer este tipo de busqueda la respuesta tiene que ser muy rapida o sobre un objeto ya instanciado
                return false;
            }

            private void changeAdapter(String query) {
                mAdapter = new EventRecyclerViewAdapter(EventUtils.searchEventByName(query));
                mRecyclerView.setAdapter(mAdapter);
            }
        };
        SearchView mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(searchListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        MenuUtils.onMenuSelected(this, MenuUtils.MENU_SELECTED_MAIN, position);
    }


}

