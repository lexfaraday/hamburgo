package wearable.hotelbeds.traveler;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import wearable.hotelbeds.shared.price.FlyBean;
import wearable.hotelbeds.shared.price.PriceInfoBean;
import wearable.hotelbeds.shared.price.PriceUtils;

/**
 * Created by lexfaraday on 08/10/15.
 */
public class HotelsFlightsRecyclerViewAdapter extends RecyclerView
        .Adapter<HotelsFlightsRecyclerViewAdapter.HotelFlightHolder> {
    private static String LOG_TAG = "HotelsFlightsRecyclerViewAdapter";
    private List<PriceInfoBean> mDataset;
    private static MyClickListener myClickListener;
    private LinearLayout arrivalFlys;
    private LinearLayout departureFlys;

    public static class HotelFlightHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView title;
        TextView shortDescription;
        TextView price;
        ImageView image;
        LinearLayout hotelImagesLineal;


        public HotelFlightHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleEvent);
            shortDescription = (TextView) itemView.findViewById(R.id.shortDescriptionEvent);
            price = (TextView) itemView.findViewById(R.id.priceEvent);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            hotelImagesLineal=(LinearLayout) itemView.findViewById(R.id.hotelImagesLineal);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //myClickListener.onItemClick(getPosition(), v);
            // Intent intent = new Intent(v.getContext(), HotelFlightActivity.class);
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        HotelsFlightsRecyclerViewAdapter.myClickListener = myClickListener;
    }

    public HotelsFlightsRecyclerViewAdapter(List<PriceInfoBean> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public HotelFlightHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_item, parent, false);

        HotelFlightHolder dataObjectHolder = new HotelFlightHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(HotelFlightHolder holder, int position) {

        Uri uri = Uri.parse(mDataset.get(position).getHotelInfo().getImages().get(0));

        View cell = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.horizontal_image_cell, null);
        ImageView imageView = (ImageView) cell.findViewById(R.id.image);


        Picasso.with(holder.image.getContext())
                .load(uri)
                //.fit()
                .resize(0, imageView.getLayoutParams().height)
                        //.placeholder(R.drawable.user_placeholder)
                        //.error(R.drawable.user_placeholder_error)
                .into(imageView);
        holder.hotelImagesLineal.addView(cell);

        departureFlys = (LinearLayout) holder.itemView.findViewById(R.id.departureRelative);

        for (FlyBean fly : mDataset.get(position).getFlyArrival()) {
            View v = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.fly_departure_element, null);
            TextView text = (TextView) v.findViewById(R.id.text);
            text.setText(fly.getCompany() + " " + fly.getDepartureAirport() + "-" + fly.getArrivalAirport() + " " + PriceUtils.FORMATER_HOUR.format(fly.getDeparture()));
            departureFlys.addView(v);
        }


        arrivalFlys = (LinearLayout) holder.itemView.findViewById(R.id.arrivalRelative);

        for (FlyBean fly : mDataset.get(position).getFlyDeparture()) {
            View v = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.fly_arrival_element, null);
            TextView text = (TextView) v.findViewById(R.id.text);
            text.setText(fly.getCompany() + " " + fly.getDepartureAirport() + "-" + fly.getArrivalAirport() + " " + PriceUtils.FORMATER_HOUR.format(fly.getDeparture()));
            arrivalFlys.addView(v);
        }
    }

    public void addItem(PriceInfoBean dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }
}
