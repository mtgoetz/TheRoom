package edu.pitt.cs.cs1635.group4.roomhub;

import android.animation.ValueAnimator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 3/26/2018.
 */

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder>{

        List<Bill> bills;
        String currentUser;
        BillAdapter(List<Bill> bills,String username){
            this.bills = bills;
            currentUser = username;
        }

        @Override
        public int getItemCount() {return bills.size();}

        @Override
        public BillViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bill_item, viewGroup, false);
            BillViewHolder pvh = new BillViewHolder(v, bills.get(i).names.length);
            return pvh;
        }

        @Override
        public void onBindViewHolder(final BillViewHolder billViewHolder, final int i) {
            billViewHolder.billName.setText(bills.get(i).name);
            billViewHolder.billAmount.setText(bills.get(i).amount);
            billViewHolder.billDueDate.setText(bills.get(i).dueDate);
            billViewHolder.payButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   for(int j=0;j<bills.get(i).names.length;j++){
                       if(currentUser.equals(bills.get(i).names[j]))
                       {
                           bills.get(i).paidBy[j] = true;
                           TextView t=(TextView)billViewHolder.paid.getChildAt(j);
                           t.setText("Paid");
                       }
                   }
                }
            });
            for(int j=0;j<bills.get(i).names.length;j++)
            {
                TextView t = (TextView) billViewHolder.names.getChildAt(j);
                TextView t2 = (TextView) billViewHolder.paid.getChildAt(j);
                t.setText(bills.get(i).names[j]);
                if(bills.get(i).paidBy[j]) {
                    t2.setText("Paid");
                }else
                {
                    t2.setText("Not Paid");
                }
            }
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public static class BillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            CardView cv;
            TextView billName;
            TextView billAmount;
            TextView billDueDate;
            LinearLayout names;
            LinearLayout paid;
            RelativeLayout extendContent;
            Button payButton;
            private int standardHeight = 0;
            private boolean isExpanded = false;


            BillViewHolder(View itemView, int len) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.billev);
                billName = (TextView)itemView.findViewById(R.id.bill_name);
                billAmount = (TextView)itemView.findViewById(R.id.bill_amount);
                billDueDate = (TextView) itemView.findViewById(R.id.bill_due_date);
                names = (LinearLayout) itemView.findViewById(R.id.groupNameLayout);
                paid = (LinearLayout) itemView.findViewById(R.id.paidByLayout);
                extendContent=(RelativeLayout) itemView.findViewById(R.id.ExtendContent);
                payButton =(Button) itemView.findViewById(R.id.PayButton);
                for(int i=0;i<len;i++)
                {

                    TextView tv = new TextView(itemView.getContext());
                    TextView tv2 = new TextView(itemView.getContext());
                    names.addView(tv);
                    paid.addView(tv2);
                }
                extendContent.setVisibility(View.GONE);


                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(final View v){

                if(standardHeight ==0)
                {
                    standardHeight = v.getHeight();
                }
                ValueAnimator valueAnimator;
                if (!isExpanded) {


                    isExpanded = true;
                    valueAnimator = ValueAnimator.ofInt(standardHeight, standardHeight + (int) (standardHeight * 2.0)); // These values in this method can be changed to expand however much you like
                    extendContent.setVisibility(View.VISIBLE);
                } else {
                    isExpanded = false;
                    valueAnimator = ValueAnimator.ofInt(standardHeight + (int) (standardHeight * 2.0), standardHeight);

                    Animation a = new AlphaAnimation(1.00f, 1.00f);

                    a.setDuration(200);
                    // Set a listener to the animation and configure onAnimationEnd
                    a.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                           extendContent.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    // Set the animation on the custom view
                    cv.startAnimation(a);
                }
                valueAnimator.setDuration(200);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer value = (Integer) animation.getAnimatedValue();
                        v.getLayoutParams().height = value.intValue();
                        v.requestLayout();
                    }
                });
                valueAnimator.start();
            }

        }
}

