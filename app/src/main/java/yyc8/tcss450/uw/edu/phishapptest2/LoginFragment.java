package yyc8.tcss450.uw.edu.phishapptest2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import yyc8.tcss450.uw.edu.phishapptest2.model.Credentials;

public class LoginFragment extends Fragment {

    private OnLoginFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments() != null) {
            Credentials creds = (Credentials) getArguments().getSerializable(getString(R.string.credential_key));
            EditText emailMessage = getActivity().findViewById(R.id.login_email);
            EditText passwordMessage = getActivity().findViewById(R.id.login_password);
            emailMessage.setText(creds.getEmail());
            passwordMessage.setText(creds.getPassword());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Button b = (Button) v.findViewById(R.id.login_signin);
        b.setOnClickListener(this::login);

        b = (Button) v.findViewById(R.id.login_register);
        b.setOnClickListener(this::register);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginFragmentInteractionListener) {
            mListener = (OnLoginFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void login(View view) {
        if (mListener != null) {
            boolean pass = true;
            EditText email = (EditText) getActivity().findViewById(R.id.login_email);
            EditText password = (EditText) getActivity().findViewById(R.id.login_password);
            String emailMessage = email.getText().toString();
            String passwordMessage = password.getText().toString();
            if (!emailMessage.contains("@")) {
                email.setError("Must enter a valid email address");
                pass = false;
            }
            if (emailMessage.isEmpty()) {
                email.setError("This field cannot be empty");
                pass = false;
            }
            if (passwordMessage.isEmpty()) {
                password.setError("This field cannot be empty");
                pass = false;
            }
            if (pass == true){
                mListener.onLoginSuccess(new Credentials.Builder(emailMessage, passwordMessage).build(), null);
            }
            // This is the builder pattern and it's good for constructor that takes a lot of parameters.
        }
    }

    public void register(View view) {
        if (mListener != null) {
            mListener.onRegisterClicked();
        }
    }


    public interface OnLoginFragmentInteractionListener {
        void onLoginSuccess(Credentials credentials, String jwt);
        void onRegisterClicked();
    }
}
