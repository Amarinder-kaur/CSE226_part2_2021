package com.example.cse226_2021_part2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


//SQL Keywords are case-insensitive ( SELECT , FROM , WHERE , etc), but are often written in all caps.
// However in some setups table and column names are case-sensitive.
/*The constructor just stores the parameters into member variables and checks that the version is at least 1.
         The database gets created when getReadableDatabase() or getWritableDatabase() is called.
   */

public class P18DatabaseHandler extends SQLiteOpenHelper {
    // Definition of table and column names
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "spinnerExample";
    private static final String TABLE_NAME = "labels";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
  //  android.database.sqlite.SQLiteDatabase.CursorFactory: Used to allow returning sub-classes of Cursor when calling query.
    public P18DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // onCreate should always create your most up to date database
        // This method(onCreate()) is called when the app is newly installed
        // Category table create query
                String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT)";
        db.execSQL(CREATE_ITEM_TABLE);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  // version update code is written at the end of this java file
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }
    /**
     * Inserting new lable into lables table
     * */
    public void insertLabel(String label){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, label);//column name, column value

        // Inserting Row
        //String table: Gives the name of the table on which to perform the insert operation. This name needs to be the same as the name given to the table when it was created.
        //String nullColumnHack: Specifies a column that will be set to null if the ContentValues argument contains no data.
        //ContentValues values: Contains the data that will be inserted into the table.
        db.insert(TABLE_NAME, null, values);//tableName, nullColumnHack, CotentValues
        db.close(); // Closing database connection
    }
    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllLabels(){
        List<String> list1 = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
//The open helper query method can construct an SQL query and send it as a rawQuery to the database which returns a cursor.
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list1.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list1;
    }
}


/*
// onUpgrade is responsible for upgrading the database when you make
// changes to the schema. For each version the specific changes you made
// in that version have to be applied.
        for (int version = oldVersion + 1; version <= newVersion; version++) {
            switch (version) {

                case 2:
                    db.execSQL("ALTER TABLE " + TABLE_PRODUCTS + " ADD COLUMN " + COLUMN_DESCRIPTION + " TEXT;");
                    break;

                case 3:
                    db.execSQL(CREATE_TABLE_TRANSACTION);
                    break;
            }
        }
 */
/*
int update(String table, ContentValues values, String whereClause, String[] whereArgs)
The common parameters for the update methods are

String table: Defines the name of the table on which to perform the update. As with the insert statements, this string needs to match the name of a table in the database schema.
ContentValues values: Contains the key/value pairs that map the columns and values to be updated by the update statement.
String whereClause: Defines the WHERE clause of an UPDATE SQL statement. This string can contain the “?” character that will be
                    replaced by the values in the whereArgs parameter.
String[] whereArgs: Provides the variable substitutions for the whereClause argument
 */

/*To execute queries, there are two methods: Execute db.rawQuery method Execute db.query method
parameters details of db.query
query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy,
       String having, String orderBy)
Query the given table, returning a Cursor over the result set.

example: public Cursor getEmpByDept(String Dept) {
   SQLiteDatabase db=this.getReadableDatabase();
   String [] columns=new String[]{"_id",colName,colAge,colDeptName};
   Cursor c=db.query(viewEmps, columns, colDeptName+"=?",
        new String[]{Dept}, null, null, null);
   return c;
  }
 db.query has the following parameters:
 1.String Table Name: The name of the table to run the query against
 2.String [ ] columns: The projection of the query, i.e., the columns to retrieve String
 3.WHERE clause: where clause, if none pass null
 4.String [ ] selection args: The parameters of the WHERE clause
 5.String Group by: A string specifying group by clause
 6.String Having: A string specifying HAVING clause
 7.String Order By by: A string Order By by clause

 */
/* rawQuery parameters
rawQuery(String sql, String[] selectionArgs)
Runs the provided SQL and returns a Cursor over the result set.

 example:
 To execute a raw query to retrieve all departments:
Cursor getAllDepts()
  {
   SQLiteDatabase db=this.getReadableDatabase();
   Cursor cur=db.rawQuery("SELECT "+colDeptID+" as _id,
        "+colDeptName+" from "+deptTable,new String [] {});

   return cur;
  }
The rawQuery method has two parameters: String query: The select statement String[] selection args: The arguments
 if a WHERE clause is included in the select statement Notes The result of a query is returned in
 Cursor object. In a select statement if the primary key column (the id column) of the table has a name other
  than _id, then you have to use an alias in the form SELECT [Column Name] as _id cause the Cursor object
   always expects that the primary key column has the name _id or it will throw an exception .

 */