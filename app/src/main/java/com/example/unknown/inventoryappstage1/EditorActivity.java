package com.example.unknown.inventoryappstage1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.unknown.inventoryappstage1.data.ItemDbHelper;
import com.example.unknown.inventoryappstage1.data.ItemEntries.*;

public class EditorActivity extends AppCompatActivity {

    private EditText mNameEdit, mPriceEdit, mQuantityEdit, mSupplierEdit, mMobileEdit;
    private int mQuantity = ItemEntry.QUANTITY_OUTSTOCK;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mNameEdit = (EditText) findViewById(R.id.edit_name);
        mPriceEdit = (EditText) findViewById(R.id.edit_price);
        mSupplierEdit = (EditText) findViewById(R.id.edit_supplier);
        mMobileEdit = (EditText) findViewById(R.id.edit_mobile);
        mSpinner = (Spinner) findViewById(R.id.spinner_quantity);

        setupSpinner();
    }

    private void setupSpinner() {

        ArrayAdapter quantitySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.quantity_options, android.R.layout.simple_spinner_item);

        quantitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mSpinner.setAdapter(quantitySpinnerAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.quantity_instock))) {
                        mQuantity = ItemEntry.QUANTITY_INSTOCK;
                    } else if (selection.equals(getString(R.string.quantity_out_of_stock))) {
                        mQuantity = ItemEntry.QUANTITY_OUTSTOCK;
                    } else
                        mQuantity = ItemEntry.QUANTITY_OUTSTOCK;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mQuantity = ItemEntry.QUANTITY_OUTSTOCK;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                insertItem();
                finish();
                return true;
            case R.id.action_delete:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertItem() {
        String nameString = mNameEdit.getText().toString().trim();
        String priceString = mPriceEdit.getText().toString().trim();
        String supplierString = mSupplierEdit.getText().toString().trim();
        String mobileString = mMobileEdit.getText().toString().trim();

        ItemDbHelper mDbHelper = new ItemDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ItemEntry.COLUMN_ITEM_NAME, nameString);
        values.put(ItemEntry.COLUMN_ITEM_PRICE, priceString);
        values.put(ItemEntry.COLUMN_ITEM_QUANTITY, mQuantity);
        values.put(ItemEntry.COLUMN_ITEM_SUPPLIER_NAME, supplierString);
        values.put(ItemEntry.COLUMN_ITEM_MOBILE, mobileString);

        long rowId = db.insert(ItemEntry.TABLE_NAME, null, values);

        if (rowId == 1) {
            Toast.makeText(this, "Error saving item", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Added item with Id: " + rowId, Toast.LENGTH_LONG).show();
        }
    }
}
