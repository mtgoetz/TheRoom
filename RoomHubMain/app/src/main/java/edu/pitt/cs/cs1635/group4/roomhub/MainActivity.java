package edu.pitt.cs.cs1635.group4.roomhub;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends BottomBarHolderActivity implements HomeFragment.OnFragmentInteractionListener{

    private static final int RC_SIGN_IN = 1635;
    RoomHubApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (RoomHubApplication)getApplication();

        //TODO: login or register and return a users email to find group?

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .build(),
                RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                app.userData = user;

                //then continue to initialize app
                NavigationPage homePage = new NavigationPage("Home", ContextCompat.getDrawable(this, R.drawable.ic_home_black_24dp), HomeFragment.newInstance());
                NavigationPage billsPage = new NavigationPage("Bills", ContextCompat.getDrawable(this, R.drawable.ic_attach_money_black_24dp), BillsFragment.newInstance());

                NavigationPage choresPage = new NavigationPage("Chores", ContextCompat.getDrawable(this, R.drawable.ic_assignment_black_24dp), ChoresFragment.newInstance());
                NavigationPage settingsPage = new NavigationPage("Settings", ContextCompat.getDrawable(this, R.drawable.ic_settings_black_24dp), SettingsFragment.newInstance());

                List<NavigationPage> navigationPages = new ArrayList<>();
                navigationPages.add(homePage);
                navigationPages.add(billsPage);
                navigationPages.add(choresPage);
                navigationPages.add(settingsPage);

                super.setupBottomBarHolderActivity(navigationPages);
                // ...
            } else {
                // Sign in failed, check response for error code
                // ...
            }
        }
    }

    public String getRoomID(){
        return "";
    }


}