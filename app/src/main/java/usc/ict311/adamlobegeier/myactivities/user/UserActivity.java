package usc.ict311.adamlobegeier.myactivities.user;

import android.support.v4.app.Fragment;

import usc.ict311.adamlobegeier.myactivities.SingleFragmentActivity;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class UserActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new UserFragment();
    }
}
