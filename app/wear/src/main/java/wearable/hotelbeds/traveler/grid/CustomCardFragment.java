package wearable.hotelbeds.traveler.grid;

import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wearable.hotelbeds.traveler.GridActivity;
import wearable.hotelbeds.traveler.R;

/**
 * Created by Zavierazo on 07/10/2015.
 */
public class CustomCardFragment extends CardFragment {
    private SimplePage page;
    private GridActivity gridActivity;

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.row_simple_item_grid_layout, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                gridActivity.clicked(page);
            }
        });
        Bundle args = this.getArguments();
        if (args != null) {
            if (args.containsKey("mPrice")) {
                TextView text = (TextView) view.findViewById(R.id.price);
                if (text != null) {
                    text.setText(args.getCharSequence("mPrice"));
                }
            }
            if (args.containsKey("mCalendar")) {
                TextView text = (TextView) view.findViewById(R.id.calendario);
                if (text != null) {
                    text.setText(args.getCharSequence("mCalendar"));
                }
            }
            if (args.containsKey("mHotel")) {
                TextView text = (TextView) view.findViewById(R.id.hotel);
                if (text != null) {
                    text.setText(args.getCharSequence("mHotel"));
                }
            }
            if (args.containsKey("mFlyOut")) {
                TextView text = (TextView) view.findViewById(R.id.takeout);
                if (text != null) {
                    text.setText(args.getCharSequence("mFlyOut"));
                }
            }
            if (args.containsKey("mFlyIn")) {
                TextView text = (TextView) view.findViewById(R.id.takein);
                if (text != null) {
                    text.setText(args.getCharSequence("mFlyIn"));
                }
            }
        }
        return view;
    }

    public SimplePage getPage() {
        return page;
    }

    public void setPage(SimplePage page) {
        this.page = page;
    }

    public GridActivity getGridActivity() {
        return gridActivity;
    }

    public void setGridActivity(GridActivity gridActivity) {
        this.gridActivity = gridActivity;
    }

    public static CustomCardFragment create(SimplePage page) {
        CustomCardFragment fragment = new CustomCardFragment();
        Bundle args = new Bundle();
        if (page.mPrice != null) {
            args.putCharSequence("mPrice", page.mPrice);
        }
        if (page.mCalendar != null) {
            args.putCharSequence("mCalendar", page.mCalendar);
        }
        if (page.mHotel != null) {
            args.putCharSequence("mHotel", page.mHotel);
        }
        if (page.mFlyOut != null) {
            args.putCharSequence("mFlyOut", page.mFlyOut);
        }
        if (page.mFlyIn != null) {
            args.putCharSequence("mFlyIn", page.mFlyIn);
        }
        fragment.setArguments(args);
        fragment.setPage(page);
        return fragment;
    }
}
