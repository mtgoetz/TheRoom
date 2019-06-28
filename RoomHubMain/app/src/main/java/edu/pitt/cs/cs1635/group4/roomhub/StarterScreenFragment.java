package edu.pitt.cs.cs1635.group4.roomhub;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This fragment displays a create group option, join a group, and about
 */
public class StarterScreenFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "userEmail";

    // TODO: Rename and change types of parameters
    private String userEmail;

    private OnFragmentInteractionListener mListener;

    //this fragments views
    private Button createNewGroupButton;
    private EditText inviteCodeField;
    private Button joinButton;
    private Button aboutButton;

    //create a new group popup window
    private PopupWindow popupWindow;
    private Button newGroupGenInviteCodeButton;
    private TextView newGroupInviteCodeField;
    private Button newGroupSaveButton;
    private Button newGroupCancelButton;
    private EditText newGroupNameField;
    private EditText newGroupAddressField;


    public StarterScreenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment StarterScreenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StarterScreenFragment newInstance(String param1) {
        StarterScreenFragment fragment = new StarterScreenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userEmail = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.starter_fragment, container, false);

        //buttons and what not
/*        private Button createNewGroupButton;
        private EditText inviteCodeField;
        private Button joinButton;
        private Button aboutButton;*/

        createNewGroupButton = (Button) rootView.findViewById(R.id.starter_newgroup_button);
        inviteCodeField = (EditText) rootView.findViewById(R.id.starter_code_edittext);
        joinButton = (Button) rootView.findViewById(R.id.starter_join_button);
        aboutButton = (Button) rootView.findViewById(R.id.starter_about_button);

        createNewGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: start new group fragment or popup.





                LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = layoutInflater.inflate(R.layout.newgroup_popup, (ViewGroup)getView(), false);

                //create buttons and button listeners here
                newGroupNameField = (EditText) popupView.findViewById(R.id.newgroup_name_text);
                newGroupAddressField = (EditText) popupView.findViewById(R.id.newgroup_address_text);
                newGroupGenInviteCodeButton = (Button) popupView.findViewById(R.id.newgroup_geninvite_button);
                newGroupInviteCodeField = (TextView) popupView.findViewById(R.id.newgroup_invitecode_text);
                newGroupSaveButton = (Button) popupView.findViewById(R.id.newgroup_create_button);
                newGroupCancelButton = (Button) popupView.findViewById(R.id.newgroup_cancel_button);

                popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                //center of window
                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                //button listeners after.
                newGroupGenInviteCodeButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //TODO: generate invite code, add to db for use, and display the code to edittext
                    }
                });

                newGroupSaveButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //TODO: create a new group, go to homescreen initialized with data
                    }
                });

                newGroupCancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: get invite code from edittext and find
                //if not error message
                //if so get data and go to home screen
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: display about information
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    //??????????????????????????????????????????
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
