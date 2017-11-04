package com.business.cacoguaa.business.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.business.cacoguaa.business.Models.Business;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ace on 4/11/2017.
 */

public class BusinessOperations {
    public static final String LOGTAG = "BUS_MNGMNT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            BusinessDBHandler.COLUMN_ID,
            BusinessDBHandler.COLUMN_NAME,
            BusinessDBHandler.COLUMN_URL,
            BusinessDBHandler.COLUMN_PHONE,
            BusinessDBHandler.COLUMN_EMAIL,
            BusinessDBHandler.COLUMN_DESCRIPTION,
            BusinessDBHandler.COLUMN_TYPE

    };

    public BusinessOperations(Context context){
        dbhandler = new BusinessDBHandler(context);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();
    }

    //Insert a new business
    public Business addBusiness(Business business){
        ContentValues values  = new ContentValues();
        values.put( BusinessDBHandler.COLUMN_NAME, business.getName());
        values.put( BusinessDBHandler.COLUMN_URL,business.getUrl());
        values.put( BusinessDBHandler.COLUMN_PHONE, business.getPhone());
        values.put( BusinessDBHandler.COLUMN_EMAIL, business.getEmail());
        values.put( BusinessDBHandler.COLUMN_DESCRIPTION, business.getDescription());
        values.put( BusinessDBHandler.COLUMN_TYPE, business.getType());
        long insertid = database.insert(BusinessDBHandler.TABLE_BUSINESS,null,values);
        business.setId(insertid);
        return business;
    }

    //Get One Business
    // Getting single Employee
    public Business getEmployee(long id) {
        database = dbhandler.getWritableDatabase();
        Cursor cursor = database.query(BusinessDBHandler.TABLE_BUSINESS,allColumns,BusinessDBHandler.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null) cursor.moveToFirst();
        Business b = new Business(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getString(6));
        // return Business
        return b;
    }

    public List<Business> getAllBusiness() {

        Cursor cursor = database.query(BusinessDBHandler.TABLE_BUSINESS,allColumns,null,null,null, null, null);

        List<Business> businesses = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Business business = new Business();
                business.setId(cursor.getLong(cursor.getColumnIndex(BusinessDBHandler.COLUMN_ID)));
                business.setName(cursor.getString(cursor.getColumnIndex(BusinessDBHandler.COLUMN_NAME)));
                business.setUrl(cursor.getString(cursor.getColumnIndex(BusinessDBHandler.COLUMN_URL)));
                business.setPhone(cursor.getString(cursor.getColumnIndex(BusinessDBHandler.COLUMN_PHONE)));
                business.setEmail(cursor.getString(cursor.getColumnIndex(BusinessDBHandler.COLUMN_EMAIL)));
                business.setDescription(cursor.getString(cursor.getColumnIndex(BusinessDBHandler.COLUMN_DESCRIPTION)));
                business.setType(cursor.getString(cursor.getColumnIndex(BusinessDBHandler.COLUMN_TYPE)));
                businesses.add(business);
            }
        }
        // return All Business
        return businesses;
    }

    // Updating Business
    public int updateEmployee(Business business) {
        ContentValues values = new ContentValues();
        values.put(BusinessDBHandler.COLUMN_NAME, business.getName());
        values.put(BusinessDBHandler.COLUMN_URL, business.getUrl());
        values.put(BusinessDBHandler.COLUMN_PHONE, business.getPhone());
        values.put(BusinessDBHandler.COLUMN_EMAIL, business.getEmail());
        values.put(BusinessDBHandler.COLUMN_DESCRIPTION, business.getDescription());
        values.put(BusinessDBHandler.COLUMN_TYPE, business.getType());

        // updating row
        return database.update(BusinessDBHandler.TABLE_BUSINESS, values,
                BusinessDBHandler.COLUMN_ID + "=?",new String[] { String.valueOf(business.getId())});
    }

    // Deleting Business
    public void removeEmployee(Business business) {
        database.delete(BusinessDBHandler.TABLE_BUSINESS, BusinessDBHandler.COLUMN_ID + "=" + business.getId(), null);
    }

    public List<Business> getBusiness(String name) {
        name = "name = '" + name + "' or type = '" + name + "'";
        Cursor cursor = database.query(BusinessDBHandler.TABLE_BUSINESS,allColumns,name,null, null,null, null, null);
        List<Business> businesses = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Business business = new Business();
                business.setId(cursor.getLong(cursor.getColumnIndex(BusinessDBHandler.COLUMN_ID)));
                business.setName(cursor.getString(cursor.getColumnIndex(BusinessDBHandler.COLUMN_NAME)));
                business.setUrl(cursor.getString(cursor.getColumnIndex(BusinessDBHandler.COLUMN_URL)));
                business.setPhone(cursor.getString(cursor.getColumnIndex(BusinessDBHandler.COLUMN_PHONE)));
                business.setEmail(cursor.getString(cursor.getColumnIndex(BusinessDBHandler.COLUMN_EMAIL)));
                business.setDescription(cursor.getString(cursor.getColumnIndex(BusinessDBHandler.COLUMN_DESCRIPTION)));
                business.setType(cursor.getString(cursor.getColumnIndex(BusinessDBHandler.COLUMN_TYPE)));
                businesses.add(business);
            }
        }
        return  businesses;
    }
}
