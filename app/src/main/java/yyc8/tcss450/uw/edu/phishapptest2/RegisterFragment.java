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

public class RegisterFragment extends Fragment {

    private OnRegisterFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        Button b = view.findViewById(R.id.register_register);
        b.setOnClickListener(this::register);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterFragmentInteractionListener) {
            mListener = (OnRegisterFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRegisterFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void register(View view) {
        if (mListener != null) {
            boolean pass = true;
            EditText email = (EditText) getActivity().findViewById(R.id.register_email);
            EditText password = (EditText) getActivity().findViewById(R.id.register_password);
            EditText retypepassword = (EditText) getActivity().findViewById(R.id.register_retypepassword);
            String emailMessage = email.getText().toString();
            String passwordMessage = password.getText().toString();
            String retypepasswordMessage = retypepassword.getText().toString();

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
            } else if (passwordMessage.length() <= 5) {
                password.setError("Password must be 6 characters or longer");
                pass = false;
            }

            if (retypepasswordMessage.isEmpty()) {
                retypepassword.setError("This field cannot be empty");
                pass = false;
            } else if (retypepasswordMessage.length() <= 5) {
                retypepassword.setError("Password must be 6 characters or longer");
                pass = false;
            }

            if (!retypepasswordMessage.equals(passwordMessage)) {
                retypepassword.setError("Passwords do not match");
                pass = false;
            }

            if (pass == true) {
                mListener.onRegisterSuccess(new Credentials.Builder(emailMessage, passwordMessage).build());
            }
            // This is the builder pattern and it's good for constructor that takes a lot of parameters.
        }
    }

    public interface OnRegisterFragmentInteractionListener {
        void onRegisterSuccess(Credentials credentials);
    }
}
