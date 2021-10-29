package com.example.cse226_2021_part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
//for more details
// https://codelabs.developers.google.com/codelabs/android-training-drawables-styles-and-themes/#4
// do changes in following for this(style and theme) application
//In style:--> parent="Theme.AppCompat.DayNight.DarkActionBar">
//create styles,drawable and menu.

//Material design is a comprehensive guide for visual, motion, and interaction design across platforms and devices. In this codelab, you'll learn the principles of this design language by building a sample Android app.

public class P17STyleThemeFinal extends AppCompatActivity {
    // Member variables for holding the score
    private int mScore1;
    private int mScore2;
    TextView tvcount1,tvcount2;
    int c1=0,c2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p17_style_theme_final);

        tvcount1 = (TextView) findViewById(R.id.count1);
        tvcount2 = (TextView)findViewById(R.id.count2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu1,menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.dark).setTitle("Day Mode");
        } else{
            menu.findItem(R.id.dark).setTitle("Dark Mode");
        }
        return true;
    }
// AppCompatDelegate.getDefaultNightMode() has two values:
//   1. MODE_NIGHT_NO
 // 2. MODE_NIGHT_YES
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.dark) {
            int night_mode = AppCompatDelegate.getDefaultNightMode();
            if (night_mode == AppCompatDelegate.MODE_NIGHT_YES)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            recreate();
        }
        return true;
    }
    //onSaveInstanceState and onRestoreInstanceState these methods are used to
//store and restore the state of instance during Changing the mode and
// during change the orientation

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ID",Integer.parseInt(tvcount1.getText().toString()));
        outState.putInt("ID1",Integer.parseInt(tvcount2.getText().toString()));
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        int a = savedInstanceState.getInt("ID");
        int b = savedInstanceState.getInt("ID1");

        tvcount1.setText(String.valueOf(a));
        tvcount2.setText(String.valueOf(b));
    }

    public void doincrease(View view)
    {
        switch (view.getId())
        {
            case R.id.increase1:
                Toast.makeText(this,"increase1", Toast.LENGTH_SHORT).show();
                String s1 = tvcount1.getText().toString();
                int a1 = Integer.parseInt(s1);
                a1=a1+1;
                tvcount1.setText(""+a1);
                break;
            case R.id.increase2:
                Toast.makeText(this,"increase2",Toast.LENGTH_SHORT).show();
                String s2 = tvcount2.getText().toString();
                int a2 = Integer.parseInt(s2);
                a2++;
                tvcount2.setText(""+a2);
                break;
        }

    }

    public void dodecrease(View view)
    {
       switch (view.getId())
        {
            case R.id.decrease1:
                String s1 = tvcount1.getText().toString();
                int a = Integer.parseInt(s1);
                if (a<=0)
                {
                    Toast.makeText(this,"Score cant be less than 0",Toast.LENGTH_SHORT).show();
                    break;}
                Toast.makeText(this,"decrease1",Toast.LENGTH_SHORT).show();
                a--;
                tvcount1.setText(""+a);
                break;
            case R.id.decrease2:
                String s2 = tvcount2.getText().toString();
                int a2 = Integer.parseInt(s2);
                if (a2<=0)
                {
                    Toast.makeText(this,"Score cant be less than 0",Toast.LENGTH_SHORT).show();
                    break;}
                Toast.makeText(this,"decrease2",Toast.LENGTH_SHORT).show();
                a2--;
                tvcount2.setText(""+a2);
                break;
        }
    }
public    void result(View v)
    {
        String s2 = tvcount2.getText().toString();
        int a2 = Integer.parseInt(s2);
        String s1 = tvcount1.getText().toString();
        int a1 = Integer.parseInt(s1);
        if (a1> a2)
            Toast.makeText(getApplicationContext(),"Team1 is winner with score"+ a1,Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Team2 is winner with score"+ a2,Toast.LENGTH_SHORT).show();

    }
}
