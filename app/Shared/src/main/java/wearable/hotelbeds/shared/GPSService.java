package wearable.hotelbeds.shared;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Zavierazo on 10/10/2015.
 */
public class GPSService {
    /**
     * Returs the Location
     *
     * @return Location or null if no location is found
     */
    public static Location getLocation(Context context) {
        try {
            Location mLocation = null;
            LocationManager mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            // Getting GPS status
            boolean isGPSEnabled = mLocationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // If GPS enabled, get latitude/longitude using GPS Services
            if (isGPSEnabled) {
                if (mLocationManager != null) {
                    try {
                        mLocation = mLocationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                    if (mLocation != null) {
                        return mLocation;
                    }
                }
            }

            // If we are reaching this part, it means GPS was not able to fetch
            // any location
            // Getting network status
            boolean isNetworkEnabled = mLocationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (isNetworkEnabled) {
                if (mLocationManager != null) {
                    try {
                        mLocation = mLocationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                    if (mLocation != null) {
                        return mLocation;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gives you complete address of the location
     *
     * @return complete address in String
     */
    public static Address getLocationAddress(Location mLocation, Context mContext) {

        if (mLocation != null) {

            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
            // Get the current location from the input parameter list
            // Create a list to contain the result address
            List<Address> addresses = null;
            try {
                /*
                 * Return 1 address.
				 */
                addresses = geocoder.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);
            } catch (IOException e1) {
                e1.printStackTrace();
                return null;
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                return null;
            }
            // If the reverse geocode returned an address
            if (addresses != null && addresses.size() > 0) {
                // Get the first address
                Address address = addresses.get(0);
                return addresses.get(0);
            }
        }
        return null;
    }
}
