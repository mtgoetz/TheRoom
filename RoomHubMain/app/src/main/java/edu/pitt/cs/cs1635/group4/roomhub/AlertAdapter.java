package edu.pitt.cs.cs1635.group4.roomhub;

//import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder> {

    //private LayoutInflater layoutInflater;
    private List<AlertItem> alertItems;
    //private RoomHubApplication app;

    public AlertAdapter(List<AlertItem> alerts) {
        this.alertItems=alerts;
    }

    @Override
    public int getItemCount() {
        return alertItems.size();
    }

    @Override
    public AlertViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_row, parent, false);
        AlertViewHolder pvh = new AlertViewHolder(v);
        //app = (RoomHubApplication)parent.getContext().getApplication();
        return pvh;
    }

    @Override
    public void onBindViewHolder(AlertViewHolder holder, int position) {

        AlertItem current = alertItems.get(position);

        holder.alertText.setText(current.getText());
        holder.profileImage.setImageBitmap(current.getDrawable());
        holder.heading.setText(current.getHeading());
        //holder.date.setText(current.getDate().toString());

        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yy");
        holder.date.setText(format.format(current.getDate()));


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

/*    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
*//*        int type = getItemViewType(position);
        if(convertView == null) {
            if(type == 1) {
                convertView = layoutInflater.inflate(R.layout.alert_row, null);
                holder = new ViewHolder();
                holder.alertText = (TextView) convertView.findViewById(R.id.alert_row_text);
                holder.profileImage = (ImageView) convertView.findViewById(R.id.alert_row_image);
                holder.heading = (TextView) convertView.findViewById(R.id.alert_row_header);
                holder.date = (TextView) convertView.findViewById(R.id.alert_row_date);
            } else {
                convertView = layoutInflater.inflate(R.layout.alert_row, null);
                holder = new ViewHolder();
                holder.alertText = (TextView) convertView.findViewById(R.id.notif_text_view);
                holder.profileImage = (ImageView) convertView.findViewById(R.id.notif_icon_view);
                holder.date = (TextView) convertView.findViewById(R.id.notif_date_view);
                holder.heading = (TextView) convertView.findViewById(R.id.alert_row_header);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }*//*
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.alert_row, null);
            holder = new ViewHolder();
            holder.alertText = (TextView) convertView.findViewById(R.id.alert_row_text);
            holder.profileImage = (ImageView) convertView.findViewById(R.id.alert_row_image);
            holder.heading = (TextView) convertView.findViewById(R.id.alert_row_header);
            holder.date = (TextView) convertView.findViewById(R.id.alert_row_date);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(holder != null) {
            //set the alert item fields here
            //Note: because using position, cannot sort while still in HomeFragment without recreating
            //the view.
*//*            if(type == 1) {
                holder.alertText.setText(listItems.get(position).getText());
                holder.profileImage.setImageBitmap(listItems.get(position).getDrawable());
                holder.heading.setText(listItems.get(position).getHeading());
                SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yy");
                holder.date.setText(format.format(listItems.get(position).getDate()));
            } else {
                holder.alertText.setText(listItems.get(position).getText());
                holder.profileImage.setImageBitmap(listItems.get(position).getDrawable());
                SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yy");
                holder.date.setText(format.format(listItems.get(position).getDate()));
                holder.heading.setText("");
            }*//*
            holder.alertText.setText(listItems.get(position).getText());
            holder.profileImage.setImageBitmap(listItems.get(position).getDrawable());
            holder.heading.setText(listItems.get(position).getHeading());
            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yy");
            holder.date.setText(format.format(listItems.get(position).getDate()));
        }
        return convertView;
    }*/

    static class AlertViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView alertText;
        ImageView profileImage;
        TextView heading;
        TextView date;

        public AlertViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.alert_row);
            alertText = (TextView)itemView.findViewById(R.id.alert_row_text);
            heading = (TextView)itemView.findViewById(R.id.alert_row_header);
            date = (TextView) itemView.findViewById(R.id.alert_row_date);
            profileImage = (ImageView)itemView.findViewById(R.id.alert_row_image);
        }
    }

/*    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return (listItems.get(position).getType());
    }*/
}
