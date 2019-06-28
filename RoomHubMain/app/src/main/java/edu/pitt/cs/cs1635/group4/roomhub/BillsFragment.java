package edu.pitt.cs.cs1635.group4.roomhub;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BillsFragment extends Fragment {
    public static BillsFragment newInstance() {
        return new BillsFragment();
    }

    private List<Bill> bills;
    protected RecyclerView rv;
    String[] gn;
    PopupWindow popupWindow;
    Button popupCancelButton;
    Button popupSendButton;
    EditText popupBillName;
    EditText popupBillAmount;
    EditText popupBillDueDate;
    String username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bills, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.bill_rv);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        initializeData();



        final BillAdapter adapter = new BillAdapter(bills,username);

        rv.setAdapter(adapter);

        FloatingActionButton addButton = (FloatingActionButton) rootView.findViewById(R.id.add_bill_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.fragment_newbill, (ViewGroup)getView(), false);
                //create buttons and button listeners here
                popupCancelButton = (Button) popupView.findViewById(R.id.bill_cancel_button);
                popupSendButton = (Button) popupView.findViewById(R.id.bill_add_button);
                popupBillName = (EditText) popupView.findViewById(R.id.bill_name_text);
                popupBillAmount = (EditText) popupView.findViewById(R.id.bill_amount_text);
                popupBillDueDate = (EditText) popupView.findViewById(R.id.bill_due_date_text);
                popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                //Bill date picker
                final Calendar myCalendar = Calendar.getInstance();
                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel(myCalendar);
                    }

                };

                popupBillDueDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(getContext(), date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

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
                       // String username = "User";
                        //Get the alert message
                        createBill(popupBillName.getText().toString(), popupBillAmount.getText().toString(), popupBillDueDate.getText().toString(),username);
                        //Provide feedback to user.
                        Toast.makeText(getContext(), "Bill added.", Toast.LENGTH_LONG).show();

                        adapter.notifyDataSetChanged();
                        //close the window
                        popupWindow.dismiss();
                    }
                });
            }
        });

        if(bills.size()==0) {
            createBill("Gas", "50.00", "4/20/2018", username);
        }
        return rootView;
    }

    public void createBill(String billName, String billAmount,String billDueDate, String username) {
        Bill newBill = new Bill(billName, billAmount, billDueDate,new boolean[gn.length],gn,username);
        RoomHubApplication app = (RoomHubApplication) getActivity().getApplication();
        app.addBill(newBill);
    }

    private void updateLabel(Calendar myCalendar) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        popupBillDueDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void initializeData(){
        RoomHubApplication app = (RoomHubApplication) getActivity().getApplication();
        if(app.getBillsList() == null) {
            app.setBillsList(new ArrayList<Bill>());
        }

        bills = app.getBillsList();
        username = app.getUserName();
        gn = app.getNames();
    }
}
