package usc.ict311.adamlobegeier.myactivities.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import usc.ict311.adamlobegeier.myactivities.R;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class UserFragment extends Fragment {
    private User mUser;

    private TextView mUID;
    private Spinner mGender;
    private EditText mWholeName;
    private EditText mEmail;
    private EditText mComments;

    @Override
    public void onPause() {
        super.onPause();
        UserLab.get(getActivity()).updateUser(mUser, mUser.getId());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment, container, false);

        mUser = UserLab.get(getActivity()).getUser();

        mWholeName = (EditText) view.findViewById(R.id.wholename);
        mWholeName.setText(mUser.getWholeName());
        mWholeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUser.setWholeName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mUID = (TextView) view.findViewById(R.id.user_id);
        mUID.setText("UID: " + mUser.getId().toString());

        mGender = (Spinner) view.findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGender.setAdapter(adapter);
        mGender.setSelection(mUser.getGender());
        mGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mUser.setGender(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mEmail = (EditText) view.findViewById(R.id.email_edittext);
        mEmail.setText(mUser.getEmail());
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUser.setEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mComments = (EditText) view.findViewById(R.id.user_comments);
        mComments.setText(mUser.getComments());
        mComments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUser.setComments(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
}
