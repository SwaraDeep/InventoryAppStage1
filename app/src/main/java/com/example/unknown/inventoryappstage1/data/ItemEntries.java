package com.example.unknown.inventoryappstage1.data;

import android.provider.BaseColumns;

public class ItemEntries implements BaseColumns {

    public final class ItemEntry{
        public static final String TABLE_NAME = "items";
        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_ITEM_NAME = "name";
        public static final String COLUMN_ITEM_PRICE = "price";
        public static final String COLUMN_ITEM_QUANTITY = "quantity";
        public static final String COLUMN_ITEM_SUPPLIER_NAME = "supplier";
        public static final String COLUMN_ITEM_MOBILE = "mobile";

        public static final int QUANTITY_INSTOCK = 0;
        public static final int QUANTITY_OUTSTOCK = 1;
    }
}
