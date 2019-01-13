package com.example.hoa.accountingbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoa.accountingbook.dummy.DummyContent;

import java.util.Arrays;
import java.util.List;

public class CreatingActivity extends AppCompatActivity {

    private TextView TEXT_DATE;
    private TextView TEXT_CATEGORY;
    private TextView TEXT_ITEM;
    private TextView TEXT_COST;

    private ImageView BTN_ADD;
    private ImageView BTN_DELETE;
    private ImageView BTN_UPDATE;
    private DummyContent.DummyItem ITEM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating);

        TEXT_DATE = (TextView) findViewById(R.id.create_date);
        TEXT_DATE.setText(MainActivity.dateToday);
        TEXT_CATEGORY = (TextView) findViewById(R.id.create_category);
        TEXT_ITEM = (TextView) findViewById(R.id.create_item);
        TEXT_COST = (TextView) findViewById(R.id.create_cost);

        BTN_ADD = (ImageView) findViewById(R.id.add);
        BTN_DELETE = (ImageView) findViewById(R.id.delete);
        BTN_UPDATE = (ImageView) findViewById(R.id.update);

        getnewIntent();
        setButtonClick();
        addClick();
        deleteClick();
        updateClick();
    }

    public void setButtonClick(){
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
                        TEXT_CATEGORY.setText("３Ｃ");
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
        BTN_ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date1 = String.valueOf(TEXT_DATE.getText());
                String category1 = String.valueOf(TEXT_CATEGORY.getText());
                String item1 = String.valueOf(TEXT_ITEM.getText());
                double cost1 = Double.parseDouble(TEXT_COST.getText().toString());

                DummyContent.DummyItem newItem = new DummyContent.DummyItem(0, date1, category1, item1, cost1);
                DataTable.Insert(newItem);

                startActivity(new Intent(CreatingActivity.this, MainActivity.class));
            }
        });
    }

    public void deleteClick(){
        BTN_DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id_selected = ITEM.id;
                DataTable.Delete(id_selected);

                startActivity(new Intent(CreatingActivity.this, MainActivity.class));
            }
        });
    }

    public void updateClick(){
        BTN_UPDATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date1 = String.valueOf(TEXT_DATE.getText());
                String category1 = String.valueOf(TEXT_CATEGORY.getText());
                String item1 = String.valueOf(TEXT_ITEM.getText());
                double cost1 = Double.parseDouble(TEXT_COST.getText().toString());

                DummyContent.DummyItem newItem = new DummyContent.DummyItem(ITEM.id, date1, category1, item1, cost1);
                DataTable.Update(newItem);

                startActivity(new Intent(CreatingActivity.this, MainActivity.class));
            }
        });
    }

    public void getnewIntent(){
        if(getIntent().hasExtra("item")){
            ITEM = (DummyContent.DummyItem) getIntent().getSerializableExtra("item");
            TEXT_CATEGORY.setText(ITEM.content);
            TEXT_ITEM.setText(ITEM.details);
            TEXT_COST.setText(String.valueOf(ITEM.cost));

            BTN_ADD.setVisibility(View.GONE);
            BTN_DELETE.setVisibility(View.VISIBLE);
            BTN_UPDATE.setVisibility(View.VISIBLE);
        }
    }
}
