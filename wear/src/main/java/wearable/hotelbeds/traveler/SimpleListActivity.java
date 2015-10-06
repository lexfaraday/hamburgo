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

public class SimpleListActivity extends Activity implements WearableListView.ClickListener {

    private WearableListView mListView;
    private Bundle params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        params = getIntent().getExtras();
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mListView = (WearableListView) stub.findViewById(R.id.listView1);
                mListView.setAdapter(new MyAdapter(SimpleListActivity.this));
                mListView.setClickListener(SimpleListActivity.this);
                mListView.setMaximizeSingleItem(true);
            }
        });
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        Intent intent = new Intent(this, ConfirmActivity.class);
        Bundle b = new Bundle();
        b.putString("price", params.getStringArrayList("name").get(viewHolder.getAdapterPosition()));
        intent.putExtras(b);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
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
            private TextView textViewDescription;

            public ItemViewHolder(View itemView) {
                super(itemView);
                // find the text view within the custom item's layout
                textViewTitle = (TextView) itemView.findViewById(R.id.textView);
                textViewDescription = (TextView) itemView.findViewById(R.id.textView_Content);
            }
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(mInflater.inflate(R.layout.row_simple_item_layout, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            itemHolder.textViewTitle.setText(params.getStringArrayList("name").get(position));
            itemHolder.textViewDescription.setText(params.getStringArrayList("description").get(position));
            holder.itemView.setTag(position);

        }

        @Override
        public int getItemCount() {
            return params.getStringArrayList("name").size();
        }

    }
}