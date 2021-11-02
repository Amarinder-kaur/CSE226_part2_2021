package com.example.cse226_2021_part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
// for coding : https://www.javatpoint.com/android-sqlite-example-with-spinner
//for basic details : https://abhiandroid.com/database/sqlite
// for deatils: https://www.informit.com/articles/article.aspx?p=2731932
public class P18SqlLiteDB extends AppCompatActivity  {
    ListView list;
    Button btnAdd;
    EditText inputLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p18_sql_lite_db);
        list = findViewById(R.id.spinner);
        btnAdd = findViewById(R.id.btn_add);
        inputLabel = findViewById(R.id.input_label);
        // Loading list data from database
        loadListData();
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
                    loadListData();
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter label name",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    /* delete on longclick
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                P18DatabaseHandler db = new P18DatabaseHandler(getApplicationContext());
                String uname = list.getItemAtPosition(pos).toString();
                int a = db.DeleteName(uname);
                if (a <= 0) {
                    Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                }
                loadListData();

                return true;

            }

        });

     */
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                P18DatabaseHandler db = new P18DatabaseHandler(getApplicationContext());
                String uname = list.getItemAtPosition(pos).toString();
                Toast.makeText(getApplicationContext(), "Enter Data" + uname, Toast.LENGTH_SHORT).show();
                   //Do your tasks here
                alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        Toast.makeText(getApplicationContext(),"You clicked yes button",Toast.LENGTH_LONG).show();
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


                /*    Dialog dialog = new Dialog(Activity.this);
            dialog.setContentView(R.layout.p18customalertdialoglayout);
            EditText updateold = dialog.findViewById(R.id.editText3);
            EditText updatenew = dialog.findViewById(R.id.editText5);


            Button updateButton = (Button) dialog.findViewById(R.id.button3);
            String u1 =list.getItemAtPosition(pos).toString();
            updateold.setText("" + u1);
            String u2 = updatenew.getText().toString();

//            Toast.makeText(getApplicationContext(), "You selected: " + u1,Toast.LENGTH_LONG).show();

            // if button is clicked, close the custom dialog
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (u1.isEmpty() || u2.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter Data", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();

                      int a = db.updateName(u1, u2);
                        if (a <= 0) {
                            Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_SHORT).show();

                            updateold.setText("");
                            updatenew.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();

                            updateold.setText("");
                            updatenew.setText("");
                        }
                    }
                }*/

                loadListData();
                return true;
            }
        });

    }
    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadListData() {
        P18DatabaseHandler db = new P18DatabaseHandler(getApplicationContext());
        List<String> labels = db.getAllLabels();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, labels);

        // attaching data adapter to spinner
        list.setAdapter(dataAdapter);
    }


}
