package edu.pitt.cs.cs1635.group4.roomhub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private List<AlertItem> alerts;
    RecyclerView rv;
    //buttons and edit text belong to the popupwindow
    PopupWindow popupWindow;
    Button alertCancelButton;
    Button alertSendButton;
    EditText alertMessageBody;
    RoomHubApplication app;

    private OnFragmentInteractionListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        app = (RoomHubApplication)getActivity().getApplication();

        rv = (RecyclerView) rootView.findViewById(R.id.alerts_list);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        //app has just opened - TODO: get data instead
        final RoomHubApplication app = (RoomHubApplication)getActivity().getApplication();
        if(app.getAlertsList() == null) {
            app.setAlertsList(new ArrayList<AlertItem>());

            //test
            app.createNotification("Gas Bill due", new Date(18, 4, 30));

        } else {
            //returning to fragment while app is still open
            //sort the alerts by data before displaying.
            app.sortAlertsList();
        }

        initializeData();

        final AlertAdapter adapter = new AlertAdapter(alerts);
        rv.setAdapter(adapter);


/*        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mItems.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mItems.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });

        mRecyclerView.addOnItemTouchListener(swipeTouchListener);
    }*/


        //TODO: get a groups name field and add it here
        //also imrove the text appearance of the group title and maybe put it
        //somewhere else.
        TextView title = (TextView) rootView.findViewById(R.id.alerts_title);
        String titleText = app.getGroupName() + ":";
        title.setText(titleText);

        //get the list window
        //final ListView lv = (ListView) view.findViewById(R.id.alerts_list);
        //set the custom adapter
        //rv.setAdapter(alerts);

        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.alert_fab);
        //fab opens new alert popup windown
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.newalert_fragment_layout, (ViewGroup)getView(), false);
                //create buttons and button listeners here
                alertCancelButton = (Button) popupView.findViewById(R.id.alert_cancel_button);
                alertSendButton = (Button) popupView.findViewById(R.id.alert_send_button);
                alertMessageBody = (EditText) popupView.findViewById(R.id.alert_row_text);

                popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                //center of window
                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                //button listeners after.
                alertCancelButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //close when done
                        popupWindow.dismiss();
                    }
                });

                alertSendButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //get default profile pic TODO: get the users' profile pic
                        //Bitmap pic = BitmapFactory.decodeResource(getResources(), R.mipmap.default_profile_image);
                        //TODO: get the users' name
                        String username = "From: Larry";
                        //Get the alert message
                        createAlert(alertMessageBody.getText().toString(), username, app.getUserImage());
                        //Provide feedback to user.
                        Toast.makeText(getContext(), "Alert Sent", Toast.LENGTH_LONG).show();
                        //close the window
                        popupWindow.dismiss();
                    }
                });
            }
        });

        //Make a test Notification


        return rootView;
    }

    //Make a new alert by calling app method where data is persistent
    public void createAlert(String messageBody, String username, Bitmap pic) {
        RoomHubApplication app = (RoomHubApplication) getActivity().getApplication();
        app.createAlert(messageBody, username, pic);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        //This can remain empty.
    }

    private void initializeData(){
        RoomHubApplication app = (RoomHubApplication) getActivity().getApplication();
        if(app.getAlertsList() == null) {
            app.setAlertsList(new ArrayList<AlertItem>());
        }
        alerts = app.getAlertsList();
    }
}
