package wearable.hotelbeds.traveler.list;

import android.content.Context;
import android.support.wearable.view.CardFrame;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import wearable.hotelbeds.traveler.R;

/**
 * Created by Zavierazo on 04/10/2015.
 */
public class WearableListItemLayout extends LinearLayout implements WearableListView.OnCenterProximityListener {

    private CardFrame mFrame;
    private TextView mDescription;
    private TextView mName;

    public WearableListItemLayout(Context context) {
        this(context, null);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
    }

    // Get references to the icon and text in the item layout definition
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // These are defined in the layout file for list items
        // (see next section)
        mName = (TextView) findViewById(R.id.title_list);
        mDescription = (TextView) findViewById(R.id.price_list);
        mFrame = (CardFrame) findViewById(R.id.cardFrameItem);
    }

    @Override
    public void onCenterPosition(boolean animate) {
    }

    @Override
    public void onNonCenterPosition(boolean animate) {
    }
}
