package com.example.cse226_2021_part2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class P16MenuAndIncrementValue extends AppCompatActivity {
Button BInc,BDec;
TextView T1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p16_menu_and_increment_value);
    BInc=findViewById(R.id.btnInc);
    BDec=findViewById(R.id.btnDec);

    T1= findViewById(R.id.txt1);
    BInc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String s1 = T1.getText().toString();
            int a1 = Integer.parseInt(s1);
            a1=a1+1;
            T1.setText(""+a1);
        }
    });
        BDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = T1.getText().toString();
                int a1 = Integer.parseInt(s1);
                a1=a1-1;
                T1.setText(""+a1);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}