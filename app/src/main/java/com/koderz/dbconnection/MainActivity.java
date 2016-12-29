package com.koderz.dbconnection;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    MyDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;


        myDB = new MyDB(mContext);

        /*btn 1 click*/
        insertDetails();

        /*btn 2 click*/
        pringDataFromDB();
    }

    private void insertDetails() {
        ContactsDto contactsDto = new ContactsDto();

        contactsDto.setId(1);
        contactsDto.setName("pavan Tester");
        contactsDto.setPhone("1234567890");

        myDB.insertData(contactsDto);
    }

    private void pringDataFromDB() {
        Log.i("TAG", "pringDataFromDB: " + myDB.readData().get(0).getName() + " " + myDB.readData().get(0).getPhone()
                + " " + myDB.readData().get(0).getId());

        Toast.makeText(mContext, "pringDataFromDB: " + myDB.readData().get(0).getName() + " " + myDB.readData().get(0).getPhone()
                + " " + myDB.readData().get(0).getId(), Toast.LENGTH_SHORT).show();


        ArrayList<ContactsDto> contactsDtos = myDB.readData();

        for(int i=0;i<contactsDtos.size();i++){
            Log.i("TAG", "pringDataFromDB: " + myDB.readData().get(i).getName() + " " + myDB.readData().get(i).getPhone()
                    + " " + myDB.readData().get(i).getId());
        }


    }
}
