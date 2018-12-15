package com.example.hoa.accountingbook;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.hoa.accountingbook.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    private TextView DATE;
    private Button PREVIOUS_DATE;
    private Button NEXT_DATE;
    public static Calendar calendar = Calendar.getInstance();
    public DummyContent.DummyItem test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DATE = (TextView) findViewById(R.id.date);
        String dateToday = String.valueOf(calendar.get(Calendar.YEAR))+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1)+"-"+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        DATE.setText(dateToday);

        PREVIOUS_DATE = (Button) findViewById(R.id.previous);
        PREVIOUS_DATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                String dateToday = String.valueOf(calendar.get(Calendar.YEAR))+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1)+"-"+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                DATE.setText(dateToday);
                onListFragmentInteraction();
            }
        });

        NEXT_DATE = (Button) findViewById(R.id.next);
        NEXT_DATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                String dateToday = String.valueOf(calendar.get(Calendar.YEAR))+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1)+"-"+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                DATE.setText(dateToday);
                onListFragmentInteraction();
            }
        });
    }

    public void datePicker(View v){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String dateTime = String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(day);
                DATE.setText(dateTime);
                calendar.set(year,month,day);
                onListFragmentInteraction();
            }
        }, year, month, day).show();
    }


    @Override
    public void onListFragmentInteraction() {
        ItemFragment itemFrag = (ItemFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        if (itemFrag != null) {
            // If article frag is available, we're in two-pane layout...
            // Call a method in the ArticleFragment to update its content

            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //transaction.detach(itemFrag).attach(itemFrag).commit();
            itemFrag.updateView();
        }
        else {
            // Otherwise, we're in the one-pane layout and must swap frags...
            // Create fragment and give it an argument for the selected article
            ItemFragment newFragment = new ItemFragment();
//            Bundle args = new Bundle();
//            args.putInt(ItemFragment.ARG_COLUMN_COUNT, item.);
//            newFragment.setArguments(args);


            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }

    }
}
