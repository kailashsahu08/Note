package com.example.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "notesApplication";
    public static final String  TABLE_NAME = "note";
    //here we have to declare the attribute
//    public static final String KEY_ID = "note_id";
    public static final String TITLE = "note_title";
    public static final String DESCRIPTION = "note_desc";

    public DataBase(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT,"+TITLE+" TEXT,"+DESCRIPTION+" TEXT)";
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public long addNotes(String title,String temp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE,title);
        values.put(DESCRIPTION,temp);
        long result = db.insert(TABLE_NAME,null,values);
        db.close();
        return  result;
    }
    public ArrayList<dbclass> getAllNotes(){
        ArrayList<dbclass> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //generate the query  to read from the data base
        String select  = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);
        //loop through now
        if(cursor.moveToFirst())
        {
            do {
                int id1 = cursor.getInt(0);
                String title1 = cursor.getString(1);
                String note1 = cursor.getString(2);
                dbclass dataBaseClass = new dbclass(title1,note1,id1);
                noteList.add(dataBaseClass);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  noteList;
    }
    public boolean update(int id1,String title1,String desc1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE,title1);
        contentValues.put(DESCRIPTION,desc1);
        int i = db.update(TABLE_NAME,contentValues,"id=?",new String[]{id1+""});
        db.close();
        if(i>0)
            return true;
        else
            return false;
    }
    public boolean deleteNote(int id1){
        SQLiteDatabase db = this.getWritableDatabase();
        int i= db.delete(TABLE_NAME,"id=?",new String[]{id1+""});
        if(i>0)
            return true;
        else
            return  false;
    }
}
