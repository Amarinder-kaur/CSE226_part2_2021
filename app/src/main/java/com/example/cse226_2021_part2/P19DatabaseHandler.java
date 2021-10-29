package com.example.cse226_2021_part2;
// for deatils: https://abhiandroid.com/database/sqlite
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class P19DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myDatabase";    // Database Name
    private static final String TABLE_NAME = "myTable";   // Table Name
    private static final int DATABASE_Version = 1;    // Database Version
      private static final String UID = "_id";     // Column I (Primary Key)
    private static final String NAME = "Name";    //Column II
    private static final String MyPASSWORD = "Password";    // Column III
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ,"
            + MyPASSWORD + " VARCHAR(225));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;

    public P19DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Toast.makeText(context, "OnUpgrade", Toast.LENGTH_SHORT).show();

            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e) {
            Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(String name, String pass) {
        SQLiteDatabase dbb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(MyPASSWORD, pass);
        long id = dbb.insert(TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {UID, NAME,MyPASSWORD};
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int cid = cursor.getInt(cursor.getColumnIndex(UID));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(NAME));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(MyPASSWORD));
            buffer.append(cid + "   " + name + "   " + password + " \n");
        }
        return buffer.toString();
    }

}