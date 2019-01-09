package com.example.hoa.accountingbook;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoa.accountingbook.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener, ChartFragment.OnFragmentInteractionListener {

    private TextView DATE;
    public static Calendar calendar = Calendar.getInstance();
    public static String dateToday;
    public DummyContent.DummyItem test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onFragmentInteraction();

        DATE = (TextView) findViewById(R.id.date);
        setCalendar();

        ImageView PREVIOUS_DATE = (ImageView) findViewById(R.id.previous);
        PREVIOUS_DATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                setCalendar();
            }
        });

        ImageView NEXT_DATE = (ImageView) findViewById(R.id.next);
        NEXT_DATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                setCalendar();
            }
        });

//        ImageView ADD = findViewById(R.id.add);
//        ADD.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, CreatingActivity.class));
//            }
//        });
    }
    public void addPage(View v){
        startActivity(new Intent(MainActivity.this, CreatingActivity.class));
    }

    public void datePicker(View v){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(year,month,day);
                setCalendar();
            }
        }, year, month, day).show();
    }

    public void setCalendar(){
        dateToday = String.valueOf(calendar.get(Calendar.YEAR))+" 年 "+String.valueOf(calendar.get(Calendar.MONTH)+1)+" 月 "+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)+" 日");
        DATE.setText(dateToday); //切換日期
        onListFragmentInteraction(test); //切換Fragment
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        //TODO: 刷新List
        ItemFragment newFragment = new ItemFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment, "LIST");
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction() {
        ChartFragment newFragment = new ChartFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.chartfrag_container, newFragment, "CHART");
        transaction.commit();
    }
}
