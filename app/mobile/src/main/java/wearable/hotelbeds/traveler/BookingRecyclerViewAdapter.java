package wearable.hotelbeds.traveler;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wearable.hotelbeds.shared.price.ConfirmDataBean;
import wearable.hotelbeds.shared.price.PriceUtils;

/**
 * Created by lexfaraday on 08/10/15.
 */
public class BookingRecyclerViewAdapter extends RecyclerView
        .Adapter<BookingRecyclerViewAdapter.EventHolder> {
    private static String LOG_TAG = "EventRecyclerViewAdapter";
    private List<ConfirmDataBean> mDataset;
    private static MyClickListener myClickListener;
    private AppCompatActivity activity;


    public void setOnItemClickListener(MyClickListener myClickListener) {
        BookingRecyclerViewAdapter.myClickListener = myClickListener;
    }

    public BookingRecyclerViewAdapter(List<ConfirmDataBean> myDataset, AppCompatActivity activity) {
        mDataset = myDataset;
        this.activity = activity;
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent,
                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_item, parent, false);

        EventHolder dataObjectHolder = new EventHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getPrice().getEvent().getName());
        String complexShortDescription = "From " + PriceUtils.DATE_FORMATER_HOUR.format(mDataset.get(position).getPrice().getEvent().getTimeStart()) + " to " + PriceUtils.DATE_FORMATER_HOUR.format(mDataset.get(position).getPrice().getEvent().getTimeEnd()) + ". Hotel " + mDataset.get(position).getPrice().getHotelInfo().getName();
        holder.shortDescription.setText(complexShortDescription);

        holder.price.setText(mDataset.get(position).getPrice().getTotalAmount() + "€");
        holder.booking_number.setText(mDataset.get(position).getToken());
    }

    public void addItem(ConfirmDataBean dataObj, int index) {
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


    public static class EventHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView title;
        TextView shortDescription;
        TextView price;
        ImageView image;
        TextView booking_number;

        public EventHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleEvent);
            shortDescription = (TextView) itemView.findViewById(R.id.shortDescriptionEvent);
            price = (TextView) itemView.findViewById(R.id.priceEvent);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            booking_number = (TextView) itemView.findViewById(R.id.booking_number);
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


}