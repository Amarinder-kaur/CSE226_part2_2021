package com.example.cse226_2021_part2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
//for coding : https://abhiandroid.com/database/sqlite
public class P19StaticDataSqlLiteDB extends AppCompatActivity {
    EditText Name, Pass , updateold, updatenew, delete;
    P19DatabaseHandler helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p19_static_data_sql_lite_db);

        Name = (EditText) findViewById(R.id.editName);
        Pass = (EditText) findViewById(R.id.editPass);
        updateold=findViewById(R.id.editText3);
        updatenew=findViewById(R.id.editText5);
        delete=findViewById(R.id.editText6);

        helper = new P19DatabaseHandler (this);
    }

    public void addUser(View view) {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();
        if (t1.isEmpty() || t2.isEmpty()) {

            Toast.makeText(getApplicationContext(),"Enter Both Name and Password",Toast.LENGTH_SHORT).show();
        } else {
            long id = helper.insertData(t1, t2);
            if (id <= 0) {

                Toast.makeText(getApplicationContext(),"Insertion Unsuccessful",Toast.LENGTH_SHORT).show();
                Name.setText("");
                Pass.setText("");
            } else {
                Toast.makeText(getApplicationContext(),"Insertion Successful",Toast.LENGTH_SHORT).show();

                Name.setText("");
                Pass.setText("");
            }
        }
    }

    public void viewdata(View view) {
        String data = helper.getData();
        Toast.makeText(getApplicationContext(),""+ data,Toast.LENGTH_SHORT).show();
    }

    public void update( View view)
    {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if(u1.isEmpty() || u2.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter Data",Toast.LENGTH_SHORT).show();
        }
        else
        {
            int a= helper.updateName( u1, u2);
            if(a<=0)
            {
                Toast.makeText(getApplicationContext(),"Unsuccessful",Toast.LENGTH_SHORT).show();
                updateold.setText("");
                updatenew.setText("");
            } else {
                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                updateold.setText("");
                updatenew.setText("");
            }
        }
    }
    public void delete( View view)
    {
        String uname = delete.getText().toString();
        if(uname.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter Data",Toast.LENGTH_SHORT).show();
        }
        else{
            int a= helper.delete(uname);
            if(a<=0)
            {
                Toast.makeText(getApplicationContext(),"Unsuccessful",Toast.LENGTH_SHORT).show();
                delete.setText("");
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                delete.setText("");
            }
        }
    }

}