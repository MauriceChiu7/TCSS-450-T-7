package yyc8.tcss450.uw.edu.phishapptest2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yyc8.tcss450.uw.edu.phishapptest2.model.Credentials;


public class SuccessFragment extends Fragment {

    public SuccessFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments() != null) {
            Credentials creds = (Credentials) getArguments().getSerializable(getString(R.string.credential_key));
            TextView tv = getActivity().findViewById(R.id.display_email);
            tv.setText(creds.getEmail());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_success, container, false);
        return v;
    }
}
