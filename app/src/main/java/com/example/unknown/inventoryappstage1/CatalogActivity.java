package com.example.unknown.inventoryappstage1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.unknown.inventoryappstage1.data.ItemDbHelper;
import com.example.unknown.inventoryappstage1.data.ItemEntries;
import com.example.unknown.inventoryappstage1.data.ItemEntries.*;

public class CatalogActivity extends AppCompatActivity {
    private ItemDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper = new ItemDbHelper(this);
        displayDataBaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDataBaseInfo();
    }

    private void insertItem() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ItemEntry.COLUMN_ITEM_NAME, "Nokia X6");
        values.put(ItemEntry.COLUMN_ITEM_PRICE, 15999);
        values.put(ItemEntry.COLUMN_ITEM_QUANTITY, ItemEntry.QUANTITY_INSTOCK);
        values.put(ItemEntry.COLUMN_ITEM_SUPPLIER_NAME, "Unknown");
        values.put(ItemEntry.COLUMN_ITEM_MOBILE, "98765432");

        long newRowId = db.insert(ItemEntry.TABLE_NAME, null, values);

    }

    private void displayDataBaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ItemEntry._ID,
                ItemEntry.COLUMN_ITEM_NAME,
                ItemEntry.COLUMN_ITEM_PRICE,
                ItemEntry.COLUMN_ITEM_QUANTITY,
                ItemEntry.COLUMN_ITEM_SUPPLIER_NAME};

        Cursor c = db.query(
                ItemEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        Cursor cursor = db.rawQuery("SELECT * FROM " + ItemEntries.ItemEntry.TABLE_NAME, null);
        try {
            TextView displayView = (TextView) findViewById(R.id.text_view_item);
            displayView.setText("The item table contains " + cursor.getCount() + " items.\n\n");
            displayView.append(ItemEntry._ID + " - "
                    + ItemEntry.COLUMN_ITEM_NAME + "-"
                    + ItemEntry.COLUMN_ITEM_PRICE + "-"
                    + ItemEntry.COLUMN_ITEM_QUANTITY + "-"
                    + ItemEntry.COLUMN_ITEM_SUPPLIER_NAME + "-"
                    + ItemEntry.COLUMN_ITEM_MOBILE + "\n");

            int idColumnIndex = cursor.getColumnIndex(ItemEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_QUANTITY);
            int supplierColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_SUPPLIER_NAME);
            int mobileColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_MOBILE);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplier = cursor.getString(supplierColumnIndex);
                String currentMobile = cursor.getString(mobileColumnIndex);
                displayView.append("\n" + currentID + " - " +
                        currentName + "-" + currentPrice + "-" + currentQuantity + "-" + currentSupplier + "-" + currentMobile);
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertItem();
                displayDataBaseInfo();
                return true;
            case R.id.action_delete_all_entries:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
