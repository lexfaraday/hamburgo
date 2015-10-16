package wearable.hotelbeds.traveler;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import wearable.hotelbeds.shared.hotel.HotelInfo;

/**
 * Created by lexfaraday on 08/10/15.
 */
public class HotelsFlightsRecyclerViewAdapter extends RecyclerView
        .Adapter<HotelsFlightsRecyclerViewAdapter.HotelFlightHolder> {
    private static String LOG_TAG = "HotelsFlightsRecyclerViewAdapter";
    private ArrayList<HotelInfo> mDataset;
    private static MyClickListener myClickListener;

    public static class HotelFlightHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView title;
        TextView shortDescription;
        TextView price;
        ImageView image;

        public HotelFlightHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleEvent);
            shortDescription = (TextView) itemView.findViewById(R.id.shortDescriptionEvent);
            price = (TextView) itemView.findViewById(R.id.priceEvent);
            image = (ImageView) itemView.findViewById(R.id.imageView);
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

    public HotelsFlightsRecyclerViewAdapter(ArrayList<HotelInfo> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public HotelFlightHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);

        HotelFlightHolder dataObjectHolder = new HotelFlightHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(HotelFlightHolder holder, int position) {

        Uri uri = Uri.parse(mDataset.get(position).images.get(0));
        Picasso.with(holder.image.getContext())
                .load(uri)
                .fit()
                        //.placeholder(R.drawable.user_placeholder)
                        //.error(R.drawable.user_placeholder_error)
                .into(holder.image);

        holder.title.setText(mDataset.get(position).name);
        holder.price.setText(mDataset.get(position).price + "€");
    }

    public void addItem(HotelInfo dataObj, int index) {
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
