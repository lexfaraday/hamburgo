package wearable.hotelbeds.traveler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wearable.hotelbeds.shared.event.EventInfoBean;

public class SimpleListActivity extends Activity implements WearableListView.ClickListener {

    private static final int PRICE_ACTIVITY_ID = 0;

    private WearableListView mListView;
    private List<EventInfoBean> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        int i = 0;
        events = new ArrayList<>();
        while (getIntent().getExtras().containsKey("event" + i)) {
            events.add((EventInfoBean) getIntent().getExtras().getSerializable("event" + i));
            i++;
        }
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mListView = (WearableListView) stub.findViewById(R.id.listView1);
                mListView.setAdapter(new MyAdapter(SimpleListActivity.this));
                mListView.setClickListener(SimpleListActivity.this);
                mListView.setHasFixedSize(false);
            }
        });
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        Intent intent = new Intent(this, GridActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("event", events.get(viewHolder.getAdapterPosition()));
        b.putParcelable("location", getIntent().getExtras().getParcelable("location"));
        intent.putExtras(b);
        startActivityForResult(intent, PRICE_ACTIVITY_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PRICE_ACTIVITY_ID && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getExtras() != null && data.getExtras().getBoolean("confirmed")) {
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        }
    }

    @Override
    public void onTopEmptyRegionClick() {

    }


    private class MyAdapter extends WearableListView.Adapter {
        private final LayoutInflater mInflater;

        private MyAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        // Provide a reference to the type of views you're using
        public class ItemViewHolder extends WearableListView.ViewHolder {
            private TextView textViewTitle;
            private TextView textViewPrice;

            public ItemViewHolder(View itemView) {
                super(itemView);
                // find the text view within the custom item's layout
                textViewTitle = (TextView) itemView.findViewById(R.id.title_list);
                textViewPrice = (TextView) itemView.findViewById(R.id.price_list);
            }
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(mInflater.inflate(R.layout.row_simple_item_list_layout, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            String amount;
            if (events.get(position).getPrice().stripTrailingZeros().scale() <= 0) {
                amount = String.valueOf(events.get(position).getPrice());
            } else {
                amount = String.valueOf(events.get(position).getPrice().setScale(2, BigDecimal.ROUND_UP));
            }
            itemHolder.textViewPrice.setText(amount + "€");
            itemHolder.textViewTitle.setText(events.get(position).getName());
            holder.itemView.setTag(position);

        }


        @Override
        public int getItemCount() {
            return events.size();
        }

    }
}