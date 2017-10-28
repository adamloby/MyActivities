package usc.ict311.adamlobegeier.myactivities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class MyActivity extends SingleFragmentActivity {

    private static final String EXTRA_ACTIVITY_ID =
            "usc.ict311.adamlobegeier.myactivities.activity_id";

    public static Intent newIntent(Context packageContext, UUID activityId) {
        Intent intent = new Intent(packageContext, MyActivity.class);
        intent.putExtra(EXTRA_ACTIVITY_ID, activityId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID activityId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_ACTIVITY_ID);
        return ActivityFragment.newInstance(activityId);
    }
}
