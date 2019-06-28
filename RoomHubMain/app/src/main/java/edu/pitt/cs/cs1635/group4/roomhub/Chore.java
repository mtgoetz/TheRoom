package edu.pitt.cs.cs1635.group4.roomhub;

import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jahvon on 3/25/2018.
 */

public class Chore {
    String name;
    String lastCompleteBy = " - ";
    String lastCompletedDate = " - ";

    Chore(String name) {
        this.name = name;
    }

    private List<Chore> chores;

}
