package com.example.hoa.accountingbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class CreatingActivity extends AppCompatActivity {

    private TextView TEXT_DATE;
    private TextView TEXT_CATEGORY;
    private TextView TEXT_ITEM;
    private TextView TEXT_COST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating);

        TEXT_DATE = (TextView) findViewById(R.id.create_date);
        TEXT_DATE.setText(MainActivity.dateToday);
        setButtonClick();
        addClick();

    }

    public void setButtonClick(){
        TEXT_CATEGORY = (TextView) findViewById(R.id.create_category);
        final List<Integer> ButtonList = Arrays.asList(R.id.food, R.id.traffic, R.id.clothes, R.id.life, R.id.entertainment, R.id.ccc, R.id.medicine, R.id.others);

        View.OnClickListener chooseContent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.food:
                        TEXT_CATEGORY.setText("食物");
                        break;
                    case R.id.traffic:
                        TEXT_CATEGORY.setText("交通");
                        break;
                    case R.id.clothes:
                        TEXT_CATEGORY.setText("服飾");
                        break;
                    case R.id.life:
                        TEXT_CATEGORY.setText("生活");
                        break;
                    case R.id.entertainment:
                        TEXT_CATEGORY.setText("育樂");
                        break;
                    case R.id.ccc:
                        TEXT_CATEGORY.setText("3C");
                        break;
                    case R.id.medicine:
                        TEXT_CATEGORY.setText("醫藥");
                        break;
                    case R.id.others:
                        TEXT_CATEGORY.setText("其他");
                        break;
                }
            }
        };
        for (int id : ButtonList) {
            findViewById(id).setOnClickListener(chooseContent);
        }
    }

    public void addClick(){
        ImageView btn_ADD = (ImageView) findViewById(R.id.add);
        btn_ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date1 = String.valueOf(TEXT_DATE.getText());
                String category1 = String.valueOf(TEXT_CATEGORY.getText());
                String item1 = String.valueOf(TEXT_ITEM.getText());
                String cost1 = String.valueOf(TEXT_COST.getText());
                BusinessRules.Add(date1, category1, item1, cost1);
            }
        });
    }
}
