package edu.pitt.cs.cs1635.group4.roomhub;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mthom on 3/21/2018.
 */



//Recommend: add a profile pic (to right) and name field (small to top of message body)
    //to do this add imageView to alert_row and textView


public class AlertItem implements Comparable<AlertItem>{
    private String alertText;
    private Bitmap profilePic;
    private String heading;
    private Date date;
    //1 = alert, 2 = notification
    private int type;
    //TODO make a default image & default name for demo
    private static Image defaultImage;

/*    public AlertItem(){
        //TODO
        //reflect to full constr.
        alertText="";
        heading = ""
    }

    public AlertItem(String messageBody) {
        //TODO
        //reflect to full constr
        alertText = messageBody;
    }*/

    public AlertItem(String messageBody, String heading, Bitmap profileImage, Boolean notification, Date date) {
        alertText = messageBody;
        profilePic = profileImage;
        this.heading = heading;
        if(date == null) this.date = Calendar.getInstance().getTime();
        else this.date=date;
        if(notification) type = 2;
        else type = 1;
    }

    public void setText(String text){
        alertText = text;
    }

    public String getText(){
        return alertText;
    }

    public Bitmap getDrawable() {
        return profilePic;
    }

    public String getHeading() {
        return heading;
    }

    public Date getDate()
    {
        return date;
    }

    @Override
    public int compareTo(@NonNull AlertItem o) {
        if(this.date.after(o.getDate())){
            return -1;
        } else {
            return 1;
        }
    }

    public int getType() {
        return type;
    }
}
