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

import org.w3c.dom.Text;

import java.util.ArrayList;

import wearable.hotelbeds.shared.event.EventInfoBean;

/**
 * Created by lexfaraday on 08/10/15.
 */
public class EventRecyclerViewAdapter extends RecyclerView
        .Adapter<EventRecyclerViewAdapter.EventHolder> {
    private static String LOG_TAG = "EventRecyclerViewAdapter";
    private ArrayList<EventInfoBean> mDataset;
    private static MyClickListener myClickListener;

    public static class EventHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView title;
        TextView shortDescription;
        TextView price;
        ImageView image;

        public EventHolder(View itemView) {
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
            myClickListener.onItemClick(getPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public EventRecyclerViewAdapter(ArrayList<EventInfoBean> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        EventHolder dataObjectHolder = new EventHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {

        Uri uri = Uri.parse(mDataset.get(position).getImageUrl());
        Picasso.with(holder.image.getContext())
                .load(uri)
                .fit()
                        //.placeholder(R.drawable.user_placeholder)
                        //.error(R.drawable.user_placeholder_error)
                .into(holder.image);

        holder.title.setText(mDataset.get(position).getName());
        String complexShortDescription = "Start at " + mDataset.get(position).getTimeStart() + " to " + mDataset.get(position).getTimeEnd();
        complexShortDescription += " " + mDataset.get(position).getShortDescription();
        holder.shortDescription.setText(complexShortDescription);

        holder.price.setText(mDataset.get(position).getPrice().toString() + "€");
    }

    public void addItem(EventInfoBean dataObj, int index) {
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
        public void onItemClick(int position, View v);
    }
}