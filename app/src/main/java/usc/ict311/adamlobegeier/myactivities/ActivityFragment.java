package usc.ict311.adamlobegeier.myactivities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.util.UUID;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class ActivityFragment extends Fragment {
    private static final String ARG_ACTIVITY_ID = "activity_id";
    private static final int REQUEST_PHOTO= 1;

    private Activity mActivity;

    private EditText mTitleField;
    private File mPhotoFile;
    private Button mDateButton;
    private EditText mPlace;
    private EditText mDuration;
    private Spinner mType;
    private EditText mComment;
    private Button mDelete;
    private Button mSave;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;

    public static ActivityFragment newInstance(UUID activityId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ACTIVITY_ID, activityId);

        ActivityFragment fragment = new ActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID activityId = (UUID) getArguments().getSerializable(ARG_ACTIVITY_ID);
        mActivity = ActivityLab.get(getActivity()).getActivity(activityId);
        mPhotoFile = ActivityLab.get(getActivity()).getPhotoFile(mActivity);
    }

    @Override
    public void onPause() {
        super.onPause();

        ActivityLab.get(getActivity()).updateActivity(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle onSavedInstancedState) {
        View v = inflater.inflate(R.layout.fragment_activity, container, false);

        mTitleField = (EditText) v.findViewById(R.id.activity_title);
        mTitleField.setText(mActivity.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mActivity.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.activity_date);
        mDateButton.setText(mActivity.getDate().toString());
        mDateButton.setEnabled(false);

        mComment = (EditText) v.findViewById(R.id.activity_comment);
        mComment.setText(mActivity.getComments());
        mComment.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mActivity.setComments(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDuration = (EditText) v.findViewById(R.id.duration);
        mDuration.setText(mActivity.getDuration());
        mDuration.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mActivity.setDuration(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mType = (Spinner) v.findViewById(R.id.activity_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.activity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mType.setAdapter(adapter);
        mType.setSelection(mActivity.getType());
        mType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mActivity.setType(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mDelete = (Button) v.findViewById(R.id.delete_button);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityLab.get(getActivity()).deleteActivity(mActivity.getId());
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            }
        });

        mSave = (Button) v.findViewById(R.id.save_button);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            }
        });

        PackageManager packageManager = getActivity().getPackageManager();

        mPhotoButton = (ImageButton) v.findViewById(R.id.activity_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;

        mPhotoButton.setEnabled(canTakePhoto);

        if (canTakePhoto) {
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);

            }
        });

        mPhotoView = (ImageView) v.findViewById(R.id.activity_photo);
        updatePhotoView();

        mPlace = (EditText) v.findViewById(R.id.activity_place);
        mPlace.setKeyListener(null);
        mPlace.setText(mActivity.getPlace());

        return v;
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PHOTO) {
            updatePhotoView();
        }
    }

}
