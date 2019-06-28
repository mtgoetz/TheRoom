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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Adib on 13-Apr-17.
 */

public class ChoresFragment extends Fragment implements ChoreAdapter.ChoreNotify {

    public static ChoresFragment newInstance() {
        return new ChoresFragment();
    }

    private List<Chore> chores;
    protected RecyclerView rv;

    PopupWindow popupWindow;
    Button popupCancelButton;
    Button popupSendButton;
    EditText popupMessageBody;
    TextView scoreText;
    TextView userText;
    RoomHubApplication app;
    String username;
    String[] groupMembers;
    ChoreAdapter adapter;


    @Override
    public void plusOne(Chore chore) {
        Toast.makeText(this.getContext(), "+1", Toast.LENGTH_LONG).show();
        int prev = Integer.parseInt(scoreText.getText().toString());
        String newText = Integer.toString(++prev);
        scoreText.setText(newText);
        app.addScore();
        Date current = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yy");
        String date = format.format(current);
        chore.lastCompletedDate = date;
        chore.lastCompleteBy=app.getUserName();
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {;
        final View rootView = inflater.inflate(R.layout.fragment_chores, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.chore_rv);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        app = (RoomHubApplication)getActivity().getApplication();

        initializeData();


        if(app.getChoresList() != null) {
            adapter = new ChoreAdapter(app.getChoresList(), app, this);
        } else {
            adapter = new ChoreAdapter(chores, app, this);
        }
        rv.setAdapter(adapter);

        userText = (TextView) rootView.findViewById(R.id.toolbar_user_text);
        userText.setText(username);

        scoreText = (TextView)rootView.findViewById(R.id.toolbar_score_text);
        //TODO: set score here

        String s = Integer.toString(app.getUserScore());
        scoreText.setText(s);



        FloatingActionButton addButton = (FloatingActionButton) rootView.findViewById(R.id.add_chore_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.fragment_newchore, (ViewGroup)getView(), false);
                //create buttons and button listeners here
                popupCancelButton = (Button) popupView.findViewById(R.id.chore_cancel_button);
                popupSendButton = (Button) popupView.findViewById(R.id.chore_send_button);
                popupMessageBody = (EditText) popupView.findViewById(R.id.chore_row_text);


                popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                //center of window
                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                //button listeners after.
                popupCancelButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //close when done
                        popupWindow.dismiss();
                    }
                });

                popupSendButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //String username = "Larry";
                        //Get the alert message
                        createChore(popupMessageBody.getText().toString());
                        //Provide feedback to user.
                        Toast.makeText(getContext(), "Chore added.", Toast.LENGTH_SHORT).show();
                        //redraw the fragment
                        //rootView.invalidate();
                        adapter.notifyDataSetChanged();
                        //close the window
                        popupWindow.dismiss();
                    }
                });
            }
        });

/*        if(chores.size()==0)
        {
            createChore("Clean the Bathroom",username,"4/1/2018",groupMembers[1]);
        }*/
        return rootView;
    }

    public void createChore(String messageBody) {
        Chore newChore = new Chore(messageBody);
        RoomHubApplication app = (RoomHubApplication) getActivity().getApplication();
        app.addChore(newChore);


    }

    private void initializeData(){
        RoomHubApplication app = (RoomHubApplication) getActivity().getApplication();
        if(app.getChoresList() == null) {
            app.setChoresList(new ArrayList<Chore>());
        }
        chores = app.getChoresList();
        username = app.getUserName();
        groupMembers = app.getNames();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
