package com.example.unknown.inventoryappstage1.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class ItemEntries {
    private ItemEntries() {
    }

    public static final String CONTENT_AUTHORITY = "com.example.android.items";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_ITEMS = "items";


    public static final class ItemEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ITEMS);
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
