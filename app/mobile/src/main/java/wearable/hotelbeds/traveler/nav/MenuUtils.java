package wearable.hotelbeds.traveler.nav;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import wearable.hotelbeds.shared.event.EventUtils;
import wearable.hotelbeds.shared.price.PriceUtils;
import wearable.hotelbeds.traveler.ConfirmActivity;
import wearable.hotelbeds.traveler.MainActivity;
import wearable.hotelbeds.traveler.R;

/**
 * Created by Zavierazo on 12/10/2015.
 */
public class MenuUtils {
    public static final int MENU_SELECTED_NONE = -1;
    public static final int MENU_SELECTED_MAIN = 0;
    public static final int MENU_SELECTED_BOOKINGS = 1;

    private static final String TAG = "MenuUtils";

    public static List<NavDrawerItem> getMenuItems(int activityId) {
        List<NavDrawerItem> data = new ArrayList<>();
        NavDrawerItem navItem = new NavDrawerItem();
        navItem.setTitle("Home");
        navItem.setChecked(activityId == MENU_SELECTED_MAIN);
        navItem.setIcon(R.drawable.ic_home_black_24dp);
        data.add(navItem);
        NavDrawerItem navItem2 = new NavDrawerItem();
        navItem2.setTitle("Bookings");
        navItem2.setChecked(activityId == MENU_SELECTED_BOOKINGS);
        navItem2.setIcon(R.drawable.ic_book_black_24dp);
        data.add(navItem2);
        return data;
    }

    public static void onMenuSelected(Context context, int activityId, int position) {
        //TODO Make actions
        if (activityId == position) {
            return;//Si se selecciona misma opcion en la que ya se encuentra no se hace nada.
        }
        Intent intent = null;
        switch (position) {
            case MENU_SELECTED_MAIN:
                //Home
                Log.i(TAG, "Home Selected");
                intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case MENU_SELECTED_BOOKINGS:
                //Bookings
                Log.i(TAG, "Bookings Selected");
                intent = new Intent(context, ConfirmActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("price", PriceUtils.searchPrices(EventUtils.obtainAllEvent().get(0), null).get(0));
                intent.putExtras(b);
                context.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
