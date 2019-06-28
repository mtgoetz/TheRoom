/*

//To Complete to for swipe to dismiss alerts and notifications on the home screen
//https://www.youtube.com/watch?v=QJv4z5jyFnM



package edu.pitt.cs.cs1635.group4.roomhub;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class AlertSwipeHelper extends ItemTouchHelper.SimpleCallback {

    AlertAdapter alerts;

    public AlertSwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public AlertSwipeHelper(AlertAdapter alerts) {
        super(0, ItemTouchHelper.RIGHT);
        this.alerts = alerts;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}*/
