package edu.pitt.cs.cs1635.group4.roomhub;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;


public class RoomHubApplication extends Application {
    private ArrayList<AlertItem> alerts;
    private ArrayList<Chore> chores;
    private ArrayList<Bill> bills;
    private String groupName;
    private String groupAddress;
    private String userName;
    private String userEmail;
    private String userPhone;
    private Bitmap userImage;
    private int userScore;
    public FirebaseUser userData;
    private String[] names = {"Larry", "Ned", "Steven"};
    private static RoomHubApplication singleton;

    public static RoomHubApplication getInstance() {
        return singleton;
    }

    public String getUserName() {
        if(userName == null) return "Larry";
        return userName;
    }

    public String getUserEmail() {
        if(userEmail == null) return "user@theCIA.gov";
        return userEmail;
    }

    public String getUserPhone() {
        if(userPhone == null) return "555-5555";
        return userPhone;
    }

    public Bitmap getUserImage() {
        if(userImage == null) {
            return BitmapFactory.decodeResource(getResources(), R.mipmap.default_profile_image);
        }
        return userImage;
    }

    public int getUserScore() {
        return userScore;
    }

    public void addScore(){
        userScore++;
    }

    public ArrayList<AlertItem> getAlertsList() {
        return alerts;
    }
    public ArrayList<Chore> getChoresList() {
        return chores;
    }
    public ArrayList<Bill> getBillsList(){return bills;}
    public String getGroupName() {
        if(groupName == null) groupName = "221B Baker's Street";
        return groupName;
    }
    public String[] getNames() {return names;}

    public String getGroupAddress() {
        return groupAddress;
    }

    public void addAlert(AlertItem item) {
        alerts.add(item);
    }

    public void addChore(Chore item) {
        chores.add(item);
    }
    public void addBill(Bill item){bills.add(item);}

    public void doChore(Chore chore) {
        chore.lastCompleteBy = userName;

        Date current = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yy");
        String date = format.format(current);
        chore.lastCompletedDate = date;
    }

    public void setAlertsList(ArrayList<AlertItem> alertsList) {
        alerts = alertsList;
    }
    public void setChoresList(ArrayList<Chore> choresList) {
        chores = choresList;
    }
    public void setBillsList(ArrayList<Bill> billsList){bills = billsList;}
    public void setUserImage(Bitmap image) {
        userImage = image;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setGroupName(String name) {
        if(name == null) name = "Your RoomHub Group";
        groupName = name;
    }

    public void removeAlert(AlertItem item)
    {
        alerts.remove(item);
    }

    public void sortAlertsList() {
        Collections.sort(alerts);
    }

/*    public void createNotification(String message, Date date) {
        Bitmap notifIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.notification_icon);
        if(date == null) if(date == null) date = Calendar.getInstance().getTime();
        AlertItem notif = new AlertItem(message, "", notifIcon, true, date);
        addAlert(notif);
    }*/

    public void createNotification(String message, Date date) {
        Bitmap notifIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.notification_icon);
        AlertItem notif = new AlertItem(message, "", notifIcon, true, date);
        addAlert(notif);
    }

    public void createAlert(String messageBody, String username, Bitmap pic) {
        AlertItem newAlert = new AlertItem(messageBody,username, pic, false, null);
        addAlert(newAlert);
    }

}
