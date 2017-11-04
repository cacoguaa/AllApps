package com.business.cacoguaa.business;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.business.cacoguaa.business.DB.BusinessOperations;
import com.business.cacoguaa.business.Models.Business;

import java.util.List;

public class ViewAllBusinesses extends ListActivity {

    private BusinessOperations businessOps;
    List<Business> businesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_businesses);
        businessOps = new BusinessOperations(this);
        businessOps.open();
        businesses = businessOps.getAllBusiness();
        businessOps.close();
        ArrayAdapter<Business> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, businesses);
        setListAdapter(adapter);
    }
}
