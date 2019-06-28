package edu.pitt.cs.cs1635.group4.roomhub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 3/26/2018.
 */

public class Bill {
    String name;
    String amount;
    String dueDate;
    boolean[] paidBy;
    String [] names;
    String addedBy;


    Bill(String name, String amount,String dueDate, boolean[] paidBy, String[] names, String addedBy) {
        this.name = name;
        this.dueDate = dueDate;
        this.amount = amount;
        this.paidBy = paidBy;
        this.addedBy = addedBy;
        this.names =names;

    }

    private List<Bill> bills;

}
