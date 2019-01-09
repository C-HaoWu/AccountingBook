package com.example.hoa.accountingbook.dummy;

import android.icu.util.Calendar;

import com.example.hoa.accountingbook.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private int COUNT = 5; //資料量

    // TODO: 建立 List
    {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    // TODO:  抓資料庫內容
    private static DummyItem createDummyItem(int position) {
        String id = String.valueOf(position); //ID
        // String date = MainActivity.dateToday; //日期
        Calendar calendar = MainActivity.calendar;
        String date = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)); //日期
        if(date == null){
            date = "17";
        }
        String content = "食物 "; //分類
        //String details = makeDetails(position); //內容
        String details = "早餐 " + position; //內容
        String cost = "$" + position*20; //金額

        return new DummyItem(id, date, content, details, cost);
    }

    // TODO: 可以不用
    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < 1; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String date;
        public final String content;
        public final String details;
        public final String cost;

        public DummyItem(String id, String date, String content, String details, String cost) {
            this.id = id;
            this.date = date;
            this.content = content;
            this.details = details;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
