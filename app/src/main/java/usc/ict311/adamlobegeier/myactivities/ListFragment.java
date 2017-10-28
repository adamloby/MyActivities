package usc.ict311.adamlobegeier.myactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import usc.ict311.adamlobegeier.myactivities.user.UserActivity;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class ListFragment extends Fragment {

    private RecyclerView mActivityRecyclerView;
    private ActivityAdapter mAdapter;
    private Button mLogButton;
    private Button mSettingsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        mActivityRecyclerView = (RecyclerView) view.findViewById(R.id.activity_recycler_view);
        mActivityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mLogButton = (Button) view.findViewById(R.id.log_button);
        mLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = new Activity();
                ActivityLab.get(getActivity()).addActivity(activity);
                Intent intent = MyActivity.newIntent(getActivity(), activity.getId());
                startActivity(intent);
            }
        });

        mSettingsButton = (Button) view.findViewById(R.id.settings_button);
        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
            }
        });

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        ActivityLab activityLab = ActivityLab.get(getActivity());
        List<Activity> activities = activityLab.getActivities();

        if (mAdapter == null) {
            mAdapter = new ActivityAdapter(activities);
            mActivityRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setActivities(activities);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class ActivityHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Activity mActivity;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mPlaceTextView;

        public void bindActivity(Activity activity) {
            mActivity = activity;
            mTitleTextView.setText(mActivity.getTitle());
            mDateTextView.setText(mActivity.getDate().toString());

            if (mActivity.getPlace() == null) {
                mPlaceTextView.setText("Location not provided");
            } else {
                mPlaceTextView.setText("Location: " + mActivity.getPlace());
            }
        }

        public ActivityHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_activity_title_text_view);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_activity_date_text_view);
            mPlaceTextView = (TextView)
                    itemView.findViewById(R.id.list_item_activity_place_text_view);
        }

        @Override
        public void onClick(View v) {
            Intent intent = MyActivity.newIntent(getActivity(), mActivity.getId());
            startActivity(intent);
        }
    }

    private class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder> {
        private List<Activity> mActivities;

        public ActivityAdapter(List<Activity> activities) {
            mActivities = activities;
        }

        @Override
        public ActivityHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_activity, parent, false);
            return new ActivityHolder(view);
        }

        @Override
        public void onBindViewHolder(ActivityHolder holder, int position) {
            Activity activity = mActivities.get(position);
            holder.bindActivity(activity);
        }

        @Override
        public int getItemCount() {
            return mActivities.size();
        }

        public void setActivities(List<Activity> activities) {
            mActivities = activities;
        }
    }
}
