package edu.pitt.cs.cs1635.group4.roomhub;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class ManageGroupFragment extends Fragment {

    RoomHubApplication app;
    EditText nameField;
    EditText addressField;
    EditText invitePhone;

    Button inviteButton;
    Button leaveButton;
    Button submitButton;
    Button cancelButton;

    View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (RoomHubApplication)getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.manage_groups_fragment, container, false);

        //app = (RoomHubApplication)getActivity().getApplication();

        nameField = (EditText) rootView.findViewById(R.id.manage_name_edittext);
        addressField = (EditText) rootView.findViewById(R.id.manage_address_edittext);
        invitePhone = (EditText) rootView.findViewById(R.id.manage_invite_edittext);
        inviteButton = (Button) rootView.findViewById(R.id.manage_invite_button);
        leaveButton = (Button) rootView.findViewById(R.id.manage_leavegroup_button);
        submitButton = (Button) rootView.findViewById(R.id.manage_submit_button);
        cancelButton = (Button) rootView.findViewById(R.id.manage_cancel_button);

        nameField.setHint(app.getGroupName());
        addressField.setHint(app.getGroupAddress());

        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //in db have an invites table (groupID, phoneNumber, inviteCode)
                Random r = new Random();
                int invCode = r.nextInt(90000) + 1000;
                //TODO: Store this code in db

                String phoneNum = invitePhone.getText().toString();
                String message = Integer.toString(invCode);

                //Send the invite code by text (as of now from this phone)
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phoneNum, null, message, null, null);
            }
        });

        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: remove user from group and route to join/create new group.
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: get fields and commit changes to db.  Do not update empty fields.
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
