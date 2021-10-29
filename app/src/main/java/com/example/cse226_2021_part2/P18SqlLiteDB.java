package com.example.cse226_2021_part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
// for coding : https://www.javatpoint.com/android-sqlite-example-with-spinner
//for basic details : https://abhiandroid.com/database/sqlite
// for deatils: https://www.informit.com/articles/article.aspx?p=2731932
public class P18SqlLiteDB extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Button btnAdd;
    EditText inputLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p18_sql_lite_db);
        spinner = findViewById(R.id.spinner);
        btnAdd =  findViewById(R.id.btn_add);
        inputLabel = findViewById(R.id.input_label);
        spinner.setOnItemSelectedListener(this);
        // Loading spinner data from database
        loadSpinnerData();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String label = inputLabel.getText().toString();
                if (label.trim().length() > 0) {
                    P18DatabaseHandler db = new P18DatabaseHandler(getApplicationContext());
                    db.insertLabel(label);
                    // making input filed text to blank
                    inputLabel.setText("");
                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(inputLabel.getWindowToken(), 0);
                    // loading spinner with newly added data
                    loadSpinnerData();
                } else {
                   Toast.makeText(getApplicationContext(), "Please enter label name",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
        P18DatabaseHandler db = new P18DatabaseHandler(getApplicationContext());
        List<String> labels = db.getAllLabels();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub


    }
}
