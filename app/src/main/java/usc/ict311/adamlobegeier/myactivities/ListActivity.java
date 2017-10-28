package usc.ict311.adamlobegeier.myactivities;

import android.support.v4.app.Fragment;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class ListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ListFragment();
    }
}
