package edu.pitt.cs.cs1635.group4.roomhub;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jahvon on 3/25/2018.
 */

public class ChoreAdapter extends RecyclerView.Adapter<ChoreAdapter.ChoreViewHolder> {

    List<Chore> chores;
    RoomHubApplication app;
    Button completeButton;
    ChoreNotify parent;
    SimpleDateFormat sdf;

    ChoreAdapter(List<Chore> chores, RoomHubApplication app, ChoreNotify parent){
        this.chores = chores;
        this.app = app;
        this.parent = parent;
    }

    public interface ChoreNotify {
        public void plusOne(Chore chore);
    }

    @Override
    public int getItemCount() {
        return chores.size();
    }

    @Override
    public ChoreViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chore_item, viewGroup, false);
        ChoreViewHolder pvh = new ChoreViewHolder(v);

        completeButton = (Button) v.findViewById(R.id.complete_button);
        //TODO: check if completeButton is for this user if not set text Initials (score)
        //TODO: check if a chore is completed for the week if so make green.
            //get date completed of the chore
            //compare to current date and time
            Date currentTime = Calendar.getInstance().getTime();

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeButton.setBackgroundColor(Color.parseColor("#4CAF50"));
                //TODO: update db with completed time.

                CardView cv = (CardView)completeButton.getParent().getParent();

                int position = (int)cv.getTag();
                parent.plusOne(chores.get(position));
            }
        });




        return pvh;
    }

    @Override
    public void onBindViewHolder(ChoreViewHolder choreViewHolder, int i) {
        choreViewHolder.choreName.setText(chores.get(i).name);
        choreViewHolder.choreCBy.setText(chores.get(i).lastCompleteBy);
        choreViewHolder.choreCDate.setText(chores.get(i).lastCompletedDate);



        //TODO: check if completeButton is for this user if not set text Initials (score)
        //TODO: check if a chore is completed for the week if so make green.
        //get date completed of the chore
        //compare to current date and time
        Date currentTime = Calendar.getInstance().getTime();



        //choreViewHolder.completed = chores.get(i).completedButton;


        //TODO: if chore has been done in last 24hrs make green.
        //completeButton.setText("complete");



        choreViewHolder.cv.setTag(i);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ChoreViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView choreName;
        TextView choreCBy;
        TextView choreCDate;
        Button completed;

        ChoreViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.chorev);
            choreName = (TextView)itemView.findViewById(R.id.chore_name);
            choreCBy = (TextView)itemView.findViewById(R.id.chore_cby);
            choreCDate = (TextView)itemView.findViewById(R.id.chore_cdate);
            completed = (Button) itemView.findViewById(R.id.complete_button);
        }
    }

}