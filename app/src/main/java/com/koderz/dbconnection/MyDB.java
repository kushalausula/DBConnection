package com.koderz.dbconnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Pupa on 12/29/2016.
 */

public class MyDB extends SQLiteOpenHelper {

    Context mContext;


    public MyDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    public MyDB(Context mContext){
        super(mContext,"MYDBTest",null,1);
        this.mContext=mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(
                "create table contacts (id integer, name text,phone text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("delete from contacts");
    }


    public void insertData(ContactsDto contactsDto){

        SQLiteDatabase db=this.getWritableDatabase();


        ContentValues contentValues=new ContentValues();

        contentValues.put("id",contactsDto.getId());
        contentValues.put("name",contactsDto.getName());
        contentValues.put("phone",contactsDto.getPhone());


        db.insertWithOnConflict("contacts",null,contentValues,SQLiteDatabase.CONFLICT_IGNORE);

        
        
    }

    public ArrayList<ContactsDto> readData(){

        ArrayList<ContactsDto> contactsDtos=new ArrayList<>();


        SQLiteDatabase db=this.getReadableDatabase();


        Cursor c=db.rawQuery("select * from contacts",null);

        /*read data from cursor*/


        while (c.moveToNext()){
            ContactsDto contactsDto=new ContactsDto();

            int id=c.getInt(c.getColumnIndexOrThrow("id"));
            String name= c.getString(c.getColumnIndexOrThrow("name"));
            String  phone= c.getString(c.getColumnIndexOrThrow("phone"));

            contactsDto.setId(id);
            contactsDto.setName(name);
            contactsDto.setPhone(phone);

            contactsDtos.add(contactsDto);
        }
        return contactsDtos;
    }


}
