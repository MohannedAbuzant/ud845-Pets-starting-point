package com.example.android.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Context;

import com.example.android.pets.data.PetsContract;
import com.example.android.pets.data.PetsContract.petsEntry;
import com.example.android.pets.data.PetDbHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        displayDatabaseInfo();
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     *
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.

        // Create and/or open a database to read from it

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        String [] projection = {
            petsEntry.COLUMN_Id,
            petsEntry.COLMUN_WEIGHT,
            petsEntry.COLMUN_GENDER,
            petsEntry.COLUMN_BREED,
            petsEntry.COLUMN_NAME};

        Cursor cursor= cursor.query(petsEntry.CONTENT_URI,projection,null,null,null,null);
        TextView displayView = (TextView) findViewById(R.id.text_view_pet);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(petsEntry._ID + " - " +
                    petsEntry.COLUMN_NAME + " - " +petsEntry.COLMUN_WEIGHT +" - " + petsEntry.COLUMN_BREED +" - " + petsEntry.COLMUN_GENDER);
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(petsEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(petsEntry.COLUMN_NAME);
            int breedColumnIndex= cursor.getColumnIndex(petsEntry.COLMUN_GENDER);
            int weightColumnIndex=cursor.getColumnIndex(petsEntry.COLMUN_WEIGHT);
            int genderColumnIndex=cursor.getColumnIndex(petsEntry.COLMUN_GENDER);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentweight = cursor.getInt(weightColumnIndex);
                int currentgender=cursor.getInt(genderColumnIndex);
                String currentbreed=cursor.getString(breedColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + currentName+ " - " + currentweight +" - "+ currentbreed +" - "+ currentgender
                        ));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
    private void insertpet(){


// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(petsEntry.COLUMN_NAME, "Toto");
        values.put(petsEntry.COLUMN_BREED, "Terrier");
        values.put(petsEntry.COLMUN_GENDER,petsEntry.gender_Male);
        values.put(petsEntry.COLMUN_WEIGHT,7);

// Insert the new row, returning the primary key value of the new row
        Uri newUri = getContentResolver().insert(petsEntry.CONTENT_URI, values);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu9
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertpet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}