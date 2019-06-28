package edu.pitt.cs.cs1635.group4.roomhub;

import android.app.Fragment;

/**
 * Created by mthom on 3/22/2018.
 */



//this class might not be used
public class NewAlert extends Fragment {

    public interface OnAlertCreatedListener {
        public void createAlert(String messageBody);
    }

    OnAlertCreatedListener alertListener;

    //we somehow need this to be the popup window??
}
