package com.example.hoa.accountingbook;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hoa.accountingbook.dummy.DummyContent;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class DataTable {

    // 資料庫物件
    private static SQLiteDatabase db;
    // 建構子，一般的應用都不需要修改
    public DataTable(Context context) {
        db = DataBase.getDatabase(context);
    }

    // 表格名稱
    public static final String TABLE_NAME = "item";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    public static final String DATE_COLUMN = "_date";
    public static final String CONTENT_COLUMN = "_content";
    public static final String DETAILS_COLUMN = "_details";
    public static final String COST_COLUMN = "_cost";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DATE_COLUMN + " TEXT NOT NULL, " +
                    CONTENT_COLUMN + " TEXT NOT NULL, " +
                    DETAILS_COLUMN + " TEXT, " +
                    COST_COLUMN + " REAL) " ;


    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    // 取得每日資料
    public static List<DummyContent.DummyItem> getData() {
        List<DummyContent.DummyItem> result = new ArrayList<>();

        String date = MainActivity.dateToday;
        String queryByDate = DATE_COLUMN + "= '" + date+"' ";

        Cursor cursor = db.query(
                TABLE_NAME, null, queryByDate, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }
        cursor.close();

        return result;
    }

    // 新增一筆資料
    public static DummyContent.DummyItem Insert(DummyContent.DummyItem item) {
        ContentValues cv = new ContentValues();
        cv.put(DATE_COLUMN, item.date);
        cv.put(CONTENT_COLUMN, item.content);
        cv.put(DETAILS_COLUMN, item.details);
        cv.put(COST_COLUMN, item.cost);

        long _id = db.insert(TABLE_NAME, null, cv);
        item.id = _id;

        return item;
    }

    //刪除一筆資料
    public static boolean Delete(long id) {
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where , null) > 0;
    }

    //修改一筆資料
    public static boolean Update(DummyContent.DummyItem item){
        ContentValues cv = new ContentValues();
        cv.put(DATE_COLUMN, item.date);
        cv.put(CONTENT_COLUMN, item.content);
        cv.put(DETAILS_COLUMN, item.details);
        cv.put(COST_COLUMN, item.cost);

        // 設定修改資料的條件為編號，格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + item.id;
        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    //依照類別分類資料
    public static List<BarEntry> Category_Cost(){
        List<BarEntry> chartData = new ArrayList<>();

        String date = MainActivity.dateToday;
        String queryByDate = DATE_COLUMN + "= '" + date+"' ";

        Cursor cursor = db.query(
                TABLE_NAME, null, queryByDate, null, CONTENT_COLUMN, null, null, null);

        while (cursor.moveToNext()) {
            //result.add(getRecord(cursor));
        }
        cursor.close();

        return chartData;
    }

    // 把Cursor目前的資料包裝為物件
    public static DummyContent.DummyItem getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        DummyContent.DummyItem result = new DummyContent.DummyItem(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getDouble(4));
        // 回傳結果
        return result;
    }




}
