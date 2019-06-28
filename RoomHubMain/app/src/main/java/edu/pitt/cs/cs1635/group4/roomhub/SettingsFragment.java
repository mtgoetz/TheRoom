package edu.pitt.cs.cs1635.group4.roomhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class SettingsFragment extends Fragment {

    Button profile;
    Button manageGroup;
    CheckBox notifications;
    Button logout;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        profile = (Button) v.findViewById(R.id.settings_prof_button);
        manageGroup = (Button) v.findViewById(R.id.settings_manageGroups_button);
        notifications = (CheckBox) v.findViewById(R.id.settings_notif_checkbox);
        logout = (Button) v.findViewById(R.id.settings_logout_button);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.settings_root, new ProfileUpdate())
                        .addToBackStack(null) // enables back key
                        .commit();
            }
        });

        manageGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.settings_root, new ManageGroupFragment())
                        .addToBackStack(null) // enables back key
                        .commit();
            }
        });

        notifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //TODO: toggle notification on/off for user.
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(getContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(getContext(), MainActivity.class));
                            }
                        });
            }
        });


        return v;
    }

}
