package wearable.hotelbeds.traveler.grid;

import java.util.ArrayList;

/**
 * Created by Zavierazo on 07/10/2015.
 */
public class SimpleRow {

    ArrayList<SimplePage> mPagesRow = new ArrayList<SimplePage>();

    public void addPages(SimplePage page) {
        mPagesRow.add(page);
    }

    public SimplePage getPages(int index) {
        return mPagesRow.get(index);
    }

    public int size() {
        return mPagesRow.size();
    }
}
