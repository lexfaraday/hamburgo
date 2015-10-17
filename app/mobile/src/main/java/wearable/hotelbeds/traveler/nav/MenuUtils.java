package wearable.hotelbeds.traveler.nav;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import wearable.hotelbeds.shared.price.BookingsBean;
import wearable.hotelbeds.shared.price.PriceUtils;
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
                menuItem.setChecked(true);
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
                BookingsBean bean = PriceUtils.getBookings(context);
                Log.i(TAG, "Bookings Selected");
                if (bean != null && bean.getConfirmDataBeans() != null && bean.getConfirmDataBeans().size() > 0) {
                    menuItem.setChecked(true);
                    intent = new Intent(context, BookingsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                    Bundle b = new Bundle();
                    b.putSerializable("bookings", PriceUtils.getBookings(context));
                    intent.putExtras(b);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "No available bookings", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
