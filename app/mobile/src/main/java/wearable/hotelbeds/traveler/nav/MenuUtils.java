package wearable.hotelbeds.traveler.nav;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import wearable.hotelbeds.traveler.BookingsActivity;
import wearable.hotelbeds.traveler.MainActivity;
import wearable.hotelbeds.traveler.R;

/**
 * Created by Zavierazo on 12/10/2015.
 */
public class MenuUtils {
    public static final int DRAWER_CLOSE_DELAY_MS = 250;
    private static final String TAG = "MenuUtils";

    public static void onMenuSelected(Context context, MenuItem menuItem) {
        //TODO Make actions
        menuItem.setChecked(true);
        Intent intent = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                //Home
                if (context instanceof MainActivity) {
                    return;
                }
                Log.i(TAG, "Home Selected");
                intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                context.startActivity(intent);
                break;
            case R.id.nav_bookings:
                //Bookings
                if (context instanceof BookingsActivity) {
                    return;
                }
                Log.i(TAG, "Bookings Selected");
                intent = new Intent(context, BookingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                context.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
