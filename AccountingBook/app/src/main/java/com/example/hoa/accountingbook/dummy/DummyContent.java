package com.example.hoa.accountingbook.dummy;

import java.io.Serializable;

public class DummyContent {
    public static class DummyItem implements Serializable {
        public long id;
        public String date;
        public String content;
        public String details;
        public double cost;

        public DummyItem(long id, String date, String content, String details, double cost) {
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
